package com.example.candidatesguide;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.Sliding.SlidingMenu;
import com.example.adapters.MyPagerAdapter;
import com.example.classinfo.NewsClass;
import com.example.httpconnection.HttpConnection;
import com.example.jsons.JsonImage;
import com.example.jsons.JsonNews;
import com.example.listener.AdPageChangeListener;
import com.example.news.NewsOne;
import com.example.news.NewsThree;
import com.example.news.NewsTwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;

public class MenuActivity extends Activity {

	private ImageButton findButton, planButton, shareButton,
			introductionButton;
	private RelativeLayout find_RLayout, share_RLayout, plan_RLayout,
			introduction_RLayout;
	private LinearLayout pagerLayout;
	private int[] resId = { R.drawable.ad1, R.drawable.ad2, R.drawable.ad3 };
	private List<NewsClass> list_newClasses;
	private ImageView image_one;
	private ViewPager viewPager;
	private List<View> pagerViews;
	private ImageView[] imageViews;
	private ImageView imageView;
	private SlidingMenu slidingMenu;
	private ViewFlipper viewFlipper;
	private float startX;
	private boolean isContinue = true;
	private ViewGroup viewGroup;
	private ImageButton imageButton;
	private MyPagerAdapter myAdapter;
	private AtomicInteger atomicInteger = new AtomicInteger(0);
	private Thread imageThread;

