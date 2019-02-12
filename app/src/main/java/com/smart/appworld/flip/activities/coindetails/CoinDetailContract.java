package com.smart.appworld.flip.activities.coindetails;

import com.smart.appworld.flip.fragment.BaseInteractor;
import com.smart.appworld.flip.fragment.BasePresenter;
import com.smart.appworld.flip.fragment.BaseView;
import com.smart.appworld.flip.model.CoinDetail;

public class CoinDetailContract {


    interface presenter extends BasePresenter {
    }


    interface MainView extends BaseView {
        void setUIData(CoinDetail coinDetail);
    }


    interface Interactor extends BaseInteractor {
        interface OnFinishedListener {
            void onFinished(CoinDetail coinDetail);
            void onFailure(Throwable t);
        }

        void getCoinDetial(String coinId,OnFinishedListener onFinishedListener);

    }
}
