package com.example.adapters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.Destroyable;

import com.example.adapters.ShareDataListAdapter.Holder;
import com.example.candidatesguide.R;
import com.example.classinfo.ReviewClass;
import com.example.classinfo.ShareArticle;
import com.example.jsons.JsonImage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class JsonReviewAdapter extends BaseAdapter {

	private List<ReviewClass> reviewList = new ArrayList<ReviewClass>();
	private Context context;
	private LayoutInflater inflater;

	public JsonReviewAdapter(List<ReviewClass> reviewList, Context context) {
		this.reviewList = reviewList;
		this.context = context;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return reviewList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return reviewList.get(arg0);
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
			arg1 = inflater.inflate(R.layout.item_review, null);
			holder = new Holder(arg1);
			arg1.setTag(holder);
		} else {
			holder = (Holder) arg1.getTag();
		}
		ReviewClass reviewClass = reviewList.get(arg0);
		holder.reviewTV.setText(reviewClass.getReviewString());
		holder.dateTV.setText(reviewClass.getTimeString());

		return arg1;
	}

	class Holder {

		private TextView reviewTV;
		private TextView dateTV;

		public Holder(View view) {
			reviewTV = (TextView) view.findViewById(R.id.itemReview);
			dateTV = (TextView) view.findViewById(R.id.itemReviewDate);
		}

	}

}
