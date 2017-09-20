package com.example.adapters;

import java.util.List;

import com.example.candidatesguide.R;
import com.example.classinfo.OtherArticle;
import com.example.jsons.JsonImage;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShareOtherListAdapter extends BaseAdapter {

	private Context context;
	LayoutInflater inflater;
	private List<OtherArticle> otherArticleList;
	private Handler handler = new Handler();

	public ShareOtherListAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<OtherArticle> otherArticleList) {
		this.otherArticleList = otherArticleList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return otherArticleList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return otherArticleList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder = null;
		if (arg1 == null) {

			arg1 = inflater.inflate(R.layout.item_share_other_article, null);
			holder = new Holder(arg1);
			arg1.setTag(holder);
		} else {
			holder = (Holder) arg1.getTag();
		}
		OtherArticle otherArticle = otherArticleList.get(arg0);
		holder.OtherArticle_title.setText(otherArticle.getOtherArticleTitle());
		holder.OtherArticle_intro.setText(otherArticle.getOtherArticleIntro());
		/*
		 * holder.OtherArticle_img.setImageResource(otherArticle
		 * .getOtherArtivleImg());
		 */
		JsonImage jsonImage = new JsonImage(otherArticle.getOtherArtivleImg(),
				holder.OtherArticle_img, handler);
		jsonImage.start();
		
		return arg1;
	}

	class Holder {
		private TextView OtherArticle_title;
		private TextView OtherArticle_intro;
		private ImageView OtherArticle_img;

		public Holder(View view) {
			OtherArticle_title = (TextView) view
					.findViewById(R.id.other_article_title);
			OtherArticle_intro = (TextView) view
					.findViewById(R.id.other_article_intro);
			OtherArticle_img = (ImageView) view
					.findViewById(R.id.share_other_img);

		}

	}

}
