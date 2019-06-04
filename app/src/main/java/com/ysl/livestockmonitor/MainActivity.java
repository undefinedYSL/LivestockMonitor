package com.ysl.livestockmonitor;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ysl.livestockmonitor.MainFrame.MainFrame;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

	@BindView(R.id.indicator_viewpager)
	public ViewPager viewPager;
	@BindView(R.id.indicator_ll)
	public LinearLayout linearLayout;
	@BindView(R.id.indicator_tv)
	public TextView textView;

	//实例化加载图片，自行更换
	private static int[] img = {R.drawable.nav1,R.drawable.nav2,R.drawable.nav3};
	private List<View> imgsList;
	private int length;

	//实例化小圆点
	private ImageView[] dotViewsList;
	private ImageView dotView;

	//记录当前选中的位置
	private int currentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		//butterknife进行activity绑定
		ButterKnife.bind(this);
		length = img.length;

		textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,MainFrame.class));
				finish();
			}
		});
		loadImageView();
		loadPoint();
	}

	private void loadPoint() {
		dotViewsList = new ImageView[imgsList.size()];
		for (int i = 0;i<length;i++){
			dotView = new ImageView(this);
			dotView.setLayoutParams(new ViewGroup.LayoutParams(20,20));
			dotView.setPadding(30,0,30,0);
			dotViewsList[i] = dotView;
			if (i == 0){
				dotView.setImageResource(R.drawable.point_light);
			}else {
				dotView.setImageResource(R.drawable.point_deep);
			}
			linearLayout.addView(dotViewsList[i]);
		}
	}

	private void loadImageView() {
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
		imgsList = new ArrayList<View>();

		for (int i=0;i<length;i++){
			ImageView iv = new ImageView(this);
			iv.setLayoutParams(layoutParams);
			iv.setImageResource(img[i]);
			imgsList.add(iv);

		}
		viewPager.setAdapter(new IndicatorViewPagerAdatper(imgsList));
		viewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onPageScrolled(int i, float v, int i1) {

	}

	@Override
	public void onPageSelected(int position) {
		for (int i = 0;i<length;i++){
			dotViewsList[position].setImageResource(R.drawable.point_light);
			if (position != i){
				dotViewsList[position].setImageResource(R.drawable.point_deep);
			}
		}
		if (position == length-1){
			textView.setVisibility(View.VISIBLE);
		}else {
			textView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onPageScrollStateChanged(int i) {

	}
}
