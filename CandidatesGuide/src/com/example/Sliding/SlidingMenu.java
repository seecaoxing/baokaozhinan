package com.example.Sliding;

import java.awt.Scrollbar;

import com.example.candidatesguide.R;
import com.nineoldandroids.view.ViewHelper;

import android.app.Notification.Action;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class SlidingMenu extends HorizontalScrollView {

	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContext;
	private int mScreenWidth;
	private Scroller mScroller;
	private int mMenuWidth;
	// dp
	private int mMenuRightPadding;
	private boolean once = false;
	private boolean isOpen = false;
	//��¼�ϴεĻ�������
	private int mLastX = 0;
	private int mLastY = 0;
	//�ֱ��¼�ϴε� x y
	private int mLastXIntercept = 0;
	private int mLastYIntercept = 0;

	/**
	 * δʹ���Զ���������,����
	 * 
	 * @param context
	 * @param attrs
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * ��ʹ�����Զ�������ʱ,����ô˹��췽��
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// ��ȡ���Ƕ��������
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyle, 0);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.SlidingMenu_rightPadding:
				mMenuRightPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50, context
										.getResources().getDisplayMetrics()));
				break;

			}
		}
		a.recycle();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		mScroller = new Scroller(getContext());
	}

	public SlidingMenu(Context context) {
		this(context, null);
	}

	/**
	 * ������view�Ŀ�͸�,�����Լ��Ŀ�͸�
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		if (!once) {
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContext = (ViewGroup) mWapper.getChildAt(1);
			mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth
					- mMenuRightPadding;
			mContext.getLayoutParams().width = mScreenWidth;
			once = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	// ͨ������ƫ����,��menu����
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			isOpen = false;
			break;
		case MotionEvent.ACTION_UP:
			// ��������ߵĿ��
			int scrollX = getScrollX();
			
			if (scrollX >= mMenuWidth / 2) {
				this.smoothScrollTo(mMenuWidth, 0);
				isOpen = false;

			} else {
				this.smoothScrollTo(0, 0);
				isOpen = true;
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}

	public void openMenu() {
		if (isOpen) {
			return;
		}
		this.smoothScrollTo(0, 0);
		isOpen = true;

	}

	public void closeMenu() {
		if (!isOpen) {
			return;
		}
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen = false;
	}

	/**
	 * �л��˵�
	 */
	public void toggle() {
		if (isOpen) {
			closeMenu();
		} else {
			openMenu();
		}
	}

	/**
	 * ��������ʱ ����ʽ�໬,��Ҫ��������� �ӵײ������Ĳ໬
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l * 1.0f / mMenuWidth;
		// 1~0
		// �������Զ���,����TranslationX
		ViewHelper.setTranslationX(mMenu, mMenuWidth * scale);
	}

	/**
	 * QQ5.0ʽ�໬: ����1: ��������1.0~0.7 ���ŵ�Ч�� scale:1.0~0.0 0.7 + 0.3 * scale ����2:
	 * �˵���ƫ������Ҫ�޸� ����3: �˵�����ʾʱ�������Լ�͸���ȱ仯 ����:0.7~1.0 1.0 - scale * 0.3 ͸����:0.6~1.0
	 * 0.6 + 0.4 * (1-scale);
	 */

	// @Override
	// protected void onScrollChanged(int l, int t, int oldl, int oldt) {
	//
	// super.onScrollChanged(l, t, oldl, oldt);
	//
	// float scale = l * 1.0f / mMenuWidth;
	// // 1~0
	//
	// float rightScale = (float) (0.7 + 0.3 * scale);
	//
	// float leftScale = 1.0f -scale *0.3f;
	//
	// float leftAlpha = (float) (0.6 + 0.4 * (1-scale));
	//
	// // �������Զ���,����TranslationX
	// ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.7f);
	//
	// ViewHelper.setScaleX(mMenu, leftScale);
	// ViewHelper.setScaleY(mMenu, leftScale);
	// ViewHelper.setAlpha(mMenu, leftAlpha);
	// // ����content���������ĵ�
	// ViewHelper.setPivotX(mContext, 0);
	// ViewHelper.setPivotY(mContext, mContext.getHeight() / 2);
	//
	// ViewHelper.setScaleX(mContext, rightScale);
	// ViewHelper.setScaleY(mContext, rightScale);
	//
	//
	// }
	/**
	 * ʵ���ڱ�Ե������Ч�໬ ������true��ػ�����Χ�������Դ������¼� ������false�������¼�������view�Ŀؼ�����
	 */
/*	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean intercepted = false;
		int x = (int) ev.getX();
		int y = (int) ev.getY();
		int x1 = 0;
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x1 = (int) ev.getX();
			if (x1 == 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}*/
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean intercepted = false;
		int x = (int) ev.getX();
		int y = (int) ev.getY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			intercepted = false;
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
			intercepted= true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaX = x -mLastXIntercept;
			int deltaY = y - mLastYIntercept;
			if (deltaX>deltaY) {
				intercepted = true;
			}else {
				intercepted = false;
			}
			break;
		case MotionEvent.ACTION_UP:
			intercepted = false;
			break;
		default:
			break;
		}
		System.out.println("LOG:"+intercepted);
		mLastXIntercept = x;
		mLastYIntercept = y;
		return intercepted;
//		return super.onInterceptTouchEvent(ev);
		
	}

}
