package com.nathalieborodina.theatreapp.data.manager;

import android.content.SharedPreferences;
import android.util.Log;

import com.nathalieborodina.theatreapp.BuildConfig;
import com.nathalieborodina.theatreapp.Constants;

/**
 * Created by nathalieborodina on 4/10/17.
 */

public class UserManager {
    private String mEmail;
    private String mPassword;

    private String mUserId;
    private String mApiKey;

    private DataManager mDataManager;


    public UserManager(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void checkUserLogged() {

        if (BuildConfig.LOG_ENABLED)
            Log.d(Constants.LOG_TAG, "BaseActivity.checkUserLogged");

        SharedPreferences sharedPreferences = mDataManager.getBaseActivity().getSharedPreferences();
        if (sharedPreferences.getBoolean(Constants.Preferences.USER_LOGGED, false)) {

            mEmail = sharedPreferences.getString(Constants.Preferences.USER_EMAIL, null);
            mPassword = sharedPreferences.getString(Constants.Preferences.USER_PASSWORD, null);
            mUserId = sharedPreferences.getString(Constants.Preferences.USER_ID, "");
            mApiKey = sharedPreferences.getString(Constants.Preferences.API_KEY, "");

        }
    }

    public void registerLogin(String email, String password) {

        SharedPreferences.Editor editor = mDataManager.getBaseActivity().getSharedPreferences().edit();

        editor.putBoolean(Constants.Preferences.USER_LOGGED, true);
        editor.putString(Constants.Preferences.USER_EMAIL, email);
        editor.putString(Constants.Preferences.USER_PASSWORD, password);

        editor.commit();

        mEmail = email;
        mPassword = password;

    }

    public void registerLogout() {

        SharedPreferences.Editor editor = mDataManager.getBaseActivity().getSharedPreferences().edit();
        editor.putBoolean(Constants.Preferences.USER_LOGGED, false);
        editor.putString(Constants.Preferences.USER_EMAIL, null);
        editor.putString(Constants.Preferences.USER_PASSWORD, null);

        editor.commit();

        mDataManager.getDaoSession().getTicketDao().deleteAll();
        mDataManager.getDaoSession().getPerfomanceEventDao().deleteAll();

        mDataManager.getDaoSession().clear();

        mEmail = null;
        mPassword = null;

    }

    public boolean isLogged() {
        return mEmail != null;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }

}
