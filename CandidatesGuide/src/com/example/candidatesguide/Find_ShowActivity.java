package com.example.candidatesguide;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.adapters.ListFindShowAdapter;
import com.example.classinfo.NewsClass;
import com.example.classinfo.SchoolFind;
import com.example.classinfo.SchoolInfo;
import com.example.classinfo.SchoolInfoFindShow;
import com.example.classinfo.findClass;
import com.example.httpconnection.HttpConnection;
import com.example.jsons.JsonFindClass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Find_ShowActivity extends Activity {
	private List<SchoolInfoFindShow> listfindshow;
	private String result;
	private ListFindShowAdapter listFindShowAdapter;
	private ListView listView_findshow;
	private List<SchoolFind> listSchoolFind = new ArrayList<SchoolFind>();
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x13) {

			}
		};
	};
	private findClass person_info = new findClass(null, null, null, null, null);

	@SuppressLint("UseValueOf")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_show);
		listView_findshow = (ListView) findViewById(R.id.list_find_show);
		person_info = (findClass) getIntent()
				.getSerializableExtra("personInfo");
		BmobQuery<SchoolFind> querySchoolFind = new BmobQuery<SchoolFind>();
		querySchoolFind.addWhereEqualTo("subject",person_info.getSubjectString());
		querySchoolFind.addWhereEqualTo("batch", person_info.getBatchString());
		querySchoolFind.addWhereEqualTo("studentAddress",person_info.getAddressString());
		querySchoolFind.addWhereGreaterThanOrEqualTo("lowRanked", new Integer(person_info.getPositionString()));
		querySchoolFind.findObjects(getApplicationContext(),
				new FindListener<SchoolFind>() {
					@Override
					public void onSuccess(List<SchoolFind> arg0) {
						listSchoolFind = arg0;
						listFindShowAdapter = new ListFindShowAdapter(getApplicationContext());
						listFindShowAdapter.setData(listSchoolFind);
						listView_findshow.setAdapter(listFindShowAdapter);
					}

					@Override
					public void onError(int arg0, String arg1) {
						Toast.makeText(getApplicationContext(),
								"00000000000000", Toast.LENGTH_SHORT).show();
					}
				});
		listView_findshow.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				SchoolFind schoolFind = listSchoolFind.get(position);
				Intent intent = new Intent(Find_ShowActivity.this,
						SchoolInfoShowActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("schoolFind", schoolFind);
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});

	}

}
