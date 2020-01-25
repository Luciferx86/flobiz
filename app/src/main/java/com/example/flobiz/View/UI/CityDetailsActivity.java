package com.example.flobiz.View.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flobiz.Model.WeatherResponse;
import com.example.flobiz.R;
import com.example.flobiz.Rest.RestApi;
import com.example.flobiz.ViewModel.WeatherViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityDetailsActivity extends AppCompatActivity {

    TextView cityName;
    TextView cityTempMax;
    TextView cityTempMin;
    TextView cityHumidity;
    TextView cityWindSpeed;

    Button goBack;

    String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cityName = findViewById(R.id.cityDetailCityName);
        cityTempMax = findViewById(R.id.cityDetailTempMax);
        cityTempMin = findViewById(R.id.cityDetailTempMin);
        cityHumidity = findViewById(R.id.cityDetailHumidity);
        cityWindSpeed = findViewById(R.id.cityDetailWindSpeed);
        city = getIntent().getStringExtra("city");
        cityName.setText(city);


        WeatherViewModel model = ViewModelProviders.of(this).get(WeatherViewModel.class);

        model.getWeather(city).observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                Log.d("Changed","Data Changed");
                cityTempMax.setText("Max Temperature: "+ String.format ("%.02f",weatherResponse.main.temp_max - 273.15) + " C");
                cityTempMin.setText("Min Temperature: "+String.format ("%.02f",weatherResponse.main.temp_min - 273.15) + " C");
                cityHumidity.setText("Humidity: "+(weatherResponse.main.humidity));
                cityWindSpeed.setText("Wind Speed: "+(weatherResponse.wind.speed));
            }
        });
    }
}
