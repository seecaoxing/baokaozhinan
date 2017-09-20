package com.example.listener;

import com.example.candidatesguide.R;

import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MyCheckChangeListener implements OnCheckedChangeListener {

	ViewPager viewPager;

	public MyCheckChangeListener(ViewPager viewPager) {
		// TODO Auto-generated constructor stub
		this.viewPager = viewPager;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.data_rb:
			viewPager.setCurrentItem(0);
			break;
		case R.id.other_rb:
			viewPager.setCurrentItem(1);
			break;
		}

	}
}
