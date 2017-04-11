package com.nathalieborodina.theatreapp.fragment;

import android.support.v4.app.Fragment;

import com.nathalieborodina.theatreapp.activity.BaseActivity;
import com.nathalieborodina.theatreapp.data.manager.DataManager;
import com.nathalieborodina.theatreapp.utils.FragmentTransactionManager;

/**
 * Created by nathalieborodina on 4/10/17.
 */

public abstract class BaseFragment extends Fragment {

    public abstract String getFragmentTag();

    public boolean onBackPressed() {
        return false;
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public DataManager getDataManager() {
        return getBaseActivity().getDataManager();
    }

    public FragmentTransactionManager getFragmentTransactionManager() {
        return getBaseActivity().getFragmentTransactionManager();
    }

}
