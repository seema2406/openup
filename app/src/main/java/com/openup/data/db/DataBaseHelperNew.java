package com.openup.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Nilesh Deokar on 7/9/2015.
 */
public class DataBaseHelperNew extends SQLiteOpenHelper {

    public static final String TAG = "DataBaseHelperNew";
    // SQLiteDatabase database;
    // table names
    public static final String TABLE_USER = "User";
    public static final String TABLE_USER_NEW = "User_new";

    public static final String TABLE_TAGS = "tags";
    public static final String TABLE_RESOURCES = "resources";
    public static final String TABLE_RESOURCES_ADD_DELETE = "add_resources";
    public static final String TABLE_RESOURCES_ADD_DELETE_NEW = "user_resources";
    public static final String TABLE_BANK_ACCOUNTS = "bank_account";


    // Database Version
    private static final int DATABASE_VERSION = 13;
    // Database Name
    private static final String DATABASE_NAME = "mutten6m_andro";
    private static final String KEY_USER_ID = "user_id";

    //User Table Column names
    public static final String KEY_RES_NAME = "name";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBNO = "mobno";
    private static final String KEY_DOB = "dob";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_PROFILE_PIC = "profilepic";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_WORK_EXP = "workexp";
    private static final String KEY_EDUCATION = "education";
    private static final String KEY_CITY_NAME = "city_name";
    private static final String KEY_CITY_ID = "city_id";
    private static final String KEY_PINCODE = "pincode";
    private static final String KEY_GCM_ID = "gcm_id";

    //user_resource table
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_IS_MOB_VERIFIED = "mobverified";
    private static final String KEY_USER_RES_ID = "Res_id";


    //TAG table
    private static final String KEY_USER_RES_NAME = "Res_name";
    private static final String KEY_USER_RES_DESC = "Res_desc";
    private static final String KEY_USER_RES_IMAGE = "Res_image";
    private static final String KEY_TAG_NAME = "tagname";
    private static final String KEY_TAG_CAT_CODE = "tagcode"; //tag catogery code 0== Foodie 1== religion



    private static final String KEY_RES_CATEGORY = "category";
    private static final String KEY_RES_URL = "url";
    private static final String KEY_RES_DESC = "description";
    private static final String KEY_MF_USER_RES_ID = "mf_user_res_id";


    // Bank account table
    private static final String KEY_RES_STARTING_FROM_PRICE = "price";
    private static final String KEY_RES_AVG_PRICE = "avg_price"; // will be using as min prirce of resource
    private static final String KEY_RES_MIN_PRICE = "min_price";
    public static final String KEY_STATUS = "status";
    private static final String KEY_ACC_NAME = "accname";
    private static final String KEY_ACC_NUMB = "accnumb";
    private static final String KEY_ACC_IFSC = "accifsc";
    private static final String KEY_ACC_COMMENT = "acccomment";

    // User inventory table
    private static final String KEY_ID = "Id";
    public static final String KEY_USER_INVENTORY = "inventory";//use this for storing the invenotry separated by ,
    private static final String KEY_RESOURCE_ID = "res_id";
    private static final String KEY_WEEKLY_PRICE = "weekly";
    private static final String KEY_MONTHLY_PRICE = "monthly";
    private static final String KEY_IS_APPROVED = "is_approve";
    private static final String KEY_END_DATE = "not_available_till";
    private static final String KEY_START_DATE = "not_available_from";
    private static final String KEY_RES_RATE_AVG = "rating_id";
    private static final String KEY_RES_RATE_COUNT = "rating_count";
    private static final String KEY_IS_AVAILABLE = "is_available";
    private static final String KEY_MIN_PRICE = "min_price";
    private static final String KEY_AUTO_OFFER = "auto_offer";
    private static final String KEY_CHARGE_PER_DAY = "charge_per_day";
    private static final String KEY_DEPOSIT_AMT = "deposit";
    private static final String KEY_IS_NEGOTIABLE = "is_negotiable";


    private static DataBaseHelperNew sInstance;
    private AtomicInteger mOpenCounter = new AtomicInteger();
    private SQLiteDatabase mDatabase;

    protected DataBaseHelperNew(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DataBaseHelperNew getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DataBaseHelperNew(context.getApplicationContext());
        }
        return sInstance;
    }

    private synchronized SQLiteDatabase openWriteDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database

            mDatabase = sInstance.getWritableDatabase();
        }
//        Log.d("DB", "Database open counter: " + mOpenCounter.get() + " opening ");
        return mDatabase;
    }

    private synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();

        }
