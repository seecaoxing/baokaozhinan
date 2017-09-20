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
					Toast.makeText(getApplicationContext(), "��������ȷ",
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
		list_batch.add("����һ��");
		list_batch.add("���ƶ���");
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
		// 1.��������Դ
		list_city = new ArrayList<String>();
		list_city.add("����");
		list_city.add("����");
		list_city.add("�Ϻ�");
		list_city.add("����");
		list_city.add("�㽭");
		list_city.add("�ӱ�");
		list_city.add("������");
		// 2.�½�ArratAdapter(����������)
		arrayAdapter_city = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list_city);

		// 3.arrayAdapter���������˵���ʽ
		arrayAdapter_city
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 4.spinner����������
		spinner_city.setAdapter(arrayAdapter_city);

		// 5.spinner���ü�����
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
		list_subject.add("��");
		list_subject.add("��ʷ");

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
