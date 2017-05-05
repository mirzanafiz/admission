package com.teampolaris.admisson;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Nafiz on 7/3/2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter
{
    private List<Fragment> fragments;
    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList)
    {
        super(fm);
        this.fragments = fragmentList;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
}
