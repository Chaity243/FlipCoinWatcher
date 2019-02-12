package com.smart.appworld.flip.activities.coindetails;

import com.smart.appworld.flip.model.CoinDetail;

public class CoinDetailPresenter implements CoinDetailContract.presenter, CoinDetailContract.Interactor.OnFinishedListener {

    private CoinDetailContract.MainView mainView;
    private CoinDetailContract.Interactor getCoinDetailInteractor;
    private String coinId;

    public CoinDetailPresenter(CoinDetailContract.MainView mainView, CoinDetailContract.Interactor getCoinDetailInteractor,String coinId) {
        this.mainView = mainView;
        this.getCoinDetailInteractor = getCoinDetailInteractor;
        this.coinId=coinId;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress(true);
        }
        getCoinDetailInteractor.getCoinDetial(coinId,this);

    }

    @Override
    public void requestDataFromServer() {
        getCoinDetailInteractor.getCoinDetial(coinId,this);
    }


    @Override
    public void onFinished(CoinDetail coinDetail) {
        if(mainView != null){
            mainView.setUIData(coinDetail);
            mainView.hideProgress();
        }
    }


    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
