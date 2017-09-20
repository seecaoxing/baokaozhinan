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

	// �ڴ滺�棬SoftReferenceʵ���Զ�����
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
		 * ���ڴ滺���У�����bitmap����
		 */
		if (imageCache.containsKey(imageURL)) {
		
			SoftReference<Bitmap> reference = imageCache.get(imageURL);
			Bitmap bitmap = reference.get();
			if (bitmap != null) {
				imageCallBack.imageLoadSuccess(imageView, bitmap);
			//	imageView.setImageBitmap(bitmap);
				System.out.println("���ڴ滺�����ҵ�");
				return bitmap;
			}
		} else {
			/**
			 * �ڱ��ػ����в���
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
				// �ҵ���ͼƬ����Bitmap
				if (i < cacheFiles.length) {
					Bitmap bitmap = BitmapFactory.decodeFile(CACHE_PATH + "/"
							+ bitmapName);
					imageCallBack.imageLoadSuccess(imageView, bitmap);
					System.out.println("�ڱ��ػ������ҵ�ͼƬ");
				//	imageView.setImageBitmap(bitmap);
					return bitmap;
				}
			}
		}
		/**
		 * �������涼û�������߳̽�������
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
					System.out.println("����ͼƬ");
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
				 * ��ͼƬ���浽����
				 */
				if (bitmap != null) {
					File dir = new File(CACHE_PATH);
					System.out.println("�ļ����Ѿ�����");
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
						   System.out.println("ͼƬ�Ѿ�����");
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
