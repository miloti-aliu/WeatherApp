package com.milota.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.milota.weatherapp.Model.City;
import com.milota.weatherapp.Model.WeatherModel;
import com.milota.weatherapp.RetrofitAPI.RetrofitClient;
import com.milota.weatherapp.RetrofitAPI.WeatherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvCities) RecyclerView recyclerView;

    private WeatherRecyclerViewAdapter mAdapter;

    private Retrofit retrofit = RetrofitClient.getRetrofitInstance();

    List<WeatherModel> weatherList = new ArrayList<>();
    List<City> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bindCities();
        init();
        bindAdapter();
    }


    private void bindCities() {
        cities.add(new City("London"));
        cities.add(new City("Stockholm"));
        cities.add(new City("Tirana"));
        cities.add(new City("München"));
    }

    private void init(){

        for(City c : cities){

            WeatherService weatherService = retrofit.create(WeatherService.class);
            Call<WeatherModel> call = weatherService.getWeather(c.getName(),"26fbb994d78d012c388d5ecb2f45f701");


            call.enqueue(new Callback<WeatherModel>() {
                @Override
                public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                    WeatherModel weatherModel = response.body();
                    weatherList.add(weatherModel);
                }

                @Override
                public void onFailure(Call<WeatherModel> call, Throwable t) {
                    Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    private void bindAdapter(){
        mAdapter = new WeatherRecyclerViewAdapter(weatherList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }
}
