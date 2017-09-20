package com.example.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.example.adapters.JsonReviewAdapter;
import com.example.candidatesguide.R;
import com.example.classinfo.ReviewClass;
import com.example.classinfo.ShareArticle;
import com.example.httpconnection.HttpConnection;
import com.example.httpconnection.HttpSendReview;
import com.example.jsons.JsonReview;

import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fragments.SlideCutListView;
import com.example.fragments.SlideCutListView.OnItemRemovedListener;
import com.example.fragments.SlideCutListView.SlideDirection;

/**
 * 用来填写评论，和显示评论的信息
 * 
 * @author see
 * 
 */
public class DataFragmentReview extends Activity {
	private EditText reviewEditText;
	private Button okButton, cancerButton;
	private SlideCutListView reviewLV;
	private String reviewString;
	private String resultString;
	private List<ReviewClass> reviewList = new ArrayList<ReviewClass>();
	private ShareArticle shareArticle = new ShareArticle();
	private JsonReviewAdapter jsonReviewAdapter;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x21) {
				// jsonReviewAdapter.notifyDataSetChanged();
				updateReview();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datafragmentreview);
		initView();
		shareArticle = (ShareArticle) getIntent().getSerializableExtra(
				"shareArticle");
		updateReview();

		okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				reviewString = reviewEditText.getText().toString();
				ReviewClass tempReview = new ReviewClass();
				tempReview.setFlagId(shareArticle.getArticleId());
				tempReview.setReviewString(reviewString);
				tempReview.setTypeString("it");
				tempReview.setTimeString(getTime());
				tempReview.save(getApplicationContext(), new SaveListener() {

					@Override
					public void onSuccess() {
						Toast.makeText(getApplicationContext(), "评论成功~",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						Toast.makeText(getApplicationContext(), "评论失败~",
								Toast.LENGTH_SHORT).show();
					}
				});
				handler.sendEmptyMessage(0x21);
			}
		});
		reviewLV.setOnItemRemovedListener(new OnItemRemovedListener() {

			@Override
			public void onItemRemoved(SlideDirection direction, int position) {
				ReviewClass reviewInfo = reviewList.get(position);
				reviewInfo.delete(getApplicationContext(),
						new DeleteListener() {
							@Override
							public void onSuccess() {
								Toast.makeText(getApplicationContext(),
										"删除成功~", Toast.LENGTH_SHORT).show();
							}

							@Override
							public void onFailure(int code, String msg) {
								Toast.makeText(getApplicationContext(),
										"删除失败~", Toast.LENGTH_SHORT).show();
							}
						});
				handler.sendEmptyMessage(0x21);
			}
		});

	}

	private void updateReview() {

		BmobQuery<ReviewClass> queryReview = new BmobQuery<ReviewClass>();
		queryReview.addWhereEqualTo("typeString", "it");
		queryReview.addWhereEqualTo("flagId", shareArticle.getArticleId());
		queryReview.findObjects(getApplicationContext(),
				new FindListener<ReviewClass>() {

					@Override
					public void onSuccess(List<ReviewClass> arg0) {

						Toast.makeText(getApplicationContext(), "更新成功~",
								Toast.LENGTH_SHORT).show();
						reviewList = arg0;
						jsonReviewAdapter = new JsonReviewAdapter(reviewList,
								getApplicationContext());
						reviewLV.setAdapter(jsonReviewAdapter);
					}

					@Override
					public void onError(int arg0, String arg1) {
						Toast.makeText(getApplicationContext(), "更新失败~",
								Toast.LENGTH_SHORT).show();
					}
				});

	}

	private void initView() {
		reviewEditText = (EditText) findViewById(R.id.review_edit);
		okButton = (Button) findViewById(R.id.ok_button);
		reviewLV = (SlideCutListView) findViewById(R.id.review_lv);
		// cancerButton = (Button) findViewById(R.id.cancer_button);

	}

	public String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日    HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);

		return str;

	}

}
