package com.example.listener;

import java.util.concurrent.atomic.AtomicInteger;

import com.example.candidatesguide.R;

import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * ViewPagerҳ��ı������
 * @author see
 *
 */
public class AdPageChangeListener implements OnPageChangeListener{
	
	AtomicInteger atomicInteger;
	ImageView[] imageViews;
    Context context;
    
	public AdPageChangeListener(AtomicInteger atomicInteger,ImageView[] imageViews,Context context) {
	
		this.atomicInteger = atomicInteger;
		this.imageViews = imageViews;
		this.context = context;
	}

    
	/**
	 * ҳ�����״̬�����ı��ʱ�򴥷�
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
	
	}

	/**
	 * ҳ�������ʱ�򴥷�
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	
		
	}

	/**
	 * ҳ��ѡ�е�ʱ�򴥷�
	 */
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		//��ȡ��ǰ��ʾ��ҳ�����ĸ�ҳ��
		atomicInteger.getAndSet(arg0);
		//��������ԭ�㲼�ּ���
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[arg0].setBackgroundResource(R.drawable.point_focused);
			if (arg0!=i) {
				imageViews[i].setBackgroundResource(R.drawable.point_unfocused);
			}
		}
		//Toast.makeText(context, "sdfasf", Toast.LENGTH_SHORT).show();
		
	
	}


}
