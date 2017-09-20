package com.example.listener;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * ���� ViewPager�����ٶ�
 * 
 */
public class ViewPagerScroller extends Scroller {

	private int mScrollDuration = 2000; // �����ٶ�

	/**
	 * �����ٶ��ٶ�
	 * 
	 * @param duration
	 */
	public void setScrollDuration(int duration) {
		this.mScrollDuration = duration;
	}

	public ViewPagerScroller(Context context) {
		super(context);
	}

	public ViewPagerScroller(Context context, Interpolator interpolator) {
		super(context, interpolator);
	}

	@SuppressLint("NewApi")
	public ViewPagerScroller(Context context, Interpolator interpolator,
			boolean flywheel) {
		super(context, interpolator, flywheel);
	}

	/**
	 * startX:������ʼ�ĵ��x 
	 * dx�����򻬶�ƫ����
	 */
	@Override
	public void startScroll(int startX, int startY, int dx, int dy, int duration) {
		super.startScroll(startX, startY, dx, dy, mScrollDuration);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy) {
		super.startScroll(startX, startY, dx, dy, mScrollDuration);
	}

	public void initViewPagerScroll(ViewPager viewPager) {
		try {
			Field mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);// ˽�б�������������AccessibleΪtrue
			mScroller.set(viewPager, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}