//        Log.d("DB", "Database open counter: " + mOpenCounter.get() + " closing ");
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {


        String TEXT_COMMA = " TEXT, ";
        String INTEGER_COMMA = " INTEGER, ";
        String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS ";

        /*
         *
         *  USER TABLE STARTS
         *
         */


        String CREATE_USER_TABLE = CREATE_TABLE + TABLE_USER_NEW + "("
                + KEY_USER_ID + INTEGER_COMMA
                + KEY_FNAME + TEXT_COMMA + KEY_LNAME + TEXT_COMMA
                + KEY_EMAIL + TEXT_COMMA + KEY_MOBNO + TEXT_COMMA
                + KEY_DOB + TEXT_COMMA + KEY_GENDER + TEXT_COMMA
                + KEY_PROFILE_PIC + TEXT_COMMA + KEY_TAGS + TEXT_COMMA
                + KEY_USER_INVENTORY + TEXT_COMMA
                + KEY_WORK_EXP + TEXT_COMMA + KEY_EDUCATION + TEXT_COMMA
                + KEY_CITY_NAME + TEXT_COMMA + KEY_CITY_ID + INTEGER_COMMA
                + KEY_PINCODE + TEXT_COMMA + KEY_GCM_ID + TEXT_COMMA
                + KEY_LATITUDE + " REAL, " + KEY_LONGITUDE + " REAL, "
                + KEY_IS_MOB_VERIFIED + " INTEGER " + ")";


        db.execSQL(CREATE_USER_TABLE);



       /*
        *
        *  TAGS TABLE STARTS
        *
        *   4-01-2016 @nilesh Added tags in the profile section for getting more data about users
        * */


        String CREATE_TABLE_TAGS = CREATE_TABLE + TABLE_TAGS + "("
                + KEY_ID + INTEGER_COMMA
                + KEY_TAG_CAT_CODE + INTEGER_COMMA + KEY_TAG_NAME + " TEXT " + ")";

        db.execSQL(CREATE_TABLE_TAGS);



        String CREATE_TABLE_RESOURCES_ADD_DELETE = CREATE_TABLE + TABLE_RESOURCES_ADD_DELETE_NEW + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USER_RES_ID + INTEGER_COMMA + KEY_MF_USER_RES_ID + INTEGER_COMMA + KEY_USER_RES_NAME + TEXT_COMMA
                + KEY_USER_RES_DESC + TEXT_COMMA + KEY_USER_RES_IMAGE + TEXT_COMMA
                + KEY_STATUS + INTEGER_COMMA + KEY_IS_NEGOTIABLE + INTEGER_COMMA
                + KEY_CHARGE_PER_DAY + INTEGER_COMMA + KEY_DEPOSIT_AMT + INTEGER_COMMA
                + KEY_AUTO_OFFER + INTEGER_COMMA + KEY_IS_AVAILABLE + INTEGER_COMMA + KEY_IS_APPROVED + INTEGER_COMMA
                + KEY_MIN_PRICE + INTEGER_COMMA + KEY_WEEKLY_PRICE + INTEGER_COMMA + KEY_MONTHLY_PRICE + INTEGER_COMMA
                + KEY_START_DATE + TEXT_COMMA + KEY_END_DATE + TEXT_COMMA
                + KEY_RES_RATE_AVG + " REAL, " + KEY_RES_RATE_COUNT + INTEGER_COMMA + " EXTRA " + " TEXT " +")";

        db.execSQL(CREATE_TABLE_RESOURCES_ADD_DELETE);


       /*
        *
        *  RESOURCES TABLE STARTS
        *
        *   01-04-2016 @nilesh Added Resources table
        *
        *   Sports & Outdoor	Appliances 	Furniture	Gaming ( Video games) 	Cooking     Music
        * */


        String CREATE_TABLE_RES = CREATE_TABLE + TABLE_RESOURCES + "("
                + KEY_ID + INTEGER_COMMA
                + KEY_RES_CATEGORY + INTEGER_COMMA + KEY_RES_NAME + TEXT_COMMA + KEY_RES_URL + TEXT_COMMA
                + KEY_RES_DESC + TEXT_COMMA + KEY_RES_STARTING_FROM_PRICE  + INTEGER_COMMA
                + KEY_RES_AVG_PRICE + INTEGER_COMMA + KEY_RES_MIN_PRICE + " INTEGER " + ")";

        db.execSQL(CREATE_TABLE_RES);


         /*
        *
        *  BANK ACCOUNTS TABLE STARTS
        *
        *   09-06-2016 @nilesh Added Bank account table for SHARER's NEFT
        *
        * */

        String CREATE_TABLE_BANK = CREATE_TABLE + TABLE_BANK_ACCOUNTS + "("
                + KEY_ID + INTEGER_COMMA + KEY_USER_ID + INTEGER_COMMA
                + KEY_ACC_NAME + TEXT_COMMA + KEY_ACC_NUMB + TEXT_COMMA + KEY_ACC_IFSC + TEXT_COMMA
                + KEY_ACC_COMMENT + " TEXT " + ")";

        db.execSQL(CREATE_TABLE_BANK);

    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {




        if (oldVersion == 9) {

            Cursor cursor2 = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + TABLE_RESOURCES_ADD_DELETE + "'", null);

            if (cursor2 != null && cursor2.getCount() == 1) {

                Log.i(TAG, "Db upgraded from 9 " + TABLE_RESOURCES_ADD_DELETE + " altered");


                //alterAddResourcesTable(db);


                cursor2.close();
            }

            ArrayList<String> mTablesToDropArrayList = new ArrayList<String>();

            mTablesToDropArrayList.add("orders");
            mTablesToDropArrayList.add("order_providers");
            mTablesToDropArrayList.add("global");

            dropTablesVer12(db,mTablesToDropArrayList);
        }

        if (oldVersion == 10) {

            //alterAddResourcesTable(db);

            db.execSQL("ALTER TABLE " + TABLE_RESOURCES + "  ADD COLUMN  " + KEY_RES_MIN_PRICE + " INTEGER");
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES + "  ADD COLUMN  " + KEY_RES_AVG_PRICE + " INTEGER");


            ArrayList<String> mTablesToDropArrayList = new ArrayList<String>();

            mTablesToDropArrayList.add("orders");
            mTablesToDropArrayList.add("order_providers");
            mTablesToDropArrayList.add("global");

            dropTablesVer12(db,mTablesToDropArrayList);
        }

        if (oldVersion == 11) {
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES_ADD_DELETE_NEW + "  ADD COLUMN  " + KEY_START_DATE + " TEXT ");
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES_ADD_DELETE_NEW + "  ADD COLUMN  " + KEY_RES_RATE_AVG + " REAL ");
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES_ADD_DELETE_NEW + "  ADD COLUMN  " + KEY_RES_RATE_COUNT + " INTEGER ");
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES_ADD_DELETE_NEW + "  ADD COLUMN  " + " EXTRA " + " TEXT ");

            db.execSQL("ALTER TABLE " + TABLE_RESOURCES + "  ADD COLUMN  " + KEY_RES_MIN_PRICE + " INTEGER");
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES + "  ADD COLUMN  " + KEY_RES_AVG_PRICE + " INTEGER");


            ArrayList<String> mTablesToDropArrayList = new ArrayList<String>();

            mTablesToDropArrayList.add("orders");
            mTablesToDropArrayList.add("order_providers");
            mTablesToDropArrayList.add("global");

            dropTablesVer12(db,mTablesToDropArrayList);
        }

        if(oldVersion == 12){
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES_ADD_DELETE_NEW + "  ADD COLUMN  " + KEY_RES_RATE_AVG + " REAL ");
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES_ADD_DELETE_NEW + "  ADD COLUMN  " + KEY_RES_RATE_COUNT + " INTEGER ");
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES_ADD_DELETE_NEW + "  ADD COLUMN  " + " EXTRA " + " TEXT ");

            db.execSQL("ALTER TABLE " + TABLE_RESOURCES + "  ADD COLUMN  " + KEY_RES_MIN_PRICE + " INTEGER");
            db.execSQL("ALTER TABLE " + TABLE_RESOURCES + "  ADD COLUMN  " + KEY_RES_AVG_PRICE + " INTEGER");



            ArrayList<String> mTablesToDropArrayList = new ArrayList<>();

            mTablesToDropArrayList.add("orders");
            mTablesToDropArrayList.add("order_providers");
            mTablesToDropArrayList.add("global");

            dropTablesVer12(db,mTablesToDropArrayList);
        }


        // Create tables again
        onCreate(db);
    }


    private void dropTablesVer12(SQLiteDatabase mDatabase,ArrayList<String> mList){

        for(String mTableName : mList){
            mDatabase.execSQL("DROP TABLE IF EXISTS " + mTableName);
        }
    }


    public void deleteTable(String query) {
        try {
            SQLiteDatabase db = openWriteDatabase();
            db.execSQL(query);
            closeDatabase();

        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

  /*  public void addTags(List<TagsModel> tagsModelList) {

        try {
            SQLiteDatabase db = openWriteDatabase();
            ContentValues values = new ContentValues();


            int size = tagsModelList.size();
            for (int i = 0; i < size; i++) {

                TagsModel tagsModel = tagsModelList.get(i);

                values.put(KEY_ID, tagsModel.tagID);//tag_id
                values.put(KEY_TAG_CAT_CODE, tagsModel.tagCatCode);//Catogery code 0=foodie 1 = religion
                values.put(KEY_TAG_NAME, tagsModel.tagName);// tag name

                // Inserting Row
                db.insert(TABLE_TAGS, null, values);


            }
//            Log.i(TAG, "Tags added");

            // Closing database connection
            closeDatabase();
        } catch (SQLiteException exception) {
            exception.printStackTrace();
        }

    }

    public List<TagsModel> getTags(int catogery) { //catogery 0=foodie , 1= religion
        SQLiteDatabase db = openWriteDatabase();
        //db.rawQuery("",new String []{});
        Cursor cursor;

        if (catogery == -1) { // we need to fetch all the tags available
            cursor = db.rawQuery("select * from " + TABLE_TAGS, null);

        } else {
            cursor = db.rawQuery("select * from " + TABLE_TAGS + " where " + KEY_TAG_CAT_CODE + " = " + catogery, null);

        }

        List<TagsModel> arrayList = new ArrayList<>();
        // looping through all rows and adding to list
        try {


            if (cursor.moveToFirst()) {
                do {
                    TagsModel or = new TagsModel();

                    or.tagID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                    or.tagCatCode = cursor.getInt(cursor.getColumnIndex(KEY_TAG_CAT_CODE));
                    or.tagName = cursor.getString(cursor.getColumnIndex(KEY_TAG_NAME));

                    arrayList.add(or);
                } while (cursor.moveToNext());

            }
            cursor.close();
            closeDatabase();

        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return arrayList;

    }

    public void upDateUserDetail(UserModel userModel) {
        try {
            SQLiteDatabase db = openWriteDatabase();
            ContentValues values = new ContentValues();


            values.put(KEY_CITY_NAME, userModel.mCity);//city
            values.put(KEY_PINCODE, userModel.mPin);//pincode

            values.put(KEY_CITY_ID, userModel.cityId);
            values.put(KEY_IS_MOB_VERIFIED, userModel.isMobVerified);
            values.put(KEY_MOBNO, userModel.mMobile);


            db.update(TABLE_USER_NEW, values, KEY_USER_ID + " = " + userModel.mUserId, null);
//            Log.i(TAG, "User details updated");
            // Closing database connection
            closeDatabase();
        } catch (SQLiteException exception) {
            exception.printStackTrace();
        }
    }

    public void upDateUserProfile(UserModel userModel) {
        try {
            SQLiteDatabase db = openWriteDatabase();
            ContentValues values = new ContentValues();


            values.put(KEY_GENDER, userModel.mGender);//gender
            values.put(KEY_MOBNO, userModel.mMobile);//pincode
            values.put(KEY_DOB, userModel.mBirthday);//building
            values.put(KEY_FNAME, userModel.mFname);//wing
            values.put(KEY_LNAME, userModel.mLname);//flat
            values.put(KEY_TAGS, userModel.mTags);//tags
            values.put(KEY_WORK_EXP, userModel.mWorkExp);//work
            values.put(KEY_EDUCATION, userModel.mEducation);//educationn
            values.put(KEY_IS_MOB_VERIFIED, userModel.isMobVerified);//educationn


            db.update(TABLE_USER_NEW, values, KEY_USER_ID + " = " + userModel.mUserId, null);
//            Log.i(TAG, "User profile updated");
            // Closing database connection
            closeDatabase();
        } catch (SQLiteException exception) {
            exception.printStackTrace();
        }
    }
*/

    public void executeString(String query) {
        try {
            SQLiteDatabase db = openWriteDatabase();
            db.execSQL(query);

            closeDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }


    public String getUserId() {

        String countQuery = "SELECT  " + KEY_USER_ID + " FROM " + TABLE_USER_NEW;
        SQLiteDatabase db = openWriteDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int userID = 0;
        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {

                    userID = cursor.getInt(0);

                } while (cursor.moveToNext());
            }
            cursor.close();
            closeDatabase();

        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        return null;
    }







}