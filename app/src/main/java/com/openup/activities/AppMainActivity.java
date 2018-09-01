package com.openup.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.openup.R;
import com.openup.base.BaseActivity;
import com.openup.fragments.BashFragment;
import com.openup.fragments.HomeFragment;
import com.openup.fragments.NotificationFragment;
import com.openup.fragments.SearchFragment;
import com.openup.fragments.SingleProfileFragment;
import com.openup.utils.BottomNavigationViewHelper;

import butterknife.ButterKnife;

public class AppMainActivity extends BaseActivity implements SpaceOnClickListener {

    SpaceNavigationView spaceNavigationView;

  /*  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            for (int i = 0; i< bottomNavigation.getMenu().size(); i++) {
                MenuItem menuItem = bottomNavigation.getMenu().getItem(i);
                if (i != 2){
                    menuItem.setChecked(false);
                }else {
                    menuItem.getIcon().clearColorFilter();
                }
            }
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    item.setChecked(true);
                    showMessage(getString(R.string.title_home));
                    return true;
                case R.id.navigation_bash:
                    item.getIcon().clearColorFilter();
                    item.setIcon(getResources().getDrawable(R.mipmap.bash_icon));
                    showMessage(getString(R.string.title_bash));
                    return true;
                case R.id.navigation_notifications:
                    item.setChecked(true);
                    showMessage(getString(R.string.title_notifications));
                    return true;
                case R.id.navigation_search:
                    item.setChecked(true);
                    showMessage(getString(R.string.title_search));
                    return true;
                case R.id.navigation_profile:
                    item.setChecked(true);
                    showMessage(getString(R.string.title_profile));
                    return true;

            }
            return false;
        }
    };*/

    private void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);

        ButterKnife.bind(this);

        spaceNavigationView = findViewById(R.id.bottom_navigation);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem(getString(R.string.title_home), R.mipmap.home_icon));
        spaceNavigationView.addSpaceItem(new SpaceItem(getString(R.string.title_notifications), R.mipmap.notification));
        spaceNavigationView.addSpaceItem(new SpaceItem(getString(R.string.title_search), R.mipmap.search_icon));
        spaceNavigationView.addSpaceItem(new SpaceItem(getString(R.string.title_profile), R.mipmap.account_icon));
        spaceNavigationView.setCentreButtonColor(getResources().getColor(android.R.color.white));
        spaceNavigationView.showIconOnly();

        spaceNavigationView.setSpaceOnClickListener(this);
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }

    @Override
    public void onCentreButtonClick() {
        showToast("onCentreButtonClick");
        Fragment fragment = new BashFragment();
        replaceFragment(fragment);
    }

    @Override
    public void onItemClick(int itemIndex, String itemName) {
        //showToast(itemIndex + " " + itemName);
        Fragment fragment = null;
        switch (itemIndex){
            case 0:
                showMessage(getString(R.string.title_home));
                fragment = new HomeFragment();
                replaceFragment(fragment);
                break;
            case 1:
                showMessage(getString(R.string.title_notifications));
                fragment = new NotificationFragment();
                replaceFragment(fragment);
                break;
            case 2:
                showMessage(getString(R.string.title_search));
                fragment = new SearchFragment();
                replaceFragment(fragment);
                break;
            case 3:
                showMessage(getString(R.string.title_profile));
                fragment = new SingleProfileFragment();
                replaceFragment(fragment);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemReselected(int itemIndex, String itemName) {
        showToast("reselect : "+itemIndex + " " + itemName);
    }


    private void replaceFragment(Fragment frag) {
        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, frag, frag.getTag());
            ft.commit();
        }
    }
}
