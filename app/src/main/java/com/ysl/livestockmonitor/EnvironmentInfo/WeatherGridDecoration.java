package com.ysl.livestockmonitor.EnvironmentInfo;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.util.DensityUtil;

public class WeatherGridDecoration extends RecyclerView.ItemDecoration {
	private float space;

	public WeatherGridDecoration(float space){
		this.space = space;
	}

	@Override
	public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.top = DensityUtil.dp2px(space);
	}
}
