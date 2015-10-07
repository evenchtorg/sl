package com.my.shili.adapter;

import java.util.List;

import com.my.shili.activity.R;
import com.my.shili.bean.MenuBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

	private List<MenuBean> menus;
	private LayoutInflater mInflater;

	private class GridHolder {
		ImageView appImage;
		TextView appName;
	}

	public GridAdapter(Context c, List<MenuBean> menus) {
		super();
		this.menus = menus;
		mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return menus.size();
	}

	public Object getItem(int position) {
		return menus.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		GridHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.mall_main_grid_item, null);
			holder = new GridHolder();
			holder.appImage = (ImageView) convertView.findViewById(R.id.itemImage);
			holder.appName = (TextView) convertView.findViewById(R.id.itemText);
			convertView.setTag(holder);

		} else {
			holder = (GridHolder) convertView.getTag();
		}
		MenuBean info = menus.get(position);
		if (info != null) {
			/*if(info.getMenuName().contains("(")){
				String[] count = info.getMenuName().split("[(]");
				holder.appName.setText(Html.fromHtml(count[0] + "<font color = \"#ff0000\">" + "(" + count[1] + "</font>"));
			}else{*/
				holder.appName.setText(info.getMenuName());
//			}
			holder.appImage.setImageResource(info.getImgRes());
		}
		return convertView;
	}
}