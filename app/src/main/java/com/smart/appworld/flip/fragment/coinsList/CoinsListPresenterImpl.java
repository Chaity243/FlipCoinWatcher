package com.smart.appworld.flip.fragment.coinsList;

import com.smart.appworld.flip.model.Coin;

import java.util.List;



public class CoinsListPresenterImpl implements CoinsListContract.presenter, CoinsListContract.Interactor.OnFinishedListener {

    private CoinsListContract.MainView mainView;
    private CoinsListContract.Interactor getCoinsInteractor;

    public CoinsListPresenterImpl(CoinsListContract.MainView mainView, CoinsListContract.Interactor getCoinsInteractor) {
        this.mainView = mainView;
        this.getCoinsInteractor = getCoinsInteractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {
        getCoinsInteractor.getCoinsList(this);

    }

    @Override
    public void requestDataFromServer() {
        if(mainView != null){
            mainView.showProgress(true);
        }
        getCoinsInteractor.getCoinsList(this);
    }


    @Override
    public void onFinished(List<Coin> coinArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(coinArrayList);
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
