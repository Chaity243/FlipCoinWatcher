package com.smart.appworld.flip.fragment.coinwatcher;

import com.google.gson.JsonElement;
import com.smart.appworld.flip.fragment.BaseInteractor;
import com.smart.appworld.flip.fragment.BasePresenter;
import com.smart.appworld.flip.fragment.BaseView;

public interface CoinWatcherContract {


    interface presenter extends BasePresenter {



    }

    /**
     * setDataToRecyclerView  is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView extends BaseView {
        void setDataToRecyclerView(JsonElement coinWatcherJSONObject);
    }


    interface Interactor extends BaseInteractor {
        interface OnFinishedListener {
            void onFinished(JsonElement coinWatcherJSON);
            void onFailure(Throwable t);
        }

        void getCoinWatcherList(String ids,OnFinishedListener onFinishedListener);

    }
}
