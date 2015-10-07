package com.my.shili.util;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.my.shili.activity.R;
//将会被xutils淘汰
@Deprecated
public class Reflex {
	
	public static Map<String, Integer> idMap = new HashMap<String, Integer>();
	public final static String logName = "Reflex";
	public final static Class view = View.class;
	static {
		Class idClazz = R.id.class;
		Field[] ids = idClazz.getDeclaredFields();
		Field.setAccessible(ids, true);
		for (Field id : ids) {
			try {
				idMap.put(id.getName().toLowerCase(), id.getInt(R.id.class));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 请确保xml中view对象的id与activity实体中的属性名称一致 不区分大小写 通过反射加载activity中的view视图属性
	 * 
	 * @param activity
	 */
	public static void loadViewForActivityOnCreate(Activity activity, int xml) {
		activity.setContentView(xml);
		Class c = activity.getClass();
		Field[] f = c.getDeclaredFields();
		Field.setAccessible(f, true);
		int i = 0;
		for (Field f1 : f) {
			try {
				if (view.isAssignableFrom(f1.getType())) {
					if (idMap.containsKey(f1.getName().toLowerCase())) {
						f1.set(activity, activity.findViewById(idMap.get(f1.getName().toLowerCase())));
					} else {
						Log.d(logName, f1.getName() + "不能实例化，请检查xml文件和修改id或者修改 " + c.getName() + "类的属性" + f1.getName() + "名称");
						i++;
					}
				} else {
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (i > 0) {
		}
	}

	/**
	 *在适配器getView对象中使用 为ItemHolder中的view装载
	 * 
	 * @param holder
	 * @param convertView
	 */
	public static void loadViewForAdapterGetView(Object holder, View convertView) {
		Class c = holder.getClass();
		Field[] fields = c.getDeclaredFields();
		Field.setAccessible(fields, true);
		for (Field field : fields) {
			if (view.isAssignableFrom(field.getType())) {
				if (idMap.containsKey(field.getName().toLowerCase())) {
					try {
						field.set(holder, convertView.findViewById(idMap.get(field.getName().toLowerCase())));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 将bean对象中的数据填充到activity界面中 目前只支持textview 和edittext
	 * 
	 * @param activity
	 * @param res
	 */
	public static void loadDetail(Object activity, Object res) {
		Class resClazz = res.getClass();
		Class activityClazz = activity.getClass();
		Field[] fileds = resClazz.getDeclaredFields();
		Field.setAccessible(fileds, true);
		try {
			for (int i = 0; i < fileds.length; i++) {
				String filedName = fileds[i].getName();
				Field activityField = activityClazz.getDeclaredField(filedName);
				Field.setAccessible(new Field[] { activityField }, true);
				if (activityField != null && (activityField.getType().getName().equals(TextView.class.getName()) || activityField.getType().getName().equals(EditText.class.getName()))) {
					Method resMethod = resClazz.getMethod("get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1));
					TextView tv = (TextView) activityField.get(activity);
					Object resFieldValue = resMethod.invoke(res);
					if (tv != null && resFieldValue != null) {
						tv.setText((String) resFieldValue);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}