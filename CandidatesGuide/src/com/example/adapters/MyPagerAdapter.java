package com.example.adapters;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;

public class MyPagerAdapter extends PagerAdapter {

	private List<View> views = new ArrayList<View>();

	/**
	 * 初始化数据源, 即View数组
	 */
	public MyPagerAdapter(List<View> views) {
		this.views = views;
	}

	/**
	 * 从ViewPager中删除集合中对应索引的View对象
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {

		/*
		 * //设置属性动画 //创建一个AnimationSet对象，参数为Boolean型，
		 * //true表示使用Animation的interpolator，false则是使用自己的 AnimationSet
		 * animationSet = new AnimationSet(true);
		 * //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明 AlphaAnimation
		 * alphaAnimation = new AlphaAnimation(1, 0); //设置动画执行的时间
		 * alphaAnimation.setDuration(500); //将alphaAnimation对象添加到AnimationSet当中
		 * animationSet.addAnimation(alphaAnimation);
		 * //使用ImageView的startAnimation方法执行动画
		 * container.startAnimation(animationSet);
		 */
		((ViewPager) container).removeView(views.get(position));

	}

	/**
	 * 获取ViewPager的个数
	 */
	@Override
	public int getCount() {

		return views.size();
	}

	/**
	 * 从View集合中获取对应索引的元素, 并添加到ViewPager中
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position), 0);
		return views.get(position);
	}

	/**
	 * 是否将显示的ViewPager页面与instantiateItem返回的对象进行关联 这个方法是必须实现的
	 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
}