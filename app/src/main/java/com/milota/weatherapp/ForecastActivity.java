package com.milota.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.milota.weatherapp.Model.WeatherForecastModel;
import com.milota.weatherapp.RetrofitAPI.RetrofitClient;
import com.milota.weatherapp.RetrofitAPI.WeatherService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForecastActivity extends AppCompatActivity {

    @BindView(R.id.rvForecasts) RecyclerView recyclerView;

    private WeatherForecastRecyclerViewAdapter mAdapter;
    private String cityName;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance();

    List<WeatherForecastModel> forecasts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        Intent intent = getIntent();
        cityName = intent.getStringExtra("cityName");

        ButterKnife.bind(this);

        init();
        bindAdapter();
    }

    private void init() {

        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<List<WeatherForecastModel>> call = weatherService.getWeatherForecast(cityName,"26fbb994d78d012c388d5ecb2f45f701");

        call.enqueue(new Callback<List<WeatherForecastModel>>() {
            @Override
            public void onResponse(Call<List<WeatherForecastModel>> call, Response<List<WeatherForecastModel>> response) {
                List<WeatherForecastModel> forecastModels = response.body();
                for(WeatherForecastModel model : forecastModels){
                    forecasts.add(model);
                }
            }

            @Override
            public void onFailure(Call<List<WeatherForecastModel>> call, Throwable t) {
                Toast.makeText(ForecastActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void bindAdapter() {
        mAdapter = new WeatherForecastRecyclerViewAdapter(forecasts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }
}
