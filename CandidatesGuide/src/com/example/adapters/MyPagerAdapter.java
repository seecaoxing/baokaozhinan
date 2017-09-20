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
	 * ��ʼ������Դ, ��View����
	 */
	public MyPagerAdapter(List<View> views) {
		this.views = views;
	}

	/**
	 * ��ViewPager��ɾ�������ж�Ӧ������View����
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {

		/*
		 * //�������Զ��� //����һ��AnimationSet���󣬲���ΪBoolean�ͣ�
		 * //true��ʾʹ��Animation��interpolator��false����ʹ���Լ��� AnimationSet
		 * animationSet = new AnimationSet(true);
		 * //����һ��AlphaAnimation���󣬲�������ȫ��͸���ȣ�����ȫ�Ĳ�͸�� AlphaAnimation
		 * alphaAnimation = new AlphaAnimation(1, 0); //���ö���ִ�е�ʱ��
		 * alphaAnimation.setDuration(500); //��alphaAnimation������ӵ�AnimationSet����
		 * animationSet.addAnimation(alphaAnimation);
		 * //ʹ��ImageView��startAnimation����ִ�ж���
		 * container.startAnimation(animationSet);
		 */
		((ViewPager) container).removeView(views.get(position));

	}

	/**
	 * ��ȡViewPager�ĸ���
	 */
	@Override
	public int getCount() {

		return views.size();
	}

	/**
	 * ��View�����л�ȡ��Ӧ������Ԫ��, ����ӵ�ViewPager��
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position), 0);
		return views.get(position);
	}

	/**
	 * �Ƿ���ʾ��ViewPagerҳ����instantiateItem���صĶ�����й��� ��������Ǳ���ʵ�ֵ�
	 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
}