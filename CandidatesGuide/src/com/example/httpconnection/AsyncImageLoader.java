package com.example.httpconnection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import android.R.string;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.widget.ImageView;

public class AsyncImageLoader {

	// 内存缓存，SoftReference实现自动回收
	private HashMap<String, SoftReference<Bitmap>> imageCache = null;

	private final static String CACHE_PATH = Environment
			.getExternalStorageDirectory().getPath()
			+ "/Candidates"
			+ "/AsyncLoadImage";

	public AsyncImageLoader() {
		imageCache = new HashMap<String, SoftReference<Bitmap>>();
	}

	public Bitmap loadBitmap(final ImageView imageView, final String imageURL,
			final ImageCallBack imageCallBack) {

		/**
		 * 在内存缓存中，返回bitmap对象
		 */
		if (imageCache.containsKey(imageURL)) {
		
			SoftReference<Bitmap> reference = imageCache.get(imageURL);
			Bitmap bitmap = reference.get();
			if (bitmap != null) {
				imageCallBack.imageLoadSuccess(imageView, bitmap);
			//	imageView.setImageBitmap(bitmap);
				System.out.println("在内存缓存中找到");
				return bitmap;
			}
		} else {
			/**
			 * 在本地缓存中查找
			 */
			
			String bitmapName = imageURL
					.substring(imageURL.lastIndexOf("/") + 1);
			File cacheDir = new File(CACHE_PATH);
			File[] cacheFiles = cacheDir.listFiles();
			if (cacheFiles != null) {
				int i = 0;
				for (; i < cacheFiles.length; i++) {
					if (bitmapName.equals(cacheFiles[i].getName())) {
						break;
					}
				}
				// 找到该图片返回Bitmap
				if (i < cacheFiles.length) {
					Bitmap bitmap = BitmapFactory.decodeFile(CACHE_PATH + "/"
							+ bitmapName);
					imageCallBack.imageLoadSuccess(imageView, bitmap);
					System.out.println("在本地缓存中找到图片");
				//	imageView.setImageBitmap(bitmap);
					return bitmap;
				}
			}
		}
		/**
		 * 两个缓存都没有则开启线程进行下载
		 */
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (0 == msg.what) {
					imageCallBack.imageLoadSuccess(imageView, (Bitmap) msg.obj);
				} else if (1 == msg.what) {
					imageCallBack.imageLoadFaluire(imageView);
				}
			}
		};

		new Thread() {

			public void run() {
				Bitmap bitmap = null;
				try {
					System.out.println("下载图片");
					URL url = new URL(imageURL);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setReadTimeout(5000);
					conn.setRequestMethod("GET");
					InputStream bitmapIS = conn.getInputStream();
					bitmap = BitmapFactory.decodeStream(bitmapIS);
					bitmapIS.close();
					imageCache.put(imageURL, new SoftReference<Bitmap>(bitmap));
					Message msg = Message.obtain();
					msg.obj = bitmap;
					msg.what = 0;
					handler.sendMessage(msg);

				} catch (Exception e) {
					e.printStackTrace();
				}
				/**
				 * 将图片保存到本地
				 */
				if (bitmap != null) {
					File dir = new File(CACHE_PATH);
					System.out.println("文件夹已经创建");
					if (!dir.exists()) {
						dir.mkdirs();
					}
					File bitmapFile = new File(CACHE_PATH+"/"+ imageURL.substring(imageURL.lastIndexOf("/") + 1));
					if (!bitmapFile.exists()) {
						try {
							bitmapFile.createNewFile();
							FileOutputStream fos;

							fos = new FileOutputStream(bitmapFile);
							bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
									fos);
						   System.out.println("图片已经保存");
							fos.close();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} else {
					handler.sendEmptyMessage(1);
				}

			};
		}.start();
		
		return null;

	}

	public interface ImageCallBack {
		public void imageLoadSuccess(ImageView imageView, Bitmap bitmap);
		public void imageLoadFaluire(ImageView imageView);
	}

}
