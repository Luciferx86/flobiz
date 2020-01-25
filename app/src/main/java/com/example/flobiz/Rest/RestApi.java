package com.example.flobiz.Rest;

import com.example.flobiz.Model.City;
import com.example.flobiz.Model.CityList;
import com.example.flobiz.Model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RestApi {
    @Headers("Content-Type: text/html")
    @GET("bins/lw9qf")
    Call<CityList> getCities();

    @GET("data/2.5/weather?")
    Call<WeatherResponse> getCurrentWeatherData(@Query("q") String q,@Query("APPID") String app_id);
}