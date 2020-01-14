package com.milota.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.milota.weatherapp.model.WeatherModel;
import com.milota.weatherapp.retrofitAPI.RetrofitClient;
import com.milota.weatherapp.retrofitAPI.WeatherService;

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
    private WeatherService weatherService = retrofit.create(WeatherService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bindAdapter();
        init();
    }

    private void init(){

        final Call<WeatherModel> call = weatherService.getWeather("524901,703448,2643743,658226,3183875,2673730,5128581", "metric","26fbb994d78d012c388d5ecb2f45f701");

        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                mAdapter.addWeatherModel(response.body());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


    private void bindAdapter(){
        mAdapter = new WeatherRecyclerViewAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }
}
