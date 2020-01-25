package com.example.flobiz.ViewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flobiz.Model.WeatherResponse;
import com.example.flobiz.Rest.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends ViewModel {

    private String AppId = "91ae153ec376cfd6284621f380b3286c";
    private String BaseUrl = "https://api.openweathermap.org/";

    private MutableLiveData<WeatherResponse> weatherResponseLive;


    public  MutableLiveData<WeatherResponse>  getWeather(String city){

        if (weatherResponseLive == null) {
            weatherResponseLive = new MutableLiveData<>();
            loadWeather(city);
        }

        //finally we will return the list
        return weatherResponseLive;
    }

    public void loadWeather(String city ){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi service = retrofit.create(RestApi.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(city, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                Log.d("Response",String.valueOf(response.code()));
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    weatherResponseLive.setValue(weatherResponse);


                }else{
                    try{
                        Log.d("Temperature",response.errorBody().string());
                    }catch (Exception e){
                        Log.d("Temperature",e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
//                weatherData.setText(t.getMessage());
                Log.d("Temperature",t.getMessage());
            }
        });
    }
}
