package ru.psylon.weatherapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.psylon.weatherapp.common.CityChangedObservable;
import ru.psylon.weatherapp.inteface.ItemClickListener;
import ru.psylon.weatherapp.model.CityCard;

public class CitiesRecyclerAdapter extends Adapter<CitiesRecyclerAdapter.CityViewHolder> {
    List<CityCard> data;
    Context context;

    int rowIndex = -1;

    CitiesRecyclerAdapter(List<CityCard> data, Context context) {
        this.data = data;
        this.context = context;
        rowIndex = CityChangedObservable.getCurrentIndex();
        Log.i("%%%%%%%%%%%", String.valueOf(rowIndex));
        Log.i("%%%%%%%%%% CUR: ", CityChangedObservable.currentCityCard == null ? "null" : CityChangedObservable.currentCityCard.getCityName());
    }



    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_city, viewGroup, false);
        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        cityViewHolder.cityName.setText(data.get(i).getCityName());

        cityViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                rowIndex = position;
                CityChangedObservable.cityCardChanged(data.get(position));
                CityChangedObservable.setCurrentIndex(position);
                notifyDataSetChanged();
            }
        });

        if (rowIndex == i)
            cityViewHolder.cardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
        else
            cityViewHolder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView cityName;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        ItemClickListener itemClickListener;

        CityViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardView = (CardView) itemView;
            this.cityName = itemView.findViewById(R.id.city_name);
            cityName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
