package com.nathalieborodina.theatreapp.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.nathalieborodina.theatreapp.BuildConfig;
import com.nathalieborodina.theatreapp.Constants;
import com.nathalieborodina.theatreapp.data.manager.DataManager;
import com.nathalieborodina.theatreapp.fragment.BaseFragment;
import com.nathalieborodina.theatreapp.utils.FragmentTransactionManager;


/**
 * Created by nathalieborodina on 4/10/17.
 */

public class BaseActivity extends AppCompatActivity {

    public int mContainerID;

    private FragmentTransactionManager mFragmentTransactionManager;
    protected SharedPreferences mSharedPreferences;

    protected DataManager mDataManager;


    public BaseActivity() {

        mContainerID = getContainerID();

        mFragmentTransactionManager = new FragmentTransactionManager(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

        mDataManager = new DataManager(this);

        mDataManager.getUserManager().checkUserLogged();

    }



    @Override
    public void finish() {
        super.finish();
    }


    public FragmentTransactionManager getFragmentTransactionManager() {
        return mFragmentTransactionManager;
    }

    public SharedPreferences getSharedPreferences() {
        if (mSharedPreferences == null)
            mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences;
    }

    public SharedPreferences.Editor getSharedPreferencesEditor() {
        if (mSharedPreferences == null)
            mSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.edit();
    }

    @Override
    public void onBackPressed() {

        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) getSupportFragmentManager().findFragmentById(mContainerID);
        } catch (Exception e) {
            try {
                super.onBackPressed();
            } catch (IllegalStateException ignored) {
                // There's no way to avoid getting this if saveInstanceState has already been called.
            }
        }

        if (fragment != null && !fragment.onBackPressed()) {

            closeKeyboard();
            super.onBackPressed();

        } else if (fragment == null) {
            try {
                super.onBackPressed();
            } catch (Exception e) {

            }
        }

    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (BuildConfig.LOG_ENABLED)
            Log.d(Constants.LOG_TAG, "BaseActivity.onActivityResult - requestCode: " + requestCode);

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume() {

        super.onResume();

        if (BuildConfig.LOG_ENABLED)
            Log.d(Constants.LOG_TAG, "BaseActivity.onResume");

        mDataManager.getUserManager().checkUserLogged();
    }

    @Override
    protected void onPause() {

        if (BuildConfig.LOG_ENABLED)
            Log.d(Constants.LOG_TAG, "BaseActivity.onPause");

        super.onPause();
    }

    public DataManager getDataManager() {

        if (mDataManager == null)
            mDataManager = new DataManager(this);

        return mDataManager;
    }

    public int getContainerID() {
        return 0;
    }

    public void showToast(int msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_LONG).show();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}