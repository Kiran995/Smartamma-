package com.burnside.digital.nestedfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;

import com.burnside.digital.nestedfragments.fragment.PF2;
import com.burnside.digital.nestedfragments.fragment.ParentViewPagerFragment;
import com.burnside.digital.nestedfragments.fragment.SingleChildFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.burnside.digital.nestedfragments.fragment.ParentTabHostFragment;
import com.burnside.digital.nestedfragments.fragment.ParentViewPagerFragment;
import com.burnside.digital.nestedfragments.fragment.TabHostLayoutFragment;

public class TabHostFragmentActivity extends FragmentActivity {

    private String LANG_CURRENT = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_tabhost_title);

        FragmentTabHost tabHost = new FragmentTabHost(this);
        setContentView(tabHost);

        tabHost.setup(this, getSupportFragmentManager(), R.layout.activity_nest_list);

        tabHost.addTab(tabHost.newTabSpec("ParentViewPagerFragment").setIndicator("View Pager"),
                ParentViewPagerFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("PF2").setIndicator("Next Fragment"),
                PF2.class, null);
        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LANG_CURRENT.equals("ar")) {
                    changeLang(TabHostFragmentActivity.this, "en");
                } else {
                    changeLang(TabHostFragmentActivity.this, "ar");
                }
                finish();
                startActivity(new Intent(TabHostFragmentActivity.this, TabHostFragmentActivity.class));
            }
        });
    }
    public void changeLang(Context context, String lang) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Language", lang);
        editor.apply();
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        LANG_CURRENT = preferences.getString("Language", "en");

        super.attachBaseContext(MyContextWrapper.wrap(newBase, LANG_CURRENT));
    }

}
