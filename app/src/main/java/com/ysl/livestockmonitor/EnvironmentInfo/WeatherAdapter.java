package com.ysl.livestockmonitor.EnvironmentInfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ysl.livestockmonitor.R;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

	private Context mContext;

	private List<WeatherItem> mWeatherList;

	static class ViewHolder extends RecyclerView.ViewHolder{
		CardView cardView;
		ImageView imageView;
		TextView textView;

		public ViewHolder(View itemView){
			super(itemView);
			cardView = (CardView)itemView;
			imageView = (ImageView)itemView.findViewById(R.id.iv_weather_icon);
			textView = (TextView)itemView.findViewById(R.id.tv_weather_info);
		}
	}

	public WeatherAdapter(List<WeatherItem> weatherItemList){
		mWeatherList = weatherItemList;
	}

	@NonNull
	@Override
	public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		if (mContext == null){
			mContext = viewGroup.getContext();
		}
		View view = LayoutInflater.from(mContext).inflate(R.layout.weather_view_item,viewGroup,false);
		final ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder viewHolder, int i) {
		WeatherItem weatherItem = mWeatherList.get(i);
		viewHolder.textView.setText(weatherItem.getInfo());
		Glide.with(mContext).load(weatherItem.getImgID()).into(viewHolder.imageView);
//		viewHolder.imageView.setImageResource(weatherItem.getImgID());
	}

	@Override
	public int getItemCount() {
		return mWeatherList.size();
	}
}
