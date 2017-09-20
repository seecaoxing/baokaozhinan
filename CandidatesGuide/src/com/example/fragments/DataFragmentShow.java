package com.example.fragments;

import com.example.candidatesguide.R;
import com.example.classinfo.ShareArticle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DataFragmentShow extends Activity {

	private ShareArticle shareArticle = new ShareArticle();
	private ImageButton collectButton,reviewButton;
	private TextView articelContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datafragmentshow);
		initview();
		shareArticle = (ShareArticle) getIntent().getSerializableExtra("shareA");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				articelContent.setText(shareArticle.getArticleContents());
			}
		}).start();
		
		reviewButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(DataFragmentShow.this,DataFragmentReview.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("shareArticle", shareArticle);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}
	private void initview(){
		articelContent = (TextView) findViewById(R.id.articleContents_show);
		collectButton = (ImageButton) findViewById(R.id.collect_button);
		reviewButton = (ImageButton) findViewById(R.id.review_button);
	}
}
