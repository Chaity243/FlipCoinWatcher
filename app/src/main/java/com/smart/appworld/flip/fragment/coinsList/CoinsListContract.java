package com.smart.appworld.flip.fragment.coinsList;

import com.smart.appworld.flip.fragment.BaseInteractor;
import com.smart.appworld.flip.fragment.BasePresenter;
import com.smart.appworld.flip.fragment.BaseView;
import com.smart.appworld.flip.model.Coin;

import java.util.List;


public interface CoinsListContract {


    interface presenter extends BasePresenter {



    }

    /**
     * setDataToRecyclerView  is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView extends BaseView {

        void setDataToRecyclerView(List<Coin> coinArrayList);
    }


    interface Interactor extends BaseInteractor {
        interface OnFinishedListener {
            void onFinished(List<Coin> coinArrayList);
            void onFailure(Throwable t);
        }

        void getCoinsList(OnFinishedListener onFinishedListener);

    }
}
