package com.example.fragments;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.adapters.ShareDataListAdapter;
import com.example.candidatesguide.*;
import com.example.classinfo.ShareArticle;
import com.example.httpconnection.HttpConnection;
import com.example.jsons.JsonShareArticle;

import android.R.integer;
import android.R.raw;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DataFragment extends Fragment {

	private String httpData;
	private ListView listView_data;
	private List<ShareArticle> list_data = new ArrayList<ShareArticle>();
	private ShareArticle shareA = new ShareArticle();
	private int[] resId = { R.drawable.list_img1, R.drawable.list_img2,
			R.drawable.list_img3, R.drawable.list_img4 };
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/**
		 * resource：需要加载布局文件的id，意思是需要将这个布局文件中加载到Activity中来操作。
		 * 
		 * root：需要附加到resource资源文件的根控件，什么意思呢，就是inflate()会返回一个View对象，
		 * 如果第三个参数attachToRoot为true
		 * ，就将这个root作为根对象返回，否则仅仅将这个root对象的LayoutParams属性附加到resource对象的根布局对象上
		 * ，也就是布局文件resource的最外层的View上，比如是一个LinearLayout或者其它的Layout对象。
		 * 
		 * attachToRoot：是否将root附加到布局文件的根视图上
		 */
		view = inflater.inflate(
				com.example.candidatesguide.R.layout.datafragment, container,
				false);

		initview();
		BmobQuery<ShareArticle> query = new BmobQuery<ShareArticle>();

		query.findObjects(getActivity(), new FindListener<ShareArticle>() {

			@Override
			public void onSuccess(List<ShareArticle> list) {

				list_data = list;
				System.out.println("success~~");

				ShareDataListAdapter shareDataListAdapter = new ShareDataListAdapter(
						getActivity());

				shareDataListAdapter.setShareArticlesList(list_data);

				listView_data.setAdapter(shareDataListAdapter);

			}

			@Override
			public void onError(int arg0, String arg1) {
				System.out.println("error~~");

			}
		});

		listView_data.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				shareA = list_data.get(arg2);
				System.out.println("<<<<<<<<<<<<<<<<<<<<<"
						+ shareA.getArticleContents());
				Intent intent = new Intent(getActivity(),
						DataFragmentShow.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("shareA", shareA);
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});
		return view;
	}

	private ImageView getImageView(int resId) {
		ImageView imageView = new ImageView(getActivity());
		imageView.setImageResource(resId);
		return imageView;
	}

	private void initview() {
		listView_data = (ListView) view.findViewById(R.id.share_data_list);

	}
}
