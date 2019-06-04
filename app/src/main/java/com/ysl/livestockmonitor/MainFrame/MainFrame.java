package com.ysl.livestockmonitor.MainFrame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ysl.livestockmonitor.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFrame extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

	private ArrayList<Fragment> fragments;
//	private BottomNavigationBar bottomNavigationBar;
	private Mf1Fragment mf1Fragment;
	private Mf2Fragment mf2Fragment;
	private Mf3Fragment mf3Fragment;
	private Mf4Fragment mf4Fragment;
	private FragmentManager fm;
	private FragmentTransaction ft;
	private Fragment fragment;

	@BindView(R.id.bottom_navigation_bar)
	public BottomNavigationBar bottomNavigationBar;
	@BindView(R.id.toolbar)
	public Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_frame);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		//初始化控件
		init();
		//获取所有的fragment
		fragments = getFragments();
		//默认选择0
		onTabSelected(0);
	}

	private ArrayList<Fragment> getFragments() {
		mf1Fragment = new Mf1Fragment();
		mf2Fragment = new Mf2Fragment();
		mf3Fragment = new Mf3Fragment();
		mf4Fragment = new Mf4Fragment();
		ArrayList<Fragment> fragments = new ArrayList<>();
		fragments.add(mf1Fragment.newInstance("主页"));
		fragments.add(mf2Fragment.newInstance("健康监测"));
		fragments.add(mf3Fragment.newInstance("环境监控"));
		fragments.add(mf4Fragment.newInstance("更多"));
		return fragments;
	}

	private void init() {
		bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
		bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
		bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.vector_drawable_home,"主页").setActiveColorResource(R.color.orange))
				.addItem(new BottomNavigationItem(R.drawable.vector_drawable_linechart,"健康监测").setActiveColorResource(R.color.teal))
				.addItem(new BottomNavigationItem(R.drawable.vector_drawable_cloud,"环境监测").setActiveColorResource(R.color.blue))
				.addItem(new BottomNavigationItem(R.drawable.vector_drawable_appstore,"更多").setActiveColorResource(R.color.brown))
				.setFirstSelectedPosition(0)
				.initialise();
		bottomNavigationBar.setTabSelectedListener(this);


	}

	@Override
	public void onTabSelected(int position) {
		if (fragments != null){
			if (position < fragments.size()){
				fm = getSupportFragmentManager();
				ft = fm.beginTransaction();
				fragment = fragments.get(position);
				String str = Integer.toString(position);
				if (fragment.isAdded()||fragment.isHidden()){
					ft.show(fragment);
					Log.d("onTabSelected","点击了"+str);
				}
				else {
					ft.add(R.id.frame_main,fragment);
					Log.d("onTabSelected","创建了"+str);
				}
				ft.commitAllowingStateLoss();
			}
		}
	}

	@Override
	public void onTabUnselected(int position) {
		if (fragments != null){
			if (position < fragments.size()){
				fm = getSupportFragmentManager();
				ft = fm.beginTransaction();
				fragment = fragments.get(position);
				String str = Integer.toString(position);
				ft.hide(fragment);
				Log.d("onTabReselected","我隐藏了"+str);
				ft.commitAllowingStateLoss();
			}
		}
	}

	@Override
	public void onTabReselected(int position) {
		if (fragments != null){
			if (position < fragments.size()){
				fm = getSupportFragmentManager();
				ft = fm.beginTransaction();
				fragment = fragments.get(position);
				String str = Integer.toString(position);
				if (fragment.isAdded()||fragment.isHidden()){
					ft.show(fragment);
					Log.d("onTabReselected","重复点击了"+str);
				}else {
					Log.d("onTabReselected","啥都没发生");
				}
				ft.commitAllowingStateLoss();
			}
		}
	}
}
