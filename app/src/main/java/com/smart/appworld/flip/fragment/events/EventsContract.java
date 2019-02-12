package com.smart.appworld.flip.fragment.events;

import com.smart.appworld.flip.fragment.BaseInteractor;
import com.smart.appworld.flip.fragment.BasePresenter;
import com.smart.appworld.flip.fragment.BaseView;
import com.smart.appworld.flip.model.Event;

import java.util.List;

public interface EventsContract {


    interface presenter extends BasePresenter {



    }

    /**
     * setDataToRecyclerView  is fetched from the GetNoticeInteractorImpl class
     **/
    interface MainView extends BaseView {

        void setDataToRecyclerView(List<Event> eventList);
    }


    interface Interactor extends BaseInteractor {

        interface OnFinishedListener {
            void onFinished(List<Event> eventList);
            void onFailure(Throwable t);
        }

        void getEventsList(OnFinishedListener onFinishedListener);
    }
}
