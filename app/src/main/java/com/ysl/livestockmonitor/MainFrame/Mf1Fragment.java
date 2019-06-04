package com.ysl.livestockmonitor.MainFrame;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.ysl.livestockmonitor.Home.ArticleAdapter;
import com.ysl.livestockmonitor.Home.ArticleItem;
import com.ysl.livestockmonitor.R;
import com.ysl.livestockmonitor.Refresh.CustomRefreshFooter;
import com.ysl.livestockmonitor.Refresh.CustomRefreshHeader;
import com.ysl.livestockmonitor.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mf1Fragment extends Fragment{

	private ArticleAdapter adapter;
	private List<ArticleItem> articleItemList = new ArrayList<>();

	@BindView(R.id.banner_mf1)
	public Banner mBanner;
	@BindView(R.id.recycler_mf1)
	public RecyclerView mRecyclerView;
	@BindView(R.id.smartRefreshLayout_mf1)
	public SmartRefreshLayout mSmartRefreshLayout;

	//每一页的大小
	private static final int PAGE_SIZE = 3;
	//当前第几页
	private int mCurrentPage = 0;
	//是否没有更多数据了
	private boolean isNoMoreData = false;

	public Mf1Fragment() {
		// Required empty public constructor
	}

	public static Mf1Fragment newInstance(String title){
		Mf1Fragment fragment = new Mf1Fragment();
		Bundle bundle = new Bundle();
		bundle.putString("title",title);
		fragment.setArguments(bundle);
		return fragment;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_mf1,null);
		ButterKnife.bind(this,view);

		return view;
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

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//初始化数据
		initItems();
		//初始化控件
		init();
	}

	private void init(){
		initBanner();
		initSmartRefreshLayout();
		initRecyclerView();
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false){
			@Override
			public boolean canScrollVertically() {
				return false;
			}
		};
		mRecyclerView.setLayoutManager(layoutManager);
		mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
		adapter = new ArticleAdapter(articleItemList);
		mRecyclerView.setAdapter(adapter);
	}

	private void initItems(){
		articleItemList.add(new ArticleItem("2019年中央一号文件全文发布：畜牧养殖行业迎来新机遇",
				"2019-3-10",R.drawable.news1));
		articleItemList.add(new ArticleItem("美国凭什么能成为养猪第一大国，看看美国养猪场的猪粪你就懂了?",
				"2019-3-2",R.drawable.news4));
		articleItemList.add(new ArticleItem("多位畜牧行业人士参与两会并为养殖行业发声。",
				"2019-3-1",R.drawable.news3));
		articleItemList.add(new ArticleItem("跨越发展 富民强盟 ——锡林郭勒盟改革开放40年畜牧业发展纪实",
				"2019-1-1",R.drawable.news2));
	}

	private void initSmartRefreshLayout() {
		mSmartRefreshLayout.setRefreshHeader(new CustomRefreshHeader(getActivity()));
		mSmartRefreshLayout.setRefreshFooter(new CustomRefreshFooter(getActivity(),"加载中"));
		mSmartRefreshLayout.setEnableScrollContentWhenLoaded(true);
		mSmartRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
		mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
			@Override
			public void onLoadMore(RefreshLayout refreshLayout) {

			}

			@Override
			public void onRefresh(RefreshLayout refreshLayout) {

			}
		});
	}



	public void initBanner(){
		mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
				.setImageLoader(new GlideImageLoader())
				.setImages(getBannerImages())
				.setBannerAnimation(Transformer.Default)
				.isAutoPlay(true)
				.setDelayTime(3000)
				.setIndicatorGravity(BannerConfig.CENTER)
				.start();
	}

	private List<Integer> getBannerImages() {
		List<Integer> mBannerImages = new ArrayList<>();
		mBannerImages.add(R.drawable.banner4);
		mBannerImages.add(R.drawable.banner5);
		mBannerImages.add(R.drawable.banner6);
		mBannerImages.add(R.drawable.banner7);
		return mBannerImages;
	}

	private void onLoadMore(){
		if (isNoMoreData){
			return;
		}

	}
}
