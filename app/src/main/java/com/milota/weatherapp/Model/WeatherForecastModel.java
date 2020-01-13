package com.milota.weatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecastModel {

    @SerializedName("list")
    @Expose
    private List<WeatherList> list;

    public List<WeatherList> getList() {
        return list;
    }

    public void setList(List<WeatherList> list) {
        this.list = list;
    }

    public class WeatherList{

        @SerializedName("")
        @Expose
        private long dt;
        @SerializedName("main")
        @Expose
        private MainClass main;
        @SerializedName("weather")
        @Expose
        private List<Weather> weather;
        @SerializedName("dtText")
        @Expose
        private String dtText;

        public long getDt() {
            return dt;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }

        public MainClass getMain() {
            return main;
        }

        public void setMain(MainClass main) {
            this.main = main;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        public String getDtText() {
            return dtText;
        }

        public void setDtText(String dtText) {
            this.dtText = dtText;
        }

        public class MainClass{

            @SerializedName("temp")
            @Expose
            private double temp;
            @SerializedName("minTemp")
            @Expose
            private double minTemp;
            @SerializedName("maxTemp")
            @Expose
            private double maxTemp;

            public double getTemp() {
                return temp;
            }

            public void setTemp(double temp) {
                this.temp = temp;
            }

            public double getMinTemp() {
                return minTemp;
            }

            public void setMinTemp(double minTemp) {
                this.minTemp = minTemp;
            }

            public double getMaxTemp() {
                return maxTemp;
            }

            public void setMaxTemp(double maxTemp) {
                this.maxTemp = maxTemp;
            }
        }

        public class Weather{

            @SerializedName("icon")
            @Expose
            private String icon;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
