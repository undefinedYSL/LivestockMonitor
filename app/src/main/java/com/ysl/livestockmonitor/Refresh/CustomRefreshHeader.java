package com.ysl.livestockmonitor.Refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.ysl.livestockmonitor.R;

import com.orhanobut.logger.Logger;

public class CustomRefreshHeader extends LinearLayout implements RefreshHeader {

	private ImageView mImageView;
	private AnimationDrawable pullDownAnim;
	private AnimationDrawable refreshingAnim;

	private boolean hasSetPullDownAnim = false;

	public CustomRefreshHeader(Context context){
		this(context,null,0);
	}

	public CustomRefreshHeader(Context context , AttributeSet attrs){
		this(context,attrs,0);
	}

	public CustomRefreshHeader(Context context , AttributeSet attrs , int defStyleAttr){
		super(context, attrs, defStyleAttr);
		View view = View.inflate(context, R.layout.custom_refresh_header, this);
		mImageView = (ImageView) view.findViewById(R.id.iv_refresh_header);
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
		Logger.d("percent:"+percent);

		//下拉百分比小于100%，不断调用setScale方法改变图片大小
		if (percent < 1){
			mImageView.setScaleX(percent);
			mImageView.setScaleY(percent);

			//是否执行过翻跟头动画的标记
			if (hasSetPullDownAnim){
				hasSetPullDownAnim = false;
			}
		}

		//当下拉的高度达到header高度100%时，开始加载正在下拉的初始动画，即翻跟头
		if (percent >= 1.0){
			//因为这个方法是不停调用的，防止重复
			if (!hasSetPullDownAnim){
				mImageView.setImageResource(R.drawable.anim_pull_loading);
				pullDownAnim = (AnimationDrawable)mImageView.getDrawable();
				pullDownAnim.start();

				hasSetPullDownAnim = true;
			}
		}
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
		if (pullDownAnim != null && pullDownAnim.isRunning()){
			pullDownAnim.stop();
		}
		if (refreshingAnim != null && refreshingAnim.isRunning()){
			refreshingAnim.stop();
		}
		hasSetPullDownAnim = false;
		return 0;
	}

	@Override
	public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

	}

	@Override
	public boolean isSupportHorizontalDrag() {
		return false;
	}


	/**
	 * 状态改变时调用。切换到某个特别的动画
	 * @param refreshLayout
	 * @param oldState
	 * @param newState
	 */
	@Override
	public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
		switch (newState){
			case PullDownToRefresh:
				mImageView.setImageResource(R.drawable.vector_drawable_vertical_align_botto);
				break;
			case Refreshing:
				mImageView.setImageResource(R.drawable.anim_pull_refreshing);
				refreshingAnim = (AnimationDrawable)mImageView.getDrawable();
				refreshingAnim.start();
				break;
			case ReleaseToRefresh:

				break;
		}
	}


}
