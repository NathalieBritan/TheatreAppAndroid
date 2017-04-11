package com.nathalieborodina.theatreapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nathalieborodina.theatreapp.data.model.PerfomanceEvent;

import java.util.ArrayList;

/**
 * Created by nathalieborodina on 4/11/17.
 */

public class TicketDetailsFragment extends BaseFragment {

    protected ArrayList<PerfomanceEvent> events;
    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ticket_details, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.tripContentRecyclerView);
        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        events = new ArrayList<>(getDataManager().getmPerfomanceEventManager().getAllPerfomanceEvent());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    public void notifyDataChanged() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

}
