package com.example.jsons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

public class JsonImage extends Thread {
	
	private ImageView imageView;
	
	private String urlImage;
	
	private Handler handler;
	
	
	public JsonImage(String urlImage,ImageView imageView,Handler handler) {
		// TODO Auto-generated constructor stub
	    this.imageView = imageView;
	    this.urlImage = urlImage;
	    this.handler = handler;
	    
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
        try {
			URL httpUrl = new URL(urlImage);
			HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
			httpURLConnection.setReadTimeout(5000);
			httpURLConnection.setRequestMethod("GET");
			
		    InputStream inputStream = httpURLConnection.getInputStream();
		    
		    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
		    
		    handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					 imageView.setImageBitmap(bitmap);
				}
			});
		   
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}

}
