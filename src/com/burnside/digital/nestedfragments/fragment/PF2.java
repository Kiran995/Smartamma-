package com.burnside.digital.nestedfragments.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.burnside.digital.nestedfragments.R;

/**
 * This fragment hosts the viewpager that will use a FragmentPagerAdapter to display child fragments.
 */
public class PF2 extends Fragment {

    public static final String TAG = PF2.class.getName();

    public static PF2 newInstance() {
        return new PF2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_parent_viewpager, container, false);

        ViewPager viewPager = (ViewPager) root.findViewById(R.id.viewPager);
        /** Important: Must use the child FragmentManager or you will see side effects. */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        return root;
    }

    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putInt(CF2.POSITION_KEY, position);
            return CF2.newInstance(args);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Child CF " + position;
        }

    }

}
