package com.smart.appworld.flip.fragment.coinsList;

import android.util.Log;

import com.smart.appworld.flip.model.Coin;
import com.smart.appworld.flip.my_interface.GetDataService;
import com.smart.appworld.flip.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class CoinsListIntractorImpl implements CoinsListContract.Interactor {

    @Override
    public void getCoinsList(final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get the  data*/
        Call<List<Coin>> call = service.getCoinsData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Coin>>() {
            @Override
            public void onResponse(Call<List<Coin>> call, Response<List<Coin>> response) {
             Log.d("getCoinsList", response.body().toString());
                onFinishedListener. onFinished(response.body());

            }

            @Override
            public void onFailure(Call<List<Coin>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

}
