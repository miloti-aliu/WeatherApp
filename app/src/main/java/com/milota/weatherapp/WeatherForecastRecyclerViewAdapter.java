package com.milota.weatherapp;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.milota.weatherapp.Model.WeatherForecastModel;

import java.util.List;

public class WeatherForecastRecyclerViewAdapter extends RecyclerView.Adapter<WeatherForecastRecyclerViewAdapter.ViewHolder> {

    List<WeatherForecastModel> forecasts;

    public WeatherForecastRecyclerViewAdapter(List<WeatherForecastModel> f){
        this.forecasts = f;
    }

    @NonNull
    @Override
    public WeatherForecastRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listForecasts = layoutInflater.inflate(R.layout.activity_city_forecast, parent, false);
        ViewHolder viewHolder = new ViewHolder(listForecasts);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final WeatherForecastModel weatherForecastModel = forecasts.get(position);

//        holder.forecastIcon
        holder.dayName.setText(weatherForecastModel.getList().get(0).getDtText());
        holder.minTemp.setText(weatherForecastModel.getList().get(0).getMain().getMinTemp() + "°");
        holder.maxTemp.setText(weatherForecastModel.getList().get(0).getMain().getMaxTemp() + "°");
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView forecastIcon;
        public TextView dayName;
        public TextView minTemp;
        public TextView maxTemp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.forecastIcon = itemView.findViewById(R.id.icon);
            this.dayName = itemView.findViewById(R.id.day);
            this.minTemp = itemView.findViewById(R.id.minTemp);
            this.maxTemp = itemView.findViewById(R.id.maxTemp);
        }
    }
}
