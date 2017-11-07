package com.rondaulz.leaderboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

class StudentDetailsViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private List<String> marks = new ArrayList<>();

    private static final String DIG = "digitalElectronics";
    private static final String ENG = "English";
    private static final String DS = "DataStructures";
    private static final String DBMS = "DatabaseManagementSystem";
    private static final String CPlUS = "CPlus";
    private static final String OS = "OperatingSystem";
    private static final String UNIX = "unix";
    private static final String VB = "VisualBasic";

    public StudentDetailsViewPagerAdapter(FragmentManager manager, List<String> marks) {
        super(manager);
        this.marks = marks;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString(DIG, marks.get(0));
                bundle.putString(ENG, marks.get(1));
                bundle.putString(DS, marks.get(2));
                bundle.putString(DBMS, marks.get(3));
                bundle.putString(CPlUS, marks.get(4));
                bundle.putString(OS, marks.get(5));
                bundle.putString(UNIX, marks.get(6));
                bundle.putString(VB, marks.get(7));
                DatabaseDetailsFragment mMarksDetails = new DatabaseDetailsFragment();
                mMarksDetails.setArguments(bundle);
                return mMarksDetails;
            case 1:
                return new SkillDetailsFragment();
        }
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}