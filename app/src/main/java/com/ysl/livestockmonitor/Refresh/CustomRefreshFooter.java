package com.ysl.livestockmonitor.Refresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.ysl.livestockmonitor.R;

public class CustomRefreshFooter extends LinearLayout implements RefreshFooter {

	public CustomRefreshFooter(Context context,String text){
		super(context);
		View view = View.inflate(context,R.layout.custom_refresh_footer,this);
		TextView tv = (TextView)findViewById(R.id.tv_refresh_footer);
		tv.setText(text);
	}

	@Override
	public boolean setNoMoreData(boolean noMoreData) {
		return false;
	}

	@NonNull
	@Override
	public View getView() {
		return this;
	}

	@NonNull
	@Override
	public SpinnerStyle getSpinnerStyle() {
		return SpinnerStyle.Translate;
	}

	@Override
	public void setPrimaryColors(int... colors) {

	}

	@Override
	public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {

	}

	@Override
	public void onPulling(float percent, int offset, int height, int extendHeight) {

	}

	@Override
	public void onReleasing(float percent, int offset, int height, int extendHeight) {

	}

	@Override
	public void onReleased(RefreshLayout refreshLayout, int height, int extendHeight) {

	}

	@Override
	public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int extendHeight) {

	}

	@Override
	public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
		return 0;
	}

	@Override
	public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

	}

	@Override
	public boolean isSupportHorizontalDrag() {
		return false;
	}

	@Override
	public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

	}
}