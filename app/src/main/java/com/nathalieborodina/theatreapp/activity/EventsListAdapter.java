package com.nathalieborodina.theatreapp.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nathalieborodina.theatreapp.data.model.PerfomanceEvent;
import com.nathalieborodina.theatreapp.fragment.TicketDetailsFragment;

import java.util.List;

/**
 * Created by nathalieborodina on 4/11/17.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private StartActivity mActivity;
    private List<PerfomanceEvent> mEventsList;

    public EventsListAdapter(StartActivity activity, List<PerfomanceEvent> events) {
        mActivity = activity;
        mEventsList = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trip_mood, parent, false);;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final PerfomanceEvent event = mEventsList.get(position);
        holder.title.setText(event.getTitle());
        holder.time.setText(event.getDate_time().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.getFragmentTransactionManager().replace(new TicketDetailsFragment());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mEventsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            time = (TextView) itemView.findViewById(R.id.txt_time);
        }

    }
}
