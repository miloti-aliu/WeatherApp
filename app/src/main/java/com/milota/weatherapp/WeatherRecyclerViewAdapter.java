package com.milota.weatherapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.milota.weatherapp.Model.WeatherModel;

import java.util.List;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder> {

    List<WeatherModel> weathers;

    public WeatherRecyclerViewAdapter(List<WeatherModel> weathers){
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public WeatherRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listCity = layoutInflater.inflate(R.layout.activity_item_city, parent, false);
        ViewHolder viewHolder = new ViewHolder(listCity);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WeatherModel weather = weathers.get(position);

//        holder.weatherIcon
        holder.cityName.setText(weather.getCity().getName());
        holder.weatherTemp.setText(weather.getMain().getTemp() + "°");

        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ForecastActivity.class);
            intent.putExtra("citName", weather.getCity().getName());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView weatherIcon;
        public TextView cityName;
        public TextView weatherTemp;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.weatherIcon = itemView.findViewById(R.id.weatherIcon);
            this.cityName = itemView.findViewById(R.id.cityName);
            this.weatherTemp = itemView.findViewById(R.id.weatherTemp);
            this.linearLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
