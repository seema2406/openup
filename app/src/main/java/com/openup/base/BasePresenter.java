package com.openup.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;

import com.openup.data.db.DataBaseHelperNew;
import com.openup.data.pref.PreferenceManager;
import com.openup.models.UserModel;


/**
 * Created by Niel on 18/07/17.
 */

public class BasePresenter<V extends IBaseView> {

    protected V mView;

    protected Context mContext;

    protected DataBaseHelperNew mDbHelper;

    protected PreferenceManager mPreferenceManager;

    public BasePresenter(Context context) {
        mContext = context;
        mDbHelper = DataBaseHelperNew.getInstance(context);
        mPreferenceManager = new PreferenceManager(context);

        //mAppDataManager = new AppDataManager();
    }

    public boolean isViewAttached() {
        return mView != null;
    }
    public void onCreate(){}

    public void attachView(V view){
        this.mView = view;
    }

    public void onDestroy(){
        this.mView = null;
    }

    protected Context getContext(){
        return mContext;
    }

    public void startSoundAnim(View view){
        addScaleAnim(view,1000,null);
    }

    public void startFavoriteAnim(View view,AnimationEndListener listener){
        addScaleAnim(view,500,listener);
    }

    private void addScaleAnim(View view, long duration, final AnimationEndListener listener) {
        ObjectAnimator animY = ObjectAnimator.ofFloat(view, "scaleY", 1f,0.5f, 1f, 1.2f,1f);
        ObjectAnimator animX = ObjectAnimator.ofFloat(view, "scaleX", 1f,0.5f, 1f, 1.2f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animX,animY);
        animatorSet.setDuration(duration);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(listener != null){
                    listener.onAnimationEnd(animation);
                }
            }
        });
        animatorSet.start();
    }

    public interface AnimationEndListener{
        void onAnimationEnd(Animator animation);
    }

    public String getUserId(){
        return mPreferenceManager.getStringForKey(PreferenceManager.USER_ID,"");
    }

    public UserModel getUserModel(){
        return mDbHelper.getUserModel();
    }

}
