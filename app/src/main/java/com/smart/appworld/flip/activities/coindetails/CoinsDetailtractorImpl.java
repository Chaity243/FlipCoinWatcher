package com.smart.appworld.flip.activities.coindetails;

import android.util.Log;

import com.smart.appworld.flip.model.CoinDetail;
import com.smart.appworld.flip.my_interface.GetDataService;
import com.smart.appworld.flip.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinsDetailtractorImpl  implements CoinDetailContract.Interactor {
    private String coinId;
    @Override
    public void getCoinDetial(String coinId,final OnFinishedListener onFinishedListener) {
        this.coinId=coinId;

        /** Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get the  data*/
        Call<CoinDetail> call = service.getCoinDetail(coinId);

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<CoinDetail>() {
            @Override
            public void onResponse(Call<CoinDetail> call, Response<CoinDetail> response) {
                Log.d("getCoinDetails", response.body().toString());
                onFinishedListener. onFinished(response.body());

            }

            @Override
            public void onFailure(Call<CoinDetail> call, Throwable t) {
                Log.d("CoinDetails onFailure:", t.getMessage());
                onFinishedListener.onFailure(t);
            }
        });

    }

}
