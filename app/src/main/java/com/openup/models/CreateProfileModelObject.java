package com.openup.models;

import com.openup.R;

/**
 * Created by anupamchugh on 26/12/15.
 */
public enum CreateProfileModelObject {

    DETAIL(0, R.layout.item_user_details),
    STATUS(1, R.layout.item_user_status),
    GENDER(2, R.layout.item_choose_gender),
    FINAL(3, R.layout.item_user_profile_final);

    private int mTitleResId;
    private int mLayoutResId;

    CreateProfileModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
