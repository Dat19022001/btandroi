package com.example.baith21.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.baith21.fragment.FragmentHistory;
import com.example.baith21.fragment.FragmentHome;
import com.example.baith21.fragment.FragmentSearch;

public class ViewPagerAdater extends FragmentPagerAdapter {

    public ViewPagerAdater(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentHome();
            case 1:return new FragmentHistory();
            case 2:return  new FragmentSearch();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
