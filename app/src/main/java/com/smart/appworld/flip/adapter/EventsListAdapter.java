package com.smart.appworld.flip.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart.appworld.flip.R;
import com.smart.appworld.flip.model.Event;

import java.util.List;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.CoinListViewHolder> {

    private List<Event>  eventList;
    private EventsListAdapter.EventsListItemClickListener recyclerItemClickListener;

    public EventsListAdapter(List<Event>  eventList , EventsListAdapter.EventsListItemClickListener recyclerItemClickListener) {
        this.eventList = eventList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @Override
    public EventsListAdapter.CoinListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.events_recycler_view, parent, false);
        return new EventsListAdapter.CoinListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsListAdapter.CoinListViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtNoticeTitle.setText(eventList.get(position).getStart_date());
        holder.txtNoticeBrief.setText(eventList.get(position).getTitle());
        holder.txtNoticeFilePath.setText(eventList.get(position).getEnd_date());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(eventList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class CoinListViewHolder extends RecyclerView.ViewHolder {

        TextView txtNoticeTitle, txtNoticeBrief, txtNoticeFilePath;

        CoinListViewHolder(View itemView) {
            super(itemView);
            txtNoticeTitle =  itemView.findViewById(R.id.tv_coin_id);
            txtNoticeBrief =  itemView.findViewById(R.id.tv_coin_symbol);
            txtNoticeFilePath =  itemView.findViewById(R.id.tv_coin_name);

        }
    }

    public static interface EventsListItemClickListener {
        void onItemClick(Event event);
    }
}