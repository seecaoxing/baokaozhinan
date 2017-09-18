package com.example.candidatesguide;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.example.adapters.JsonSchoolAdapter;
import com.example.classinfo.SchoolInfo;
import com.example.jsons.JsonSchoolInfo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class IntroductionActivity extends Activity {

	private ListView listView;
	private EditText school_name_editText;
	private Button find_introduction_button;
	private TextView show_null_TextView;
	private ProgressDialog progressDialog;
	public static List<SchoolInfo> schoolInfos_list = new ArrayList<SchoolInfo>();
	private SchoolInfo sInfo = new SchoolInfo();

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (msg.what == 0x11) {
				progressDialog.dismiss();
			}
			if (msg.what == 0x12) {
				show_null_TextView.setText("对不起您输入的校名有误,请重新输入");
			}
			if (msg.what == 0x15) {
				// Thread.currentThread().notify();
			}

		};
	};
	JsonSchoolAdapter jsonSchoolAdapter;
	private Context context;
	String urlString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.introduction);
		school_name_editText = (EditText) findViewById(R.id.school_name_Edit);
		school_name_editText.setText("西安交通大学");
		show_null_TextView = (TextView) findViewById(R.id.show_null);
		find_introduction_button = (Button) findViewById(R.id.find_school_introduction);
		listView = (ListView) findViewById(R.id.list_introduction);
		jsonSchoolAdapter = new JsonSchoolAdapter(this);

		find_introduction_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				progressDialog = ProgressDialog.show(IntroductionActivity.this,
						"任务执行中", "任务执行,请等待");
				// TODO Auto-generated method stub
				progressDialog.setCancelable(true);
				show_null_TextView.setText("");
				String url = "http://schoolguide.sinaapp.com/getSchoolInf?name=";
				try {

					urlString = url
							+ URLEncoder.encode(school_name_editText.getText()
									.toString(), "utf-8");
					new JsonSchoolInfo(urlString, jsonSchoolAdapter, handler,
							listView, context, progressDialog, null).start();

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				sInfo = schoolInfos_list.get(arg2);
				System.out.println(sInfo.getSchool_adderss().toString());
				Intent intent = new Intent(IntroductionActivity.this,
						Introduction_ShowActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("sInfo", sInfo);
				intent.putExtras(bundle);
				startActivity(intent);
			}

		});

	}

}
