package com.ysl.livestockmonitor.MainFrame;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ysl.livestockmonitor.LoginAndSearch.LoginActivity;
import com.ysl.livestockmonitor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mf4Fragment extends Fragment {

	@BindView(R.id.rl_mf4)
	RelativeLayout mRelativeLayout;


	public Mf4Fragment() {
		// Required empty public constructor
	}

	public static Mf4Fragment newInstance(String title){
		Mf4Fragment fragment = new Mf4Fragment();
		Bundle bundle = new Bundle();
		bundle.putString("title",title);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_mf4, container, false);
		ButterKnife.bind(this,view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {
		mRelativeLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Mf4Fragment.this.getContext(),LoginActivity.class);
				startActivity(intent);
			}
		});
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
