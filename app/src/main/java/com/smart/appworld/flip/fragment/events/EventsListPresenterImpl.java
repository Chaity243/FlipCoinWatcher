package com.smart.appworld.flip.fragment.events;

import com.smart.appworld.flip.model.Event;

import java.util.List;


public class EventsListPresenterImpl implements EventsContract.presenter, EventsContract.Interactor.OnFinishedListener {

    private EventsContract.MainView mainView;
    private EventsContract.Interactor getEventsInteractor;

    public EventsListPresenterImpl(EventsContract.MainView mainView, EventsContract.Interactor getEventsInteractor) {
        this.mainView = mainView;
        this.getEventsInteractor = getEventsInteractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {
        getEventsInteractor.getEventsList(this);
        }

    @Override
    public void requestDataFromServer() {
        if(mainView != null){
            mainView.showProgress(true);
        }
        getEventsInteractor.getEventsList(this);
    }





    @Override
    public void onFinished(List<Event> eventsList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(eventsList);
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
