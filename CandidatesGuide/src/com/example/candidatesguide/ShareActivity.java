package com.example.candidatesguide;

import java.util.ArrayList;
import java.util.List;

import com.example.adapters.MyFragmentAdapter;
import com.example.fragments.DataFragment;
import com.example.fragments.OtherFragment;
import com.example.listener.MyCheckChangeListener;
import com.example.listener.MyPagerChangeListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.RadioGroup;

public class ShareActivity extends FragmentActivity {

	private List<Fragment> listFragments;
	private ViewPager mViewPager;
	private RadioGroup radioGroup;
	private ImageButton iButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		radioGroup = (RadioGroup) findViewById(R.id.rgroup);
		listFragments = new ArrayList<Fragment>();
		listFragments.add(new DataFragment());
		listFragments.add(new OtherFragment());
        iButton = (ImageButton) findViewById(R.id.back_title_share_ib);
		
        iButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				finish();
			}
		});
        
		MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(
				getSupportFragmentManager(), listFragments);

		mViewPager.setAdapter(myFragmentAdapter);

		MyCheckChangeListener myCheckChangeListener = new MyCheckChangeListener(
				mViewPager);

		radioGroup.setOnCheckedChangeListener(myCheckChangeListener);

		MyPagerChangeListener myPagerChangeListener = new MyPagerChangeListener(
				radioGroup);

		mViewPager.setOnPageChangeListener(myPagerChangeListener);

		mViewPager.setOffscreenPageLimit(2);

		radioGroup.check(R.id.data_rb);
		
		mViewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return false;
			}
		});
	}

}
