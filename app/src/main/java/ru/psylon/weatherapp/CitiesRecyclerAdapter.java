package ru.psylon.weatherapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CitiesRecyclerAdapter extends Adapter<CitiesRecyclerAdapter.CityViewHolder> {
    private String[] data;

    private View.OnClickListener onClickListener;

    CitiesRecyclerAdapter(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        if (onClickListener != null) textView.setOnClickListener(onClickListener);
        return new CityViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        cityViewHolder.textView.setText(data[i]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public void setOnCityClickListener(View.OnClickListener onItemClickListener) {
        this.onClickListener = onItemClickListener;
    }


    static class CityViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        CityViewHolder(@NonNull TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}
