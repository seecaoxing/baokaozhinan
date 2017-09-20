package com.example.fragments;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.adapters.ShareOtherListAdapter;
import com.example.candidatesguide.R;
import com.example.classinfo.OtherArticle;
import com.example.classinfo.ShareArticle;
import com.example.httpconnection.HttpConnection;
import com.example.jsons.JsonShareArticle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class OtherFragment extends Fragment {

	private String httpData;
	private ListView listview_other;
	private List<OtherArticle> OtherArticle_list = new ArrayList<OtherArticle>();
	private View view;
	private int[] resId = { R.drawable.list_img1, R.drawable.list_img2,
			R.drawable.list_img3, R.drawable.list_img4 };
	private OtherArticle otherA = new OtherArticle();;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.otherfragment, container, false);
		listview_other = (ListView) view.findViewById(R.id.other_lv);

		BmobQuery<OtherArticle> query = new BmobQuery<OtherArticle>();

		query.findObjects(getActivity(), new FindListener<OtherArticle>() {

			@Override
			public void onSuccess(List<OtherArticle> list) {
				OtherArticle_list = list;
				ShareOtherListAdapter shareOtherListAdapter = new ShareOtherListAdapter(getActivity());
				shareOtherListAdapter.setData(OtherArticle_list);
				listview_other.setAdapter(shareOtherListAdapter);
			}

			@Override
			public void onError(int arg0, String arg1) {

			}
		});

		listview_other.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				otherA = OtherArticle_list.get(arg2);
				Intent intent = new Intent(getActivity(),
						OtherFragmentShow.class);

				Bundle bundle = new Bundle();

				bundle.putSerializable("otherA", otherA);
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});
		return view;
	}

	private void loadingData() {
		// TODO Auto-generated method stub
		OtherArticle otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(String.valueOf(resId[0]));
		otherArticle.setOtherArticleTitle("Android性能优化典范（三）");
		otherArticle.setOtherArticleIntro("Android性能优化典范的课程最近更新到第三季，共包含12个短视频");
		otherArticle
				.setOtherArticleConent("内容大致有：更高效的ArrayMap容器、使用Android系统提供的特殊容器来避免自动装箱、避免使用枚举类型、注意onLowMemory与onTrimMemory的回调等。");
		OtherArticle_list.add(otherArticle);

		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[1] + "");
		otherArticle.setOtherArticleTitle("ZUK Z1智能手机背后的硬件和软件技术");
		otherArticle
				.setOtherArticleIntro("喧嚣的智能手机市场又有新锐加入。目标是重度用户的ZUK首款手机Z1发布");
		otherArticle
				.setOtherArticleConent("硬件之外，软件也有很多创新：指纹识别的U-Touch功能，多维度递进自启防护引擎，面部优化算法，色彩还原，并承诺升级Android M，N，最终开源");
		OtherArticle_list.add(otherArticle);

		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[2] + "");
		otherArticle.setOtherArticleTitle("Android项目中如何用好构建神器Gradle？");
		otherArticle
				.setOtherArticleIntro("本文作者贾吉鑫为大众点评Android工程师，在进行团队并行开发时，分库遇到的问题很多都要通过Gradle脚本解决。");
		otherArticle
				.setOtherArticleConent("Gradle虽为构建神器，但学习曲线比较陡峭，要想在Android项目中用好Gradle必须要做到三点。");
		OtherArticle_list.add(otherArticle);

		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[3] + "");
		otherArticle.setOtherArticleTitle("《近匠》Remix周哲，让Android运行在“电脑”上！");
		otherArticle.setOtherArticleIntro("《近匠》第99期，Remix是技德科技今年推出的一款平板电脑，");
		otherArticle
				.setOtherArticleConent("与其他类似产品相比它重新改装了Android系统，使之可以运行在平板电脑上，旨在打破娱乐与办公的界限。");
		OtherArticle_list.add(otherArticle);
		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[0] + "");
		otherArticle.setOtherArticleTitle("iOS开发生涯的初恋：详解Objective-C多项改进");
		otherArticle
				.setOtherArticleIntro("对于许多iOS开发者而言，Objective-C就是开发生涯中的初恋。");
		otherArticle
				.setOtherArticleConent("如今，Swift正在以迅雷不及掩耳之势碾压Objective-C的份额，但Objective-C多项性能改进依然让开发者非常激动，本文作者对OC的提升进行了详解。");
		OtherArticle_list.add(otherArticle);

		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[1] + "");
		otherArticle.setOtherArticleTitle("C++是2015年上半年同比增长最快的编程语言");
		otherArticle
				.setOtherArticleIntro("具体为C++ 增长3.1%、Java 增长2.0%、C#增长1.6%、Python增长1.6%。");
		otherArticle
				.setOtherArticleConent("C++大幅度增长的原因可能是引入了新的C++11标准，这使得C++被大范围的接受。");
		OtherArticle_list.add(otherArticle);

	}
}
