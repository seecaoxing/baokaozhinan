package com.example.fragments;

import com.example.candidatesguide.R;
import com.example.classinfo.OtherArticle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class OtherFragmentShow extends Activity {

	private TextView other_articleContent;
	private ImageButton other_reviewButton, other_collectButton;

	private OtherArticle otherArticle = new OtherArticle();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.otherfragmentshow);
		initView();
		otherArticle = (OtherArticle) getIntent().getSerializableExtra("otherA");
		System.out.println("00000000000000000000:"+otherArticle.getOtherArticleConent());
		new Thread(new Runnable() {
			@Override
			public void run() {
				other_articleContent.setText(otherArticle.getOtherArticleConent());
				System.out.println("content“—æ≠œ‘ æ");
			}
		}).start();

		other_reviewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(OtherFragmentShow.this,
						OtherFragmentReview.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("otherArticle", otherArticle);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	public void initView() {

		other_articleContent = (TextView) findViewById(R.id.other_articleContents_show);
		other_collectButton = (ImageButton) findViewById(R.id.other_collect_button);
		other_reviewButton = (ImageButton) findViewById(R.id.other_review_button);
	}

}
