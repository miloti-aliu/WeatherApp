package com.milota.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.milota.weatherapp.model.WeatherForecastModel;
import com.milota.weatherapp.retrofitAPI.RetrofitClient;
import com.milota.weatherapp.retrofitAPI.WeatherService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForecastActivity extends AppCompatActivity {

    @BindView(R.id.rvForecasts) RecyclerView recyclerView;

    private WeatherForecastRecyclerViewAdapter mAdapter;
    private Retrofit retrofit = RetrofitClient.getRetrofitInstance();
    private WeatherService weatherService = retrofit.create(WeatherService.class);

    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        cityName = intent.getStringExtra("cityName");

        bindAdapter();
        init();
    }

    private void init() {

        final Call<WeatherForecastModel> call = weatherService.getWeatherForecast(cityName, "metric", "26fbb994d78d012c388d5ecb2f45f701");

        call.enqueue(new Callback<WeatherForecastModel>() {
            @Override
            public void onResponse(Call<WeatherForecastModel> call, Response<WeatherForecastModel> response) {
                mAdapter.addWeatherForecastModel(response.body());
            }

            @Override
            public void onFailure(Call<WeatherForecastModel> call, Throwable t) {
                Toast.makeText(ForecastActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void bindAdapter() {
        mAdapter = new WeatherForecastRecyclerViewAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }
}
