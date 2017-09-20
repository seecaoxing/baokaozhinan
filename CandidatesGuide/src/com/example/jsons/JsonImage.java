package com.example.jsons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.candidatesguide.R;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.widget.ImageView;

public class JsonImage extends Thread {

	private ImageView imageView;

	private String urlImage;

	private Handler handler;
	private Bitmap bitmap;
	private BitmapDrawable bitmapDrawable;

	public JsonImage(String urlImage, ImageView imageView, Handler handler) {
		this.imageView = imageView;
		this.urlImage = urlImage;
		this.handler = handler;
	}

	public ImageView getImage() {
		return imageView;
	}

	public BitmapDrawable getBitmapDrawable() {

		return bitmapDrawable;
	}

	/**
	 * 为了清楚内存溢出的问题；
	 * 
	 * @param bitmap
	 */
	public void distoryBitmap(Bitmap bitmap) {
		if (bitmap != null) {
			bitmap.recycle();
			System.gc();
		}
	}

	@Override
	public void run() {
		try {
			URL httpUrl = new URL(urlImage);
			HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
			httpURLConnection.setReadTimeout(5000);
			httpURLConnection.setRequestMethod("GET");
			InputStream inputStream = httpURLConnection.getInputStream();
			distoryBitmap(bitmap);
			BitmapFactory.Options opts = new BitmapFactory.Options();

			// opts.inJustDecodeBounds = true;//如果设为true返回的只是bitmap的长宽

			// BitmapFactory.decodeStream(inputStream, null, opts);

			// opts.inSampleSize = computeSampleSize(opts, -1, 128*128);
               opts.inSampleSize = 5;
			// opts.inJustDecodeBounds = false;

			bitmap = BitmapFactory.decodeStream(inputStream, null, opts);
			handler.post(new Runnable() {
				@SuppressLint("NewApi")
				@Override
				public void run() {
					imageView.setImageBitmap(bitmap);
				}
			});

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			System.out.println("又是加载图片加载出错了！！");
		}
	}

	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);
		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));
		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}
		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}
}
