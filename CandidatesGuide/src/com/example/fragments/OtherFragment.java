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
		otherArticle.setOtherArticleTitle("Android�����Ż��䷶������");
		otherArticle.setOtherArticleIntro("Android�����Ż��䷶�Ŀγ�������µ���������������12������Ƶ");
		otherArticle
				.setOtherArticleConent("���ݴ����У�����Ч��ArrayMap������ʹ��Androidϵͳ�ṩ�����������������Զ�װ�䡢����ʹ��ö�����͡�ע��onLowMemory��onTrimMemory�Ļص��ȡ�");
		OtherArticle_list.add(otherArticle);

		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[1] + "");
		otherArticle.setOtherArticleTitle("ZUK Z1�����ֻ������Ӳ�����������");
		otherArticle
				.setOtherArticleIntro("�����������ֻ��г�����������롣Ŀ�����ض��û���ZUK�׿��ֻ�Z1����");
		otherArticle
				.setOtherArticleConent("Ӳ��֮�⣬���Ҳ�кܶഴ�£�ָ��ʶ���U-Touch���ܣ���ά�ȵݽ������������棬�沿�Ż��㷨��ɫ�ʻ�ԭ������ŵ����Android M��N�����տ�Դ");
		OtherArticle_list.add(otherArticle);

		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[2] + "");
		otherArticle.setOtherArticleTitle("Android��Ŀ������úù�������Gradle��");
		otherArticle
				.setOtherArticleIntro("�������߼ּ���Ϊ���ڵ���Android����ʦ���ڽ����ŶӲ��п���ʱ���ֿ�����������ܶ඼Ҫͨ��Gradle�ű������");
		otherArticle
				.setOtherArticleConent("Gradle��Ϊ������������ѧϰ���߱Ƚ϶��ͣ�Ҫ����Android��Ŀ���ú�Gradle����Ҫ�������㡣");
		OtherArticle_list.add(otherArticle);

		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[3] + "");
		otherArticle.setOtherArticleTitle("��������Remix���ܣ���Android�����ڡ����ԡ��ϣ�");
		otherArticle.setOtherArticleIntro("����������99�ڣ�Remix�Ǽ��¿Ƽ������Ƴ���һ��ƽ����ԣ�");
		otherArticle
				.setOtherArticleConent("���������Ʋ�Ʒ��������¸�װ��Androidϵͳ��ʹ֮����������ƽ������ϣ�ּ�ڴ���������칫�Ľ��ޡ�");
		OtherArticle_list.add(otherArticle);
		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[0] + "");
		otherArticle.setOtherArticleTitle("iOS�������ĵĳ��������Objective-C����Ľ�");
		otherArticle
				.setOtherArticleIntro("�������iOS�����߶��ԣ�Objective-C���ǿ��������еĳ�����");
		otherArticle
				.setOtherArticleConent("���Swift������Ѹ�ײ����ڶ�֮����ѹObjective-C�ķݶ��Objective-C�������ܸĽ���Ȼ�ÿ����߷ǳ��������������߶�OC��������������⡣");
		OtherArticle_list.add(otherArticle);

		otherArticle = new OtherArticle();
		otherArticle.setOtherArtivleImg(resId[1] + "");
		otherArticle.setOtherArticleTitle("C++��2015���ϰ���ͬ���������ı������");
		otherArticle
				.setOtherArticleIntro("����ΪC++ ����3.1%��Java ����2.0%��C#����1.6%��Python����1.6%��");
		otherArticle
				.setOtherArticleConent("C++�����������ԭ��������������µ�C++11��׼����ʹ��C++����Χ�Ľ��ܡ�");
		OtherArticle_list.add(otherArticle);

	}
}
