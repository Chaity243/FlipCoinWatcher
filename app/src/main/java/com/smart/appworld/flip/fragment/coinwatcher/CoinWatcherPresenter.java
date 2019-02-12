package com.smart.appworld.flip.fragment.coinwatcher;

import com.google.gson.JsonElement;

public class CoinWatcherPresenter implements CoinWatcherContract.presenter, CoinWatcherContract.Interactor.OnFinishedListener {

    private CoinWatcherContract.MainView mainView;
    private CoinWatcherContract.Interactor getCoinWatcherInteractor;
    private String ids;

    public CoinWatcherPresenter(CoinWatcherContract.MainView mainView, CoinWatcherContract.Interactor getCoinWatcherInteractor,String ids) {
        this.mainView = mainView;
        this.getCoinWatcherInteractor = getCoinWatcherInteractor;
        this.ids= ids;
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
        getCoinWatcherInteractor.getCoinWatcherList(ids,this);

    }

    @Override
    public void requestDataFromServer() {
        if(mainView != null){
            mainView.showProgress(true);
        }
        getCoinWatcherInteractor.getCoinWatcherList(ids,this);
    }


    @Override
    public void onFinished(JsonElement coinWatcherJSONObject) {
        if(mainView != null){
            mainView.setDataToRecyclerView(coinWatcherJSONObject);
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
