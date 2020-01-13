package com.milota.weatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherModel {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Weather")
    @Expose
    private Weather weather;
    @SerializedName("Main")
    @Expose
    private Main main;

    private City city;

    public City getCity(){return city;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public class Weather{

        @SerializedName("Icon")
        @Expose
        private String weatherIcon;

        public String getWeatherIcon() {
            return weatherIcon;
        }

        public void setWeatherIcon(String weatherIcon) {
            this.weatherIcon = weatherIcon;
        }
    }

    public class Main{
        @SerializedName("Temp")
        @Expose
        private double temp;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }
}
