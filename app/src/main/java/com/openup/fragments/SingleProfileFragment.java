package com.openup.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.openup.R;
import com.openup.adapters.ProfileTabAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleProfileFragment extends Fragment {

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private int setTabPosition = 0;

    public SingleProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_single_profile, container, false);
        ButterKnife.bind(this,rootView);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.photos)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.about)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab LayoutTab) {

                //Log.e("current tab", "position " + LayoutTab.getPosition());
                viewPager.setCurrentItem(LayoutTab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {
                //Log.e("on tab unselected", "tab");
            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {
                //Log.e("on tab reselected", "re select tab");
                viewPager.setCurrentItem(LayoutTab.getPosition());
            }
        });

        ProfileTabAdapter fragmentAdapter = new ProfileTabAdapter(getChildFragmentManager(), tabLayout.getTabCount(),setTabPosition);
        if (fragmentAdapter != null){
            viewPager.setAdapter(fragmentAdapter);
        }
        viewPager.setOffscreenPageLimit(0);
        viewPager.setCurrentItem(setTabPosition);

        return rootView;
    }
}
