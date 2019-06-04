package com.ysl.livestockmonitor;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class IndicatorViewPagerAdatper extends PagerAdapter {
	private List<View> navList;

	public IndicatorViewPagerAdatper(List<View> navList){
		this.navList = navList;
	}

	@Override
	public int getCount() {
		return navList.size();
	}

	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
		return view == o;
	}

	@NonNull
	@Override
	public Object instantiateItem(@NonNull ViewGroup container, int position) {
		container.addView(navList.get(position));
		return navList.get(position);
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView(navList.get(position));
	}
}
