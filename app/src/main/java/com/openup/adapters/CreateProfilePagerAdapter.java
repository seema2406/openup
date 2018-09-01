package com.openup.adapters;


import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.openup.R;
import com.openup.models.CreateProfileModelObject;
import com.openup.utils.Constants;
import com.openup.widgets.OpenUpEditText;
import com.openup.widgets.OpenUpTextview;

public class CreateProfilePagerAdapter extends PagerAdapter implements View.OnClickListener {

    private Context mContext;
    private int ProfileCard = 0;
    private int StatusCard = 1;
    private int GenderCard = 2;
    private int FinalCard = 3;
    private ViewPagerClickListener listner;
    private String idToken, name, email, photo;
    private Uri photoUri;

//    @BindView(R.id.next_user_details)
    OpenUpTextview btnNextUserDetails;
//    @BindView(R.id.next_user_status)
    OpenUpTextview btnNextStatus;
//    @BindView(R.id.status_single)
    OpenUpTextview statusSingle;
//    @BindView(R.id.status_taken)
    OpenUpTextview statusTaken;
    //    @BindView(R.id.next_user_status)
    OpenUpTextview btnNextChooseGender;
    //    @BindView(R.id.status_single)
    OpenUpTextview genderMale;
    //    @BindView(R.id.status_taken)
    OpenUpTextview genderFemale;

    OpenUpEditText userName;
    CircularImageView profileImage;


    public CreateProfilePagerAdapter(Context context, String name, String email, String photo, ViewPagerClickListener listner) {
        mContext = context;
        this.listner = listner;
        this.name = name;
        this.email = email;
        this.photo = photo;
    }

    public interface ViewPagerClickListener{

        void userDetails(int position);

        void userStatus(String status, int position);

        void userFinalSubmit(int position);

        void nextUserStatus(int position);

        void nextuserGender(int position);

        void userGender(String female, int position);
    }
    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {
        CreateProfileModelObject modelObject = CreateProfileModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (modelObject.getTitleResId() == ProfileCard) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_user_details, collection, false);
            collection.addView(layout);
            //ButterKnife.bind(this,layout);
            userName = layout.findViewById(R.id.username);
            profileImage = layout.findViewById(R.id.profile_image);
            btnNextUserDetails = layout.findViewById(R.id.next_user_details);
            photoUri = Uri.parse(photo);
            btnNextUserDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.userDetails(position);
                }
            });
            userName.setText(name);
            Glide.with(mContext).load(photoUri).into(profileImage);
            return layout;
        }else if (modelObject.getTitleResId() == StatusCard) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_user_status, collection, false);
            collection.addView(layout);
            //ButterKnife.bind(this,layout);
            btnNextStatus = layout.findViewById(R.id.next_user_status);
            statusSingle = layout.findViewById(R.id.status_single);
            statusTaken = layout.findViewById(R.id.status_taken);
            btnNextStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.nextUserStatus(position);
                }
            });

            statusSingle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    statusSingle.setBackground(mContext.getResources().getDrawable(R.drawable.blue_border_round_bg));
                    statusSingle.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                    statusTaken.setBackground(mContext.getResources().getDrawable(R.drawable.gray_border_round));
                    statusTaken.setTextColor(mContext.getResources().getColor(R.color.dark_gray));
                    listner.userStatus("single",position);
                }
            });

            statusTaken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    statusSingle.setBackground(mContext.getResources().getDrawable(R.drawable.gray_border_round));
                    statusSingle.setTextColor(mContext.getResources().getColor(R.color.dark_gray));
                    statusTaken.setBackground(mContext.getResources().getDrawable(R.drawable.blue_border_round_bg));
                    statusTaken.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                    listner.userStatus("taken", position);
                }
            });
            //layout.findViewById(R.id.next_user_status).setTag(this);
            return layout;
        }else if (modelObject.getTitleResId() == GenderCard) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_choose_gender, collection, false);
            collection.addView(layout);
            btnNextChooseGender = layout.findViewById(R.id.next_choose_genger);
            genderMale = layout.findViewById(R.id.gender_male);
            genderFemale = layout.findViewById(R.id.gender_female);
            btnNextChooseGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.nextuserGender(position);
                }
            });

            genderMale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    genderMale.setBackground(mContext.getResources().getDrawable(R.drawable.blue_border_round_bg));
                    genderMale.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                    genderFemale.setBackground(mContext.getResources().getDrawable(R.drawable.gray_border_round));
                    genderFemale.setTextColor(mContext.getResources().getColor(R.color.dark_gray));
                    listner.userGender(Constants.MALE, position);
                }
            });

            genderFemale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    genderMale.setBackground(mContext.getResources().getDrawable(R.drawable.gray_border_round));
                    genderMale.setTextColor(mContext.getResources().getColor(R.color.dark_gray));
                    genderFemale.setBackground(mContext.getResources().getDrawable(R.drawable.blue_border_round_bg));
                    genderFemale.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                    listner.userGender(Constants.FEMALE, position);
                }
            });
            return layout;
        }else if (modelObject.getTitleResId() == FinalCard) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_user_profile_final, collection, false);
            collection.addView(layout);
            layout.findViewById(R.id.begin_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.userFinalSubmit(position);
                }
            });
            //layout.findViewById(R.id.begin_btn).setTag(this);
            return layout;
        }else{
            return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return CreateProfileModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return "";
    }

    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        /*switch (v.getId()){
            case R.id.next_user_details:
                listner.userDetails(position);
                break;
            case R.id.next_user_status:
                listner.userStatus(position);
                break;
            case R.id.next_choose_genger:
                listner.userGender(position);
                break;
            case R.id.begin_btn:
                listner.userFinalSubmit(position);
                break;
        }*/
    }
}
