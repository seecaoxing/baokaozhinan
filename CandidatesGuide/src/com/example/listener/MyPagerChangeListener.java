package com.example.listener;

import com.example.candidatesguide.R;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioGroup;

public class MyPagerChangeListener implements OnPageChangeListener {

	private RadioGroup rGroup;

	public MyPagerChangeListener(RadioGroup rGroup) {
		this.rGroup = rGroup;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			rGroup.check(R.id.data_rb);
			break;
		case 1:
			rGroup.check(R.id.other_rb);
			break;
		}

	}

}
