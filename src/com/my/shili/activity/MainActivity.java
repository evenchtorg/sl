package com.my.shili.activity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.my.shili.activity.mall.MallMainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	@ViewInject(R.id.btn1)
	Button btn1;
	@ViewInject(R.id.btn2)
	Button btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
	}

	@OnClick({R.id.btn1,R.id.btn2})
	public void click(View v){
		switch (v.getId()) {
		case R.id.btn1:
			Intent openMallIntent = new Intent(MainActivity.this,MallMainActivity.class);
			startActivity(openMallIntent);
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
