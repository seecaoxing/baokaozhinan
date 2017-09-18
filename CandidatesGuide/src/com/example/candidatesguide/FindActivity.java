package com.example.candidatesguide;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.Spinner;

public class FindActivity extends Activity implements OnItemSelectedListener{
	
	
	private List<String> list;
	private Spinner spinner;
	private ArrayAdapter<String> arrayAdapter;
	private EditText score_EditText,position_EditText;
	private Button find_school_Button;
	private String city_nameString;
	String urlfind = "";
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find);
		spinner = (Spinner) findViewById(R.id.city_spinner);
		score_EditText = (EditText) findViewById(R.id.my_score);
		position_EditText = (EditText) findViewById(R.id.my_position);
		find_school_Button = (Button) findViewById(R.id.find_school_find);
		//1.��������Դ
		list = new ArrayList<String>();
		list.add("����");
		list.add("�Ϻ�");
		list.add("���");
		list.add("����");
		list.add("����");
		list.add("ɽ��");
		
		//2.�½�ArratAdapter(����������)
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
		
		//3.arrayAdapter���������˵���ʽ
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
		//4.spinner����������
		spinner.setAdapter(arrayAdapter);
		
		//5.spinner���ü�����
		spinner.setOnItemSelectedListener(this);
			
		find_school_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				Intent intent = new Intent();
				intent.setClass(FindActivity.this, Find_ShowActivity.class);
				Bundle bundle  = new Bundle();
				bundle.putString("city_name", city_nameString);
				bundle.putString("score", score_EditText.getText().toString());
				bundle.putString("position", position_EditText.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		//String city_nameString = list.get(arg2);
		 city_nameString = arrayAdapter.getItem(arg2);
		 System.out.println(city_nameString);
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
