package com.openup.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.openup.fragments.ProfileAboutFragment;
import com.openup.fragments.ProfilePhotoFragment;

public class ProfileTabAdapter  extends FragmentStatePagerAdapter {
    private int tabCount, setTabPosition;
    public ProfileTabAdapter(FragmentManager fragmentManager, int tabCount, int setTabPosition) {
        super(fragmentManager);
        this.tabCount = tabCount;
        this.setTabPosition = setTabPosition;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                ProfilePhotoFragment tab1 = new ProfilePhotoFragment();
                return tab1;
            case 1:
                ProfileAboutFragment tab2 = new ProfileAboutFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
