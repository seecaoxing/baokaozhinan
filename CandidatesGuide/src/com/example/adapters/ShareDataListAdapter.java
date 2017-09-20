package com.example.adapters;

import java.util.List;

import com.example.candidatesguide.R;
import com.example.classinfo.ShareArticle;
import com.example.httpconnection.AsyncImageLoader;
import com.example.httpconnection.AsyncImageLoader.ImageCallBack;
import com.example.jsons.JsonImage;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareDataListAdapter extends BaseAdapter {

	private Handler handler = new Handler();
	private List<ShareArticle> shareArticlesList;
	private LayoutInflater inflater;
	private AsyncImageLoader asyncImageLoader;

	public ShareDataListAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		asyncImageLoader = new AsyncImageLoader();

	}

	public void setShareArticlesList(List<ShareArticle> shareArticlesList) {
		this.shareArticlesList = shareArticlesList;
	}

	@Override
	public int getCount() {
		return shareArticlesList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return shareArticlesList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		Holder holder = null;
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.item_share_data_article, null);
			holder = new Holder(arg1);
			arg1.setTag(holder);
		} else {
			holder = (Holder) arg1.getTag();
		}
		ShareArticle shareArticle = shareArticlesList.get(arg0);
		holder.articleTitleTV.setText(shareArticle.getArticleTitle());
		holder.articleIntroTV.setText(shareArticle.getArticleIntro());
		holder.articleImg.setImageResource(R.drawable.ic_launcher);
		asyncImageLoader.loadBitmap(holder.articleImg, shareArticle.getArticleImg(), new ImageCallBack() {
			
			@Override
			public void imageLoadSuccess(ImageView imageView, Bitmap bitmap) {
				imageView.setImageBitmap(bitmap);
			}
			
			@Override
			public void imageLoadFaluire(ImageView imageView) {
			imageView.setImageResource(R.drawable.ic_launcher);	
			}
		});
		
		//JsonImage jsonImage = new JsonImage(shareArticle.getArticleImg(),holder.articleImg, handler);
		
		//jsonImage.start();

		return arg1;
	}

	static class Holder {

		private ImageView articleImg;
		private TextView articleTitleTV;
		private TextView articleIntroTV;

		public Holder(View view) {
			articleTitleTV = (TextView) view.findViewById(R.id.article_title);
			articleImg = (ImageView) view.findViewById(R.id.share_data_img);
			articleIntroTV = (TextView) view.findViewById(R.id.article_intro);
		}

	}

}
