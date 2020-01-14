package com.milota.weatherapp.retrofitAPI;

import com.milota.weatherapp.model.WeatherForecastModel;
import com.milota.weatherapp.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("group")
    Call<WeatherModel> getWeather(@Query("id")String cityId,
                                  @Query("units") String unit,
                                  @Query("appid")String appid);

    @GET("forecast")
    Call<WeatherForecastModel> getWeatherForecast(@Query("q")String cityName,
                                                  @Query("units")String unit,
                                                  @Query("appid")String appid);
}
