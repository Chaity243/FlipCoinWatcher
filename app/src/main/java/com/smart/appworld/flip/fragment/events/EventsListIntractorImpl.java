package com.smart.appworld.flip.fragment.events;

import android.util.Log;

import com.smart.appworld.flip.model.EventResponse;
import com.smart.appworld.flip.my_interface.GetDataService;
import com.smart.appworld.flip.network.RetrofitInstance;
import com.smart.appworld.flip.utility.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventsListIntractorImpl implements EventsContract.Interactor {

    @Override
    public void getEventsList(final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get the  data*/
        Call<EventResponse> call = service.getEventsData(Utils.getTodaysDate(),  Utils.getXdaysAfterDate(5));

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
             Log.d("getEventsList", response.body().toString());
                onFinishedListener. onFinished(response.body().getData());

            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.d("getEventsList: Failure,", t.getMessage());
                onFinishedListener.onFailure(t);
            }
        });

    }

}
