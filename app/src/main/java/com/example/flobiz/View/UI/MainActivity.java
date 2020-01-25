package com.example.flobiz.View.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.flobiz.Model.City;
import com.example.flobiz.Model.CityList;
import com.example.flobiz.R;
import com.example.flobiz.Rest.RestApi;
import com.example.flobiz.View.Adapter.CityAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static final String BASE_URL = "https://api.myjson.com/";
    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        connectAndGetApiData();


    }
    public void connectAndGetApiData() {

        Log.d("entering","connecting");
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        RestApi cityApiService = retrofit.create(RestApi.class);
        Call<CityList> call = cityApiService.getCities();
        call.enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {
                List<City> cities = response.body().getCities();
                if(cities!=null) {
                    recyclerView.setAdapter(new CityAdapter(cities, R.layout.city_name, getApplicationContext()));
                    Log.d("Cities", cities.get(0).getName());
                }else{
                    Log.d("Cities", "no cities found");
                }
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable throwable) {
                String TAG = "Cities";
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
