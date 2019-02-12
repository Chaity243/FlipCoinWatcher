package com.smart.appworld.flip.fragment.coinwatcher;

import android.util.Log;

import com.google.gson.JsonElement;
import com.smart.appworld.flip.my_interface.GetDataService;
import com.smart.appworld.flip.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinWatcherIntractorImpl implements CoinWatcherContract.Interactor {
String ids;
    @Override
    public void getCoinWatcherList(String ids,final OnFinishedListener onFinishedListener) {
this.ids=ids;

        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get the  data*/
        Call<JsonElement> call = service.getPrices(ids,"usd,inr");

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
/*
                CoinWatcher response_one = new Gson().fromJson(response.body(), CoinWatcher.class);
                System.out.println("getPrices : "+response_one);
                Log.d("getPrices", String.valueOf.(response_one));*/

                Log.d("getPrices", response.body().toString());
                onFinishedListener. onFinished(response.body());

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

}
