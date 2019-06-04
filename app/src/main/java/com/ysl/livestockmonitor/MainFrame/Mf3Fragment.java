package com.ysl.livestockmonitor.MainFrame;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ysl.livestockmonitor.EnvironmentInfo.WeatherAdapter;
import com.ysl.livestockmonitor.EnvironmentInfo.WeatherGridDecoration;
import com.ysl.livestockmonitor.EnvironmentInfo.WeatherItem;
import com.ysl.livestockmonitor.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mf3Fragment extends Fragment {

	private WeatherAdapter adapter_f;
	private WeatherAdapter adapter_r;
	private List<WeatherItem> weatherItemList_f = new ArrayList<>();
	private List<WeatherItem> weatherItemList_r = new ArrayList<>();

	@BindView(R.id.rv_mf3_realtime)
	RecyclerView recyclerView_realtime;
	@BindView(R.id.rv_mf3_forecast)
	RecyclerView recyclerView_forecast;
	@BindView(R.id.iv_mf3_refresh)
	ImageView imageView;


	public Mf3Fragment() {
		// Required empty public constructor
	}

	public static Mf3Fragment newInstance(String title){
		Mf3Fragment fragment = new Mf3Fragment();
		Bundle bundle = new Bundle();
		bundle.putString("title",title);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_mf3, container, false);
		ButterKnife.bind(this,view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initItems();
		init();
	}

	private void init() {
		GridLayoutManager gridLayoutManager_r = new GridLayoutManager(getContext(),3);
		GridLayoutManager gridLayoutManager_f = new GridLayoutManager(getContext(),3);
		//将grid配置进行进去
		recyclerView_realtime.setLayoutManager(gridLayoutManager_r);
		recyclerView_forecast.setLayoutManager(gridLayoutManager_f);
		//设置分割器
		recyclerView_forecast.addItemDecoration(new WeatherGridDecoration(12));
		recyclerView_realtime.addItemDecoration(new WeatherGridDecoration(12));
		//设置动画
		recyclerView_forecast.setItemAnimator(new DefaultItemAnimator());
		recyclerView_realtime.setItemAnimator(new DefaultItemAnimator());
		//向adapter中添加数据
		adapter_f = new WeatherAdapter(weatherItemList_f);
		adapter_r = new WeatherAdapter(weatherItemList_r);

		recyclerView_realtime.setAdapter(adapter_r);
		recyclerView_forecast.setAdapter(adapter_f);
	}

	private void initItems() {
		weatherItemList_r.add(new WeatherItem(R.drawable.svg_qing,"晴"));
		weatherItemList_r.add(new WeatherItem(R.drawable.svg_temp,"当前温度14℃"));
		weatherItemList_r.add(new WeatherItem(R.drawable.svg_humi,"湿度41%"));
		weatherItemList_r.add(new WeatherItem(R.drawable.svg_winds,"风速3km/h"));
		weatherItemList_r.add(new WeatherItem(R.drawable.svg_windd,"西南"));
		weatherItemList_r.add(new WeatherItem(R.drawable.svg_rainfall,"0mm"));

		weatherItemList_f.add(new WeatherItem(R.drawable.svg_morecloud,"多云" ));
		weatherItemList_f.add(new WeatherItem(R.drawable.svg_temp,"4-15℃"));
		weatherItemList_f.add(new WeatherItem(R.drawable.svg_humi,"湿度41%"));
		weatherItemList_f.add(new WeatherItem(R.drawable.svg_winds,"风速4.1km/h"));
		weatherItemList_f.add(new WeatherItem(R.drawable.svg_windd,"南"));
		weatherItemList_f.add(new WeatherItem(R.drawable.svg_rainfall,"0mm"));
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		//取消异步任务
	}

}
