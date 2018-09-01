package com.openup.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;

import com.openup.R;
import com.openup.adapters.CreateProfilePagerAdapter;
import com.openup.base.BaseActivity;
import com.openup.data.pref.PreferenceManager;
import com.openup.models.CreateProfileModel;
import com.openup.utils.CarouselEffectTransformer;
import com.openup.utils.CirclePageIndicator;
import com.openup.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProfileActivity extends BaseActivity implements CreateProfilePagerAdapter.ViewPagerClickListener{

    private LinearLayoutManager layoutManager;
    private CreateProfilePagerAdapter createProfilePagerAdapter;
    private String gender;
    private String idToken, name, email, photo;

    @BindView(R.id.viewpager)
    ViewPager viewpagerTop;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        ButterKnife.bind(this);

        Bundle intent = getIntent().getExtras();
        if (intent != null){
            idToken = intent.getString("idToken");
            name = intent.getString("name");
            email = intent.getString("email");
            photo = intent.getString("photo");
        }

        setViewPager();

    }

    private void setViewPager() {
        createProfilePagerAdapter = new CreateProfilePagerAdapter(this,name,email,photo,this);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        viewpagerTop.setClipChildren(false);
        viewpagerTop.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.dp6));
        viewpagerTop.setOffscreenPageLimit(4);
        viewpagerTop.setPageTransformer(false, new CarouselEffectTransformer(this)); // Set transformer
        viewpagerTop.setAdapter(createProfilePagerAdapter);


        viewpagerTop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int index = 0;

            @Override
            public void onPageSelected(int position) {
                index = position;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //int width = viewPagerBackground.getWidth();
                //viewPagerBackground.scrollTo((int) (width * position + width * positionOffset), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    //viewPagerBackground.setCurrentItem(index);
                }

            }
        });

        indicator.setViewPager(viewpagerTop);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(6 * density);
    }

    @Override
    public void userDetails(int position) {
        showToast("submit user details");
        viewpagerTop.setCurrentItem(viewpagerTop.getCurrentItem()+1);
    }

    @Override
    public void userStatus(String status, int position) {
        showToast(status);
    }

    @Override
    public void userFinalSubmit(int position) {
        showToast("begin user choice");
        Intent intent = new Intent(this, QuestionAnswerActivity.class);
        intent.putExtra("gender", gender);
        startActivity(intent);
        finish();
    }

    @Override
    public void nextUserStatus(int position) {
        showToast("submit user status");
        viewpagerTop.setCurrentItem(viewpagerTop.getCurrentItem()+1);
    }

    @Override
    public void nextuserGender(int position) {
        showToast("choose gender");
        viewpagerTop.setCurrentItem(viewpagerTop.getCurrentItem()+1);
    }

    @Override
    public void userGender(String gender, int position) {
        showToast(gender);
        this.gender = gender;
    }
}
