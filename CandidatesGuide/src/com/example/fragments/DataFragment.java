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
		 * resource����Ҫ���ز����ļ���id����˼����Ҫ����������ļ��м��ص�Activity����������
		 * 
		 * root����Ҫ���ӵ�resource��Դ�ļ��ĸ��ؼ���ʲô��˼�أ�����inflate()�᷵��һ��View����
		 * �������������attachToRootΪtrue
		 * ���ͽ����root��Ϊ�����󷵻أ�������������root�����LayoutParams���Ը��ӵ�resource����ĸ����ֶ�����
		 * ��Ҳ���ǲ����ļ�resource��������View�ϣ�������һ��LinearLayout����������Layout����
		 * 
		 * attachToRoot���Ƿ�root���ӵ������ļ��ĸ���ͼ��
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
