package com.example.candidatesguide;

import com.example.classinfo.SchoolInfo;
import com.example.jsons.JsonImage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 从IntroductionActivity中的listview获得点击事件 传过来一个SchoolInfo对象
 * 在这个页面显示这个对象的学校描述信息
 * @author see
 *
 */
public class Introduction_ShowActivity extends Activity {

	private TextView school_msg_tView;
	private SchoolInfo sInfo_show = new SchoolInfo();

	private ImageView schoolLogo;
	private TextView schoolCode;
	private TextView schoolAddress;
	private TextView schoolName;
	private TextView schoolIntroduce;

	Handler handler = new Handler(){
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schoolinfoshow);
		initView();
		sInfo_show = (SchoolInfo) getIntent().getSerializableExtra("sInfo");

		new Thread(new Runnable() {

			@Override
			public void run() {
				Message message = new Message();
				message.obj = sInfo_show;
				message.what = 0x11;
				handler.sendMessage(message);
			}
		}).start();
		
		
		
		
		
	}
	private void initView() {
		schoolLogo = (ImageView) findViewById(R.id.schoollogo_schoolinfoshow);
		schoolCode = (TextView) findViewById(R.id.schoolcode_schoolinfoshow);
		schoolAddress = (TextView) findViewById(R.id.schooladdress_schoolinfoshow);
		schoolName = (TextView) findViewById(R.id.schoolname_schoolinfoshow);
		schoolIntroduce = (TextView) findViewById(R.id.schoolintroduce_schoolinfoshow);
		school_msg_tView = (TextView) findViewById(R.id.school_msg_show);
	}
	
}
