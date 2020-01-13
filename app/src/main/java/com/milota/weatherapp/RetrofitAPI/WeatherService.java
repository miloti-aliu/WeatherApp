package com.milota.weatherapp.RetrofitAPI;

import com.milota.weatherapp.Model.WeatherForecastModel;
import com.milota.weatherapp.Model.WeatherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<WeatherModel> getWeather(@Query("q")String cityName,
                                  @Query("appid")String appid);

    @GET("forecast")
    Call<List<WeatherForecastModel>> getWeatherForecast(@Query("name")String cityName,
                                                              @Query("appid")String appid);
}
