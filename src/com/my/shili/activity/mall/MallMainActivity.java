package com.my.shili.activity.mall;


import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.my.shili.activity.R;
import com.my.shili.adapter.GridAdapter;
import com.my.shili.bean.MenuBean;
import com.my.shili.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MallMainActivity extends Activity {
	
	private final int START_CAPTURE_ACTIVITY_RQUEST_CODE=1;
	private final int SCANNER_SUCCESS_CODE_VALUE=501;
	
	
	@ViewInject(R.id.title_back)
	private ImageButton titleBack;
	@ViewInject(R.id.mall_title_menu)
	private ImageButton mallTitleMenu;
	@ViewInject(R.id.barcodescanner)
	private ImageButton barcodescanner;
	@ViewInject(R.id.searchBtn)
	private TextView searchBtn;
	@ViewInject(R.id.flgrid)
	private GridView flgrid;
	@ViewInject(R.id.flgrid2)
	private GridView flgrid2;
	
	private GridAdapter mall_fl_adapter,mall_fl_adapter2;//分类导航适配器
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mall_main);
		ViewUtils.inject(this);
		initMenu();
	}
	
	@OnClick({R.id.title_back,R.id.mall_title_menu,R.id.searchBtn,R.id.barcodescanner})
	private void click(View v){
		switch (v.getId()) {
		case R.id.searchBtn://检索按钮
			Intent it=new Intent(MallMainActivity.this,MallSerachActivity.class);
			startActivity(it);
			break;
		case R.id.barcodescanner://扫码
			Intent scannerit=new Intent(MallMainActivity.this,CaptureActivity.class); 
			Bundle b=new Bundle();
			b.putInt("code", SCANNER_SUCCESS_CODE_VALUE);
			scannerit.putExtras(b);
			startActivityForResult(scannerit,START_CAPTURE_ACTIVITY_RQUEST_CODE);
			break;
		default:
			break;
		}
	}

	/**
	 * 扫码返回
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==START_CAPTURE_ACTIVITY_RQUEST_CODE && resultCode==SCANNER_SUCCESS_CODE_VALUE){
			String res=data.getExtras().getString("text");
			Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();;
		}
	}
	
	/**
	 * 头部导航的2个gridview点击事件
	 * @author cht
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@OnItemClick({R.id.flgrid,R.id.flgrid2})
	private void itemClick(AdapterView<?> parent, View view, int position,long id){
		MenuBean mb=null;
		switch (parent.getId()) {
		case R.id.flgrid:
			mb=(MenuBean) flgrid.getAdapter().getItem(position);
			enterFlMall(mb);
			break;
		case R.id.flgrid2:
			mb=(MenuBean) flgrid2.getAdapter().getItem(position);
			enterFlMall(mb);
			break;
		}
		
	}
	
	/**
	 * 进入分类商城
	 * @param mb
	 */
	private void enterFlMall(MenuBean mb){
		if(mb!=null){
			if(!mb.isClickable()){
				Toast.makeText(getApplicationContext(), "未开通", Toast.LENGTH_SHORT).show();
				return ;
			}
			if(mb.getClazz()==null){
				Toast.makeText(getApplicationContext(), "建设中", Toast.LENGTH_SHORT).show();
				return ;
			}
			Intent intent=new Intent(getApplicationContext(), mb.getClass());
			startActivity(intent);
		}
	}
	
	/**
	 * 初始化头部九宫格menu
	 */
	private void initMenu(){
		List<MenuBean> menus = new ArrayList<MenuBean>();
		menus.add(new MenuBean("超市零食", R.drawable.csls_icon,false,""));
		menus.add(new MenuBean("餐饮外卖", R.drawable.cywm_icon,false,""));
		menus.add(new MenuBean("衣物护洗", R.drawable.ywhx_icon,false,""));
		menus.add(new MenuBean("学习用品", R.drawable.xxyp_icon,false,""));
		List<MenuBean> menus2 = new ArrayList<MenuBean>();
		menus2.add(new MenuBean("培训报名", R.drawable.pxbm_icon,false,""));
		menus2.add(new MenuBean("旅游素拓", R.drawable.lyst_icon,false,""));
		menus2.add(new MenuBean("摄影印刷", R.drawable.syys_icon,false,""));
		menus2.add(new MenuBean("其他服务", R.drawable.qtfw_icon,false,""));
		this.mall_fl_adapter = new GridAdapter(this, menus);
		this.mall_fl_adapter2 = new GridAdapter(this, menus2);
		flgrid.setAdapter(mall_fl_adapter);
		flgrid2.setAdapter(mall_fl_adapter2);
		flgrid.setOverScrollMode(View.OVER_SCROLL_NEVER); //2.3以后去除滑动阴影
	}
	
	
}