	private final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x16) {
				viewPager.setCurrentItem(atomicInteger.get());
			}

			super.handleMessage(msg);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		Bmob.initialize(this, "613e247f1dfb373cfc03f90cf907b782");
		initview();
		automaticPager();
		initCirclePoint();
		myAdapter = new MyPagerAdapter(pagerViews);

		viewPager.setAdapter(myAdapter);

		AdPageChangeListener adPageChangeListener = new AdPageChangeListener(
				atomicInteger, imageViews, getApplicationContext());

		viewPager.setOnPageChangeListener(adPageChangeListener);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				slidingMenu.toggle();
			}
		});

		findButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MenuActivity.this,
						FindActivity.class);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "正在开发",
						Toast.LENGTH_SHORT).show();
			}
		});

		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MenuActivity.this,
						ShareActivity.class);
				startActivity(intent);
			}
		});

		planButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MenuActivity.this,
						PlanActivity.class);
				startActivity(intent);
			}
		});

		introductionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(MenuActivity.this,
						IntroductionActivity.class);
				startActivity(intent);
			}
		});

		imageThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (isContinue) {
					handler.sendEmptyMessage(0x16);
					atomicOption();
				}
			}
		});
		imageThread.start();
		// 实例化一个设置viewpager滚动的类
		com.example.listener.ViewPagerScroller scroller = new com.example.listener.ViewPagerScroller(
				getApplicationContext());
		// 设置页面切换事件为0秒
		scroller.setScrollDuration(1000);

		scroller.initViewPagerScroll(viewPager);

		pagerViews.get(0).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MenuActivity.this, NewsOne.class);
				Bundle bundle = new Bundle();

				/*
				 * Toast.makeText(getApplicationContext(), "请检查网络",
				 * Toast.LENGTH_SHORT).show();
				 */
				bundle.putSerializable("newsClass", list_newClasses.get(0));
				intent.putExtras(bundle);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "欢迎进入",
						Toast.LENGTH_SHORT).show();

			}
		});
		pagerViews.get(1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MenuActivity.this, NewsTwo.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("newsClass", list_newClasses.get(1));
				intent.putExtras(bundle);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "欢迎进入",
						Toast.LENGTH_SHORT).show();
			}
		});
		pagerViews.get(2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MenuActivity.this, NewsThree.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("newsClass", list_newClasses.get(2));
				intent.putExtras(bundle);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "欢迎进入",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void atomicOption() {
		atomicInteger.incrementAndGet();
		if (atomicInteger.get() > imageViews.length - 1) {
			atomicInteger.getAndAdd(-3);
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ImageView getImageView(int resId, int i) {

		ImageView image = new ImageView(this);
		JsonImage jsonImage = new JsonImage(list_newClasses.get(i).getPicUrl(),
				image, handler);
		jsonImage.start();
		image = jsonImage.getImage();
		// image.setImageResource(resId);
		// image.setBackgroundResource(resId);
		return image;
	}

	public ImageView getImageViewNull(int resId, int i) {
		System.out.println("广告栏图片为空 进入备用图片下载");
		ImageView image = new ImageView(this);
		image.setBackgroundResource(resId);
		return image;

	}

	public void automaticPager() {
		synchronized (this) {
			list_newClasses = new ArrayList<NewsClass>();
			BmobQuery<NewsClass> newsList = new BmobQuery<NewsClass>();
			newsList.findObjects(getApplicationContext(),
					new FindListener<NewsClass>() {

						@Override
						public void onSuccess(List<NewsClass> arg0) {
							System.out.println("已进入succes");
							list_newClasses = arg0;
							pagerViews = new ArrayList<View>();
							for (int i = 0; i < resId.length; i++) {
								pagerViews.add(getImageView(resId[i], i));

							}

						}

						@Override
						public void onError(int arg0, String arg1) {

							System.out.println("广告栏图片未下载");
							Toast.makeText(getApplicationContext(), "列表出错",
									Toast.LENGTH_SHORT).show();
							pagerViews = new ArrayList<View>();
							for (int i = 0; i < resId.length; i++) {
								pagerViews.add(getImageViewNull(resId[i], i));
							}
						}
					});
			// String urlString = "http://baokaozhinan.duapp.com/getpicture";
			// HttpConnection imageHttpConnection = new
			// HttpConnection(urlString,
			// "UTF-8");
			// imageHttpConnection.start();
			// try {
			// imageHttpConnection.join();
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
			String result = null;
			if (result == null) {
				System.out.println("使用备用图片");
				pagerViews = new ArrayList<View>();
				for (int i = 0; i < resId.length; i++) {
					pagerViews.add(getImageViewNull(resId[i], i));
				}
			} else {
				JsonNews jsonNews = new JsonNews(result);
				list_newClasses = jsonNews.getList_newsClasses();

				pagerViews = new ArrayList<View>();
				for (int i = 0; i < resId.length; i++) {
					pagerViews.add(getImageView(resId[i], i));

				}
			}
		}

	}

	private void initCirclePoint() {

		viewGroup = (ViewGroup) findViewById(R.id.viewGroup);
		imageViews = new ImageView[pagerViews.size()];
		// 广告栏的小圆点图标
		for (int i = 0; i < pagerViews.size(); i++) {
			// 创建一个ImageView,并设置宽高.将该对象放入到数组
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageViews[i] = imageView;

			// 初始值.默认第0个选中
			if (i == 0) {
				imageViews[i].setBackgroundResource(R.drawable.point_focused);

			} else {
				imageViews[i].setBackgroundResource(R.drawable.point_unfocused);
			}
			// 将小圆点放入到布局中
			viewGroup.addView(imageViews[i]);
		}
	}

	/*
	 * @Override public boolean onTouchEvent(MotionEvent event) {
	 * 
	 * switch (event.getAction()) {
	 * 
	 * case MotionEvent.ACTION_DOWN: { startX = event.getX(); break; } case
	 * MotionEvent.ACTION_MOVE: { if (event.getX() - startX > 100) {
	 * viewFlipper.setInAnimation(getApplicationContext(), R.anim.right_in);
	 * viewFlipper.setOutAnimation(getApplicationContext(), R.anim.right_out);
	 * viewFlipper.showPrevious(); return true; } else if (startX - event.getX()
	 * > 100) { viewFlipper.setInAnimation(getApplicationContext(),
	 * R.anim.left_in); viewFlipper.setOutAnimation(getApplicationContext(),
	 * R.anim.left_out); viewFlipper.showNext(); return true; } break; } case
	 * MotionEvent.ACTION_UP: {
	 * 
	 * break; } }
	 * 
	 * return super.onTouchEvent(event); }
	 */
	void initview() {
		findButton = (ImageButton) findViewById(R.id.find_menu_button);
		shareButton = (ImageButton) findViewById(R.id.share_menu_button);
		introductionButton = (ImageButton) findViewById(R.id.introduction_menu_button);
		planButton = (ImageButton) findViewById(R.id.plan_menu_button);
		imageButton = (ImageButton) findViewById(R.id.sliding_title_menu_ib);
		slidingMenu = (SlidingMenu) findViewById(R.id.sliding_menu);
		find_RLayout = (RelativeLayout) findViewById(R.id.find_menu_rlayout);
		share_RLayout = (RelativeLayout) findViewById(R.id.share_menu_rlayout);
		introduction_RLayout = (RelativeLayout) findViewById(R.id.introduction_menu_rlayout);
		plan_RLayout = (RelativeLayout) findViewById(R.id.plan_menu_rlayout);
		// pagerLayout = (LinearLayout) findViewById(R.id.viewpager_menu);
		viewPager = (ViewPager) findViewById(R.id.viewpager_menu);
	}

}
