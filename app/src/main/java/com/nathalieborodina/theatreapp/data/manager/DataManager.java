package com.nathalieborodina.theatreapp.data.manager;



import android.database.sqlite.SQLiteDatabase;

import com.nathalieborodina.theatreapp.Constants;
import com.nathalieborodina.theatreapp.activity.BaseActivity;
import com.nathalieborodina.theatreapp.data.model.DaoMaster;
import com.nathalieborodina.theatreapp.data.model.DaoSession;

/**
 * Created by nathalieborodina on 4/10/17.
 */

public class DataManager {
    private final DaoMaster mDaoMaster;
    private final DaoSession mDaoSession;

    private BaseActivity mBaseActivity;
    private UserManager mUserManager;
    private PerfomanceEventManager mPerfomanceEventManager;

    public DataManager(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(baseActivity, Constants.TRIPTALK_DB, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

    }

    DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    DaoSession getDaoSession() {
        return mDaoSession;
    }

    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    public UserManager getUserManager() {

        if (mUserManager == null)
            mUserManager = new UserManager(this);

        return mUserManager;
    }

    public PerfomanceEventManager getTripManager() {

        if (mPerfomanceEventManager == null)
            mPerfomanceEventManager = new PerfomanceEventManager(this);

        return mPerfomanceEventManager;
    }

}
