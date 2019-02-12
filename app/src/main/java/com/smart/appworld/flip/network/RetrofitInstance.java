package com.smart.appworld.flip.network;

import com.smart.appworld.flip.MyApp;
import com.smart.appworld.flip.utility.Utils;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static OkHttpClient client;
    private static final String BASE_URL = "https://api.coingecko.com/api/v3/";

    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient(){
        if (client == null)
        {

            //setup cache
            File httpCacheDirectory = new File(MyApp.appContext.getCacheDir(), "responses");
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(httpCacheDirectory, cacheSize);

            client = new OkHttpClient.Builder().cache(cache).addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {

                    okhttp3.Response originalResponse = chain.proceed(chain.request());
                    if (Utils.isNetworkAvailable()) {
                        int maxAge = 60; // read from cache for 1 minute
                        return originalResponse.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .build();
                    } else {
                        int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                        return originalResponse.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .build();
                    }

                }
            }).build();




        }
        return client;

    }



}