package com.example.candidatesguide;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import com.example.classinfo.findClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class FindActivity extends Activity {

	private List<String> list_city, list_subject, list_batch;
	private Spinner spinner_city, spinner_subject, spinner_batch;
	private ArrayAdapter<String> arrayAdapter_city, arrayAdapter_subject,
			arrayAdapter_batch;
	private EditText score_EditText, position_EditText;
	private Button find_school_Button;
	private String city_nameString, subject_nameString, batch_nameString;
	private ImageButton back_title_ib;
	String urlfind = "";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find);
		initview();

		setCitySpinner();
		setSubjectSpinner();
		setBatchSpinner();

		find_school_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				findClass person = new findClass(null, null, null, null, null);
				person.setAddressString(city_nameString);

				person.setSubjectString(subject_nameString);

				person.setPositionString(position_EditText.getText().toString());

				person.setScoreString(score_EditText.getText().toString());

				person.setBatchString(batch_nameString);

				Intent intent = new Intent();

				intent.setClass(FindActivity.this, Find_ShowActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("personInfo", person);
				intent.putExtras(bundle);
				if (person.getPositionString().equals("")
						|| person.getScoreString().equals("")) {
					Toast.makeText(getApplicationContext(), "请输入正确",
							Toast.LENGTH_SHORT).show();
				} else {

					startActivity(intent);
				}

			}
		});
		back_title_ib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private void initview() {
		spinner_city = (Spinner) findViewById(R.id.city_spinner);
		spinner_subject = (Spinner) findViewById(R.id.subject_spinner);
		spinner_batch = (Spinner) findViewById(R.id.batch_spinner);
		score_EditText = (EditText) findViewById(R.id.my_score);
		position_EditText = (EditText) findViewById(R.id.my_position);
		find_school_Button = (Button) findViewById(R.id.find_school_find);
		back_title_ib = (ImageButton) findViewById(R.id.back_title_find_ib);
	}

	public void setBatchSpinner() {
		list_batch = new ArrayList<String>();
		list_batch.add("本科一批");
		list_batch.add("本科二批");
		arrayAdapter_batch = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list_batch);
		arrayAdapter_batch
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_batch.setAdapter(arrayAdapter_batch);
		spinner_batch.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				batch_nameString = arrayAdapter_batch.getItem(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	public void setCitySpinner() {
		// 1.设置数据源
		list_city = new ArrayList<String>();
		list_city.add("陕西");
		list_city.add("北京");
		list_city.add("上海");
		list_city.add("江苏");
		list_city.add("浙江");
		list_city.add("河北");
		list_city.add("黑龙江");
		// 2.新建ArratAdapter(数组适配器)
		arrayAdapter_city = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list_city);

		// 3.arrayAdapter设置下拉菜单样式
		arrayAdapter_city
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 4.spinner加载适配器
		spinner_city.setAdapter(arrayAdapter_city);

		// 5.spinner设置监听器
		spinner_city.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// String city_nameString = list.get(arg2);
				city_nameString = arrayAdapter_city.getItem(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void setSubjectSpinner() {
		list_subject = new ArrayList<String>();
		list_subject.add("理工");
		list_subject.add("文史");

		arrayAdapter_subject = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list_subject);

		arrayAdapter_subject
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_subject.setAdapter(arrayAdapter_subject);
		spinner_subject.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				subject_nameString = arrayAdapter_subject.getItem(arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

}
