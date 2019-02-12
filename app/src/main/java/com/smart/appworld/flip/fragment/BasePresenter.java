package com.smart.appworld.flip.fragment;

public interface BasePresenter {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    void onDestroy();

    void onRefreshButtonClick();

    void requestDataFromServer();

}
