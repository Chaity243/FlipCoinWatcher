package com.smart.appworld.flip.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.smart.appworld.flip.R;
import com.smart.appworld.flip.model.Coin;
import com.smart.appworld.flip.sqliteDB.DatabaseOpenHelper;

import java.util.List;

public class CoinsListAdapter extends RecyclerView.Adapter<CoinsListAdapter.CoinListViewHolder> {

    private List<Coin> dataList;
    private CoinsListItemClickListener recyclerItemClickListener;
    private  DatabaseOpenHelper databaseOpenHelper;


    public CoinsListAdapter(List<Coin> dataList , CoinsListItemClickListener recyclerItemClickListener, DatabaseOpenHelper databaseOpenHelper) {
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
        this.databaseOpenHelper=databaseOpenHelper;
    }


    @Override
    public CoinListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.coins_recycler_view, parent, false);
        return new CoinListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoinListViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tv_coin_id.setText(dataList.get(position).getId());
        holder.tv_coin_symbol.setText(dataList.get(position).getSymbol());
        holder.tv_coin_name.setText(dataList.get(position).getName());
        if(databaseOpenHelper.hasObject(dataList.get(position).getId()))
        {
            holder.btn_add.setText("REMOVE");
        }
        else {
            holder.btn_add.setText("ADD");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(dataList.get(position));
            }
        });
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseOpenHelper.hasObject(dataList.get(position).getId()))
                {
                    recyclerItemClickListener.onRemoveButtonClick(dataList.get(position));
                }
                else {
                    recyclerItemClickListener.onAddButtonClick(dataList.get(position));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CoinListViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_coin_id, tv_coin_symbol, tv_coin_name;
        private Button btn_add;

        CoinListViewHolder(View itemView) {
            super(itemView);
            tv_coin_id =  itemView.findViewById(R.id.tv_coin_id);
            tv_coin_symbol =  itemView.findViewById(R.id.tv_coin_symbol);
            tv_coin_name =  itemView.findViewById(R.id.tv_coin_name);
            btn_add=itemView.findViewById(R.id.btn_add);

        }
    }

    public static interface CoinsListItemClickListener {
        void onItemClick(Coin coin);
        void onAddButtonClick(Coin coin);
        void onRemoveButtonClick(Coin coin);
    }
}