package com.my.shili.util;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class ApplicationData {
	
	public static String URL = "http://192.168.1.93:8080/zsxf";
	public static String UPLOAD_URL = URL + "/UploadServlet";
	public static String CH;
	public static ExecutorService executorService;// 线程池
	
	public static final int NORMAL = 1;// 正常
	public static final int NULL = 0;// 没有相关数据
	public static final int FAIL = -1;// 请求失败
	public static final int NetErr = -2;// 网络错误
	public static final String S_NULL = "没有相关数据！";
	public static final String S_FAIL = "请求失败！";
	public static final String S_NetErr = "网络错误！";

	public static ExecutorService getExecutorService() {
		if (executorService == null) {
			executorService = Executors.newFixedThreadPool(5);
		}
		return executorService;
	}
	//test
	/**
	 * 初始化图片加载
	 * @param context
	 */
	public static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/shili/Cache");
        
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();
        
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
        		.defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .threadPoolSize(3)//线程池内加载的数量
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheExtraOptions(480, 320, null)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .memoryCache(new WeakMemoryCache())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
