package com.example.transferee.web;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "http://10.0.2.2:3000/";
    private static  final String BASE_URL_SHORTEN = "http://10.0.2.2:3000";
    private static final Retrofit Retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()).build();

    public static WebService getWebService() {
        return Retrofit.create(WebService.class);
    }

    public static String getBaseURLShorten() {
        return BASE_URL_SHORTEN;
    }
}
