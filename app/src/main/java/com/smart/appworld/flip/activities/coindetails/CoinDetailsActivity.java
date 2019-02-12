package com.smart.appworld.flip.activities.coindetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.appworld.flip.R;
import com.smart.appworld.flip.model.CoinDetail;

public class CoinDetailsActivity extends AppCompatActivity  implements CoinDetailContract.MainView {

    private TextView coin_name,coin_symbol,market_cap_rank,coingecko_rank,coingecko_score, developer_score,community_score,liquidity_score, public_interest_score,last_updated;

    private ProgressBar progressBar;
    private Toolbar toolbar;

    private CoinDetailContract.MainView mainView;
    private CoinDetailContract.presenter presenter;
    private Context mContext;
    private String coinId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mContext=this;
        getIntentData();
    }

    private void getIntentData() {
        Intent i = getIntent();
        coinId =i.getStringExtra("coinID");
        intPresenter();

    }

    private void initViews() {
        coin_name=findViewById(R.id.coin_name);
        coin_symbol=findViewById(R.id.coin_symbol);
        market_cap_rank=findViewById(R.id.market_cap_rank);
        coingecko_rank=findViewById(R.id.coingecko_rank);
        coingecko_score=findViewById(R.id.coingecko_score);
        developer_score=findViewById(R.id.developer_score);
        community_score=findViewById(R.id.community_score);
        liquidity_score=findViewById(R.id.liquidity_score);
        public_interest_score=findViewById(R.id.public_interest_score);
        last_updated=findViewById(R.id.last_updated);

    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    private void intPresenter() {

        presenter = new CoinDetailPresenter(this, new CoinsDetailtractorImpl(),coinId);
        presenter.requestDataFromServer();
        initViews();

    }




    @Override
    public void showProgress(boolean refreshing) {

    }

    @Override
    public void hideProgress() {
        showProgress(false);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(mContext,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setUIData(CoinDetail coinDetail) {
        coin_name.setText(coinDetail.getName());
        coin_symbol.setText(coinDetail.getSymbol());
        market_cap_rank.setText(coinDetail.getMarket_cap_rank());
        market_cap_rank.setText(coinDetail.getMarket_cap_rank());
        coingecko_rank.setText(coinDetail.getCoingecko_rank());
        coingecko_score.setText(coinDetail.getCoingecko_score());
        developer_score.setText(coinDetail.getDeveloper_score());
        community_score.setText(coinDetail.getCommunity_score());
        liquidity_score.setText(coinDetail.getLiquidity_score());
        public_interest_score.setText(coinDetail.getPublic_interest_score());
        last_updated.setText(coinDetail.getLast_updated());




    }
}
