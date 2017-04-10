package com.nathalieborodina.theatreapp.utils;

import android.support.v4.app.FragmentTransaction;

import com.nathalieborodina.theatreapp.activity.BaseActivity;
import com.nathalieborodina.theatreapp.fragment.BaseFragment;

/**
 * Created by nathalieborodina on 4/10/17.
 */

public class FragmentTransactionManager {

    private BaseActivity mBaseActivity;

    public FragmentTransactionManager(BaseActivity baseActivity) {
        this.mBaseActivity = baseActivity;
    }

    public void replace(BaseFragment fragment, boolean addToBackStack) {

        if (mBaseActivity == null)
            return;

        if (!isFragmentVisible(fragment.getFragmentTag())) {

            FragmentTransaction ft = beginTransaction();

            ft.replace(mBaseActivity.getContainerID(), fragment, fragment.getFragmentTag());

            if (addToBackStack)
                ft.addToBackStack(null);

            ft.commitAllowingStateLoss();
        }
    }

    public void replace(BaseFragment fragment) {
        replace(fragment, true);
    }

    public void forceReplace(BaseFragment fragment, boolean addToBackStack) {

        if (mBaseActivity == null)
            return;

        FragmentTransaction ft = beginTransaction();

        ft.replace(mBaseActivity.getContainerID(), fragment, fragment.getFragmentTag());

        if (addToBackStack)
            ft.addToBackStack(null);

        ft.commitAllowingStateLoss();
    }

    public void add(BaseFragment fragment, boolean addToBackStack) {

        if (mBaseActivity == null)
            return;

        if (!isFragmentVisible(fragment.getFragmentTag())) {

            FragmentTransaction ft = beginTransaction();

            ft.add(mBaseActivity.getContainerID(), fragment, fragment.getFragmentTag());

            if (addToBackStack)
                ft.addToBackStack(null);

            ft.commitAllowingStateLoss();
        }
    }

    public void add(BaseFragment fragment) {
        add(fragment, true);
    }

    private FragmentTransaction beginTransaction() {
        return mBaseActivity.getSupportFragmentManager().beginTransaction();
    }

    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    public boolean isFragmentVisible(String tagName)
    {
        BaseFragment fragment = (BaseFragment) mBaseActivity.getSupportFragmentManager().findFragmentByTag(tagName);
        return fragment != null && fragment.isVisible();
    }

}