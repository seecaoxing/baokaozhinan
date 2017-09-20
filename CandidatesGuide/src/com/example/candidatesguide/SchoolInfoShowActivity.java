package com.example.candidatesguide;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.classinfo.SchoolFind;
import com.example.classinfo.SchoolInfo;
import com.example.jsons.JsonImage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class SchoolInfoShowActivity extends Activity {

	private SchoolFind schoolFind = new SchoolFind();
	private SchoolInfo schoolInfo = new SchoolInfo();
	private ImageView schoolLogo;
	private TextView schoolName, schoolAddress, schoolCode, schoolIntroduce;
	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x11) {
				SchoolInfo sInfo = (SchoolInfo) msg.obj;
				JsonImage jsonImage = new JsonImage(sInfo.getSchool_imageurl(),
						schoolLogo, handler);
				jsonImage.start();
				schoolName.setText(sInfo.getSchool_name());
				schoolAddress.setText(sInfo.getSchool_adderss());
				schoolCode.setText(sInfo.getSchool_Code());
				schoolIntroduce.setText(sInfo.getSchool_introduction());

			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schoolinfoshow);
		initView();
		schoolFind = (SchoolFind) getIntent().getExtras().getSerializable("schoolFind");
       System.out.println(schoolFind.getSchoolName());
		BmobQuery<SchoolInfo> querySchoolInfo = new BmobQuery<SchoolInfo>();

		querySchoolInfo.addWhereEqualTo("school_NameString",
				schoolFind.getSchoolName());

		querySchoolInfo.findObjects(getApplicationContext(),
				new FindListener<SchoolInfo>() {

					@Override
					public void onError(int arg0, String arg1) {

					}

					@Override
					public void onSuccess(List<SchoolInfo> arg0) {

						schoolInfo = arg0.get(0);
						System.out.println(schoolInfo.getSchool_adderss());
						
						
						new Thread() {
							@Override
							public void run() {
								Message message = new Message();
								message.obj = schoolInfo;
								message.what = 0x11;
								handler.sendMessage(message);
							}
						}.start();

					}
				});

	}

	private void initView() {
		schoolLogo = (ImageView) findViewById(R.id.schoollogo_schoolinfoshow);
		schoolCode = (TextView) findViewById(R.id.schoolcode_schoolinfoshow);
		schoolAddress = (TextView) findViewById(R.id.schooladdress_schoolinfoshow);
		schoolName = (TextView) findViewById(R.id.schoolname_schoolinfoshow);
		schoolIntroduce = (TextView) findViewById(R.id.schoolintroduce_schoolinfoshow);

	}

}
