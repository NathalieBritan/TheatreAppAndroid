package com.nathalieborodina.theatreapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nathalieborodina.theatreapp.data.model.PerfomanceEvent;
import com.nathalieborodina.theatreapp.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by nathalieborodina on 4/11/17.
 */

public class StartActivity extends BaseActivity{

    protected BaseFragment mCurrentFragment;
    protected ArrayList<PerfomanceEvent> events;
    protected RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        events = new ArrayList<>(getDataManager().getmPerfomanceEventManager().getAllPerfomanceEvent());

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_events);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        mMoodAdapter = new TripMoodAdapter((TripContentMoodActivity) mTripContentDetailsActivity, moodList, mShowUploadOk);
    }

    @Override
    public int getContainerID() {
        return 0;
    }

    protected void setFragment(BaseFragment fragment) {
        mCurrentFragment = fragment;
        getFragmentTransactionManager().forceReplace(fragment, false);
    }
}
