package com.smart.appworld.flip.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.smart.appworld.flip.R;
import com.smart.appworld.flip.model.Coin;
import com.smart.appworld.flip.sqliteDB.DatabaseOpenHelper;

import java.util.List;

public class CoinWatcherAdapter extends RecyclerView.Adapter<CoinWatcherAdapter.CoinListViewHolder> {

    private List<Coin> dataList;
    private  JsonObject coinWatcherJSONObject;
    private CoinWatcherAdapter.CoinsListItemClickListener recyclerItemClickListener;
    private DatabaseOpenHelper databaseOpenHelper;


    public CoinWatcherAdapter(List<Coin> dataList , JsonObject coinWatcherJSONObject, CoinWatcherAdapter.CoinsListItemClickListener recyclerItemClickListener,DatabaseOpenHelper databaseOpenHelper) {
        this.dataList = dataList;
        this.coinWatcherJSONObject = coinWatcherJSONObject;
        this.recyclerItemClickListener = recyclerItemClickListener;
        this.databaseOpenHelper=databaseOpenHelper;
    }




    @Override
    public CoinWatcherAdapter.CoinListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.coins_watcher_recycler_view, parent, false);
        return new CoinWatcherAdapter.CoinListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoinWatcherAdapter.CoinListViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        /*   holder.tv_coin_id.setText(dataList.get(position).getId());*/
        holder.tv_coin_symbol.setText(dataList.get(position).getSymbol());
        holder.tv_coin_name.setText(dataList.get(position).getName());

        String usd= coinWatcherJSONObject.get(dataList.get(position).getId()).getAsJsonObject().getAsJsonPrimitive("usd").toString();
        String inr= coinWatcherJSONObject.get(dataList.get(position).getId()).getAsJsonObject().getAsJsonPrimitive("inr").toString();
        holder.tv_coin_price_usd.setText(usd);
        holder.tv_coin_price_inr.setText(inr);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CoinListViewHolder extends RecyclerView.ViewHolder {

        TextView  tv_coin_symbol, tv_coin_name,tv_coin_price_usd,tv_coin_price_inr;

        CoinListViewHolder(View itemView) {
            super(itemView);
            tv_coin_symbol =  itemView.findViewById(R.id.tv_coin_symbol);
            tv_coin_name =  itemView.findViewById(R.id.tv_coin_name);
            tv_coin_price_usd =  itemView.findViewById(R.id.tv_coin_price_usd);
            tv_coin_price_inr =  itemView.findViewById(R.id.tv_coin_price_inr);

        }
    }

    public static interface CoinsListItemClickListener {
        void onItemClick(Coin coinWatcher);
    }
}