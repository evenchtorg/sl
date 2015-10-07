package com.my.shili.widge;

import java.util.Date;

import com.my.shili.activity.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Custom_QG_View extends RelativeLayout {
	
	private TextView timesplitA,timesplitB,timesplitC;//倒计时分割
	private TextView morexsqg;//更多抢购按钮
	private TextView qgdjs_seccend;//倒计时：秒
	private TextView qgdjs_minute;//倒计时：分
	private TextView qgdjs_hours;//倒计时：时
	private TextView qgdjs_day;//倒计时：天
	private TextView qgdjsinfo;//倒计时提示信息
	

	public Custom_QG_View(Context context, AttributeSet attrs) {
		super(context, attrs);
		 LayoutInflater.from(context).inflate(R.layout.custom_qg_layout, this);
		 morexsqg=(TextView) findViewById(R.id.morexsqg);
		 qgdjs_seccend=(TextView) findViewById(R.id.qgdjs_seccend);
		 qgdjs_minute=(TextView) findViewById(R.id.qgdjs_minute);
		 qgdjs_hours=(TextView) findViewById(R.id.qgdjs_hours);
		 qgdjs_day=(TextView) findViewById(R.id.qgdjs_day);
		 qgdjsinfo=(TextView) findViewById(R.id.qgdjsinfo);
		 timesplitA=(TextView) findViewById(R.id.timesplitA);
		 timesplitB=(TextView) findViewById(R.id.timesplitB);
		 timesplitC=(TextView) findViewById(R.id.timesplitC);
	}
	/**
	 * 点击限时抢购触发时间
	 * @param listener
	 */
	public void bindMoreClick(OnClickListener listener) {
		if(listener!=null){
			morexsqg.setOnClickListener(listener);
		}
	}
	
	/**
	 * 启动计时器
	 * @param activityEndTime 结束时间
	 * @param activityEndTime 结束时间
	 */
	public void startTimer(Date activityEndTime,long timediff){
		
	}

}
