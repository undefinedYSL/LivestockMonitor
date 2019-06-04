package com.ysl.livestockmonitor.MainFrame;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.ysl.livestockmonitor.PigTemperature.PigInfo;
import com.ysl.livestockmonitor.R;
import com.ysl.livestockmonitor.utils.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mf2Fragment extends Fragment {

	private Context mContext;

	@BindView(R.id.tv_mf2_latest)
	TextView tv_latest;
	@BindView(R.id.btn_mf2)
	TextView btn_search;
	@BindView(R.id.iv_mf2)
	ImageView iv_history;
	@BindView(R.id.et_mf2)
	EditText et_num;
	@BindView(R.id.lc_mf2)
	LineChart lc_history;

	//图表参数设置
	private XAxis mXAxis;
	private YAxis mYAxis;
	private YAxis nYAxis;
	private Legend mLegend;
	private LimitLine mLimitLine;

	//数据
	private SimpleDateFormat Itime = new SimpleDateFormat("HH:MM");
	//Entry
	List<Entry> entries = new ArrayList<Entry>();
	//初始化一个测试数组
	private List<PigInfo> pigInfoList = new ArrayList<>();

	//获取选择的年月日用
	String year,month,day;

	public Mf2Fragment() {
		// Required empty public constructor
	}

	public static Mf2Fragment newInstance(String title){
		Mf2Fragment fragment = new Mf2Fragment();
		Bundle bundle = new Bundle();
		bundle.putString("title",title);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_mf2,null);
		ButterKnife.bind(this,view);
		//测试用
		initData();
		return view;
	}

	private void initData() {
		pigInfoList.add(new PigInfo("080011",38.9f));
		pigInfoList.add(new PigInfo("090022",39.2f));
		pigInfoList.add(new PigInfo("100022",39.1f));
		pigInfoList.add(new PigInfo("110011",39.5f));
		pigInfoList.add(new PigInfo("120011",39.9f));
		pigInfoList.add(new PigInfo("130022",40.0f));
		pigInfoList.add(new PigInfo("140022",39.7f));
		pigInfoList.add(new PigInfo("150022",39.6f));
		pigInfoList.add(new PigInfo("160022",39.6f));
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//初始化搜索
		initHistorySearch();
		//初始化图标表,初始化线在展示中初始化
		initLineChart();
		//初始化按钮，目前为测试
		initBtn();
	}

	private void initBtn() {
		btn_search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showLineChart(pigInfoList,"猪只体温历史曲线", Color.GREEN);
				Log.d("Mf2Fragment","pigTemp@2@latest@OK@39.1@");
			}
		});
	}

	//曲线展示
	private void showLineChart(final List<PigInfo> dataList, String name, int color){
		for (int i = 0;i<dataList.size();i++){
			PigInfo pi = dataList.get(i);
			Entry entry = new Entry(i,(float)pi.getTemp());
			entries.add(entry);
		}
		mXAxis.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				String time = dataList.get((int)value%dataList.size()).getTime();

				return TimeUtil.formatTime(time);
			}
		});
		mXAxis.setLabelCount(6);
		mYAxis.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				return ((int)value)+"℃";
			}
		});
		mYAxis.setLabelCount(6);
		//每一个linedataset代表一条曲线
		LineDataSet lineDataSet = new LineDataSet(entries,name);
		initDataSet(lineDataSet,color,LineDataSet.Mode.LINEAR);
		LineData lineData = new LineData(lineDataSet);
		lc_history.setData(lineData);
	}

	//折线设置
	private void initDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
		lineDataSet.setColor(color);
		lineDataSet.setCircleColor(color);
		lineDataSet.setLineWidth(1f);
		lineDataSet.setCircleRadius(3f);

		//设置曲线值的圆点是实心还是空心
		lineDataSet.setDrawCircleHole(true);
		lineDataSet.setValueTextSize(10f);
		//设置折线图填充
		lineDataSet.setDrawFilled(true);
		lineDataSet.setFormLineWidth(1f);
		lineDataSet.setFormSize(15.f);
		if (mode == null){
			lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		}else {
			lineDataSet.setMode(mode);
		}
	}

	//图标设置
	private void initLineChart() {
		//是否展示网格线
		lc_history.setDrawGridBackground(false);
		//是否显示边界
		lc_history.setDrawBorders(false);
		//是否可以拖动
		lc_history.setDragEnabled(false);
		//修改背景色
//		lc_history.setBackgroundColor(Color.WHITE);
		//是否有触摸事件
		lc_history.setTouchEnabled(true);
		//设置XY轴动画效果
		lc_history.animateY(2500);
		lc_history.animateX(1500);


		/*XY轴设置*/
		mXAxis = lc_history.getXAxis();
		mYAxis = lc_history.getAxisLeft();
		nYAxis = lc_history.getAxisRight();
		//X轴设置显示位置在底部
		mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		mXAxis.setAxisMinimum(0f);
		mXAxis.setGranularity(1f);
		//保证Y轴从0开始，不然会上移一点
		mYAxis.setAxisMinimum(35f);
		mYAxis.setAxisMaximum(45f);
		nYAxis.setAxisMinimum(0f);
		//禁掉网格线
		mXAxis.setDrawGridLines(false);
		mYAxis.setDrawGridLines(true);
		nYAxis.setDrawGridLines(false);
		//设置虚线
		mYAxis.enableGridDashedLine(10f,10f,0);
		//去掉右侧坐标
		nYAxis.setEnabled(false);

		/*折线图例*/
		mLegend = lc_history.getLegend();
		mLegend.setForm(Legend.LegendForm.LINE);
		mLegend.setTextSize(12f);
		//显示位置 左下方
		mLegend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		mLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		mLegend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		//是否绘制在图表里面
		mLegend.setDrawInside(false);
	}

	//初始化历史搜索按钮的点击事件
	private void initHistorySearch() {
		iv_history.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDatePicker();
			}
		});
	}

	private void showDatePicker() {
		TimePickerView pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
			@Override
			public void onTimeSelect(Date date, View v) {
				Toast.makeText(mContext,getTime(date),Toast.LENGTH_SHORT).show();
			}
		}).build();
		pvTime.setDate(Calendar.getInstance());
		pvTime.show();
	}

	private String getTime(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mContext = getActivity();
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
