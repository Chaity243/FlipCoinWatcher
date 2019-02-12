package com.smart.appworld.flip.my_interface;

import com.google.gson.JsonElement;
import com.smart.appworld.flip.model.Coin;
import com.smart.appworld.flip.model.CoinDetail;
import com.smart.appworld.flip.model.EventResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("coins/list")
    Call<List<Coin>> getCoinsData();

    @GET("simple/price")
    Call<JsonElement> getPrices(@Query("ids") String ids, @Query("vs_currencies") String vs_currencies);


    @GET("coins/{id}")
    Call<CoinDetail> getCoinDetail(@Path(value = "id", encoded = true) String coinId);




    @GET("events")
    Call<EventResponse> getEventsData(@Query("from_date") String current_date,@Query("to_date") String x_days_after_date);

}