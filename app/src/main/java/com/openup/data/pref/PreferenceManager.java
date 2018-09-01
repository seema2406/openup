package com.openup.data.pref;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Nilesh Deokar on 03/09/15.
 */
public class PreferenceManager {

    public static final String USER_ID = "USER_ID";
    public static final String FIRST_NM = "fname";
    public static final String LAST_NM = "lname";
    public static final String USER_PROFILE_PIC = "u_prof_pic";
    public static final String GCM_ID = "gcm_id";
    //UsesCases
    public static final String AUTH = "auth";
    public static final String SELECTED_TAGS = "SELECTED_TAGS";
    public static final String USER_CITY = "user_city";
    public static final String USER_CITY_ID = "user_city_id";
    public static final String USER_LAT = "user_lat";
    public static final String USER_LONG = "user_long";
    public static final String USER_RESOURCES = "res_user";
    public static final String USER_RESOURCES_COUNT = "res_user_count";
    public static final String USER_CITY_UPDATED = "is_city_upd";// indicated wheather user city id is replaced with the pincode id
    public static final String USER_SHARED_PREF_CLEARED = "SHARED_PREF_CLEAR";//
    public static final String RESOURCES_STORED = "res_stored";

    public static final String VERSION_CODE = "VERSION_";

    public static final String WALKTHROUGH_SHOWN = "walkthrough";

    public static final String USER_LOCATION_TIME_STAMP = "loc_last_updated";
    public static final String RESOURCE_UPDATE_TIME_STAMP = "res_update_time";
    //public static final String BANNER_IMGS_JSON = "banner_js";
    //public static final String POPULAR_ITEMS = "popular_items";
    //public static final String REQUESTER_GUIDE_SHOWN = "req_guide_shown";
    public static final String SHARER_GUIDE_SHOWN = "share_guide_shown";
    public static final String USER_ADDRESS = "user_address";
    public static final String ACCESS_TOKEN = "access_token";



    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private String SHARED_PREFERENCES_FILE = "MutterflyAppPref";


    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveIntForKey(String key, int data) {

        editor.putInt(key, data);
        editor.commit();
    }

    public int getIntForKey(String key, int defaultData) {
        return sharedPreferences.getInt(key, defaultData);
    }

    public void removeIntForKey(String key) {
        editor.remove(key);
        editor.commit();
    }

    //Float operations
    public void saveFloatForKey(String key, int data) {
        editor.putInt(key, data);
        editor.commit();
    }

    public int getFloatForKey(Context context, String key, int defaultData) {
        SharedPreferences
            sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultData);
    }


    public void removeFloatForKey(String key) {
        editor.remove(key);
        editor.commit();
    }


    //String operations
    public void saveStringForKey(String key, String data) {
        editor.putString(key, data);
        editor.commit();
    }

    public String getStringForKey(String key, String defaultData) {

        return sharedPreferences.getString(key, defaultData);
    }


    public void saveDoubleForKey(String key, double data) {
        editor.putLong(key, Double.doubleToRawLongBits(data));
        editor.commit();
    }

    public double getDoubleForKey(String key, double defaultData) {

        return Double.longBitsToDouble(sharedPreferences.getLong(key, Double.doubleToLongBits(defaultData)));
    }

    public void removeStringForKey(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void SaveSelecteditemlist(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getSelectedItemlist(String key, String defaultData) {

        return sharedPreferences.getString(key, defaultData);
    }

    //boolean operations
    public void saveBooleanForKey(String key, boolean data) {
        editor.putBoolean(key, data);
        editor.commit();
    }

    public boolean getBooleanForKey(String key, boolean defaultData) {
        return sharedPreferences.getBoolean(key, defaultData);
    }

    public void removeBooleanForKey(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }


    public void clearPref() {
        editor.clear();
        editor.commit();
    }

    /*@Override public int getCurrentUserLoggedInMode() {
        return 0;
    }



    @Override public Long getCurrentUserId() {
        return null;
    }

    @Override public void setCurrentUserId(Long userId) {

    }

    @Override public String getCurrentUserName() {
        return sharedPreferences.getString(FIRST_NM, null);
    }

    @Override public void setCurrentUserName(String userName) {
         editor.putString(FIRST_NM,userName);
         editor.commit();
    }

    @Override public String getCurrentUserEmail() {
        return null;
    }

    @Override public void setCurrentUserEmail(String email) {

    }

    @Override public String getCurrentUserProfilePicUrl() {
        return sharedPreferences.getString(USER_PROFILE_PIC,"");
    }

    @Override public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        editor.putString(USER_PROFILE_PIC,profilePicUrl);
        editor.commit();
    }

    @Override public String getAccessToken() {
        return null;
    }

    @Override public void setAccessToken(String accessToken) {

    }*/
}

