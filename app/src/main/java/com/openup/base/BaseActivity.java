package com.openup.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import com.openup.data.pref.PreferenceManager;

import com.openup.R;


public class BaseActivity extends AppCompatActivity {

    public ProgressDialog pd;
    public PreferenceManager pf;
    public Toolbar toolbar;


    public void showProgress(String msg)
    {
        if (pd != null && pd.isShowing()) {
            dismissProgress();
        } else {
            pd = getProgressDialog(this, msg);
            pd.show();
        }
    }

    public static ProgressDialog getProgressDialog(Context mCon, String Msg) {

        ProgressDialog pd = new ProgressDialog(mCon);

        pd.setCancelable(false);
        pd.setMessage(Msg);

        return pd;

    }

    public void dismissProgress() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    public void showSnackBar(View view, String msg){

        Snackbar snackbar = Snackbar
                .make(view,msg, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // this listener will be called when there is change in firebase user session
    @Override
    public void onBackPressed() {
        return;
    }


    public boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() ==
                NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED ) {
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() ==
                        NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                NetworkInfo.State.DISCONNECTED  ) {
            return false;
        }
        return false;
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        try {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setupToolbar();
            pf = new PreferenceManager(this);
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    protected void setupBackButton() {
        if (toolbar != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setIcon(android.R.color.transparent);


        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackbar.show();
    }






    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
