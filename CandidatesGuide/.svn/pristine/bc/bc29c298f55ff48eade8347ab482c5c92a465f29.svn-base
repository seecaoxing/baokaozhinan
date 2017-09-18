package com.example.adapters;

import java.util.List;

import com.example.candidatesguide.R;
import com.example.classinfo.SchoolInfo;
import com.example.jsons.JsonImage;

import android.R.string;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class JsonSchoolAdapter extends BaseAdapter{

	/**
	 * 為校園json数据解析介绍ListView,设置适配器
	 */
	
	
	
	private List<SchoolInfo> lSchoolInfos;
	private LayoutInflater inflater;
	private Context context;
	private Handler handler = new Handler();
	public JsonSchoolAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		inflater=LayoutInflater.from(context);
	}
	public void setData(List<SchoolInfo> lsInfos){
		this.lSchoolInfos = lsInfos;
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lSchoolInfos.size();
		
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return lSchoolInfos.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		Holder holder = null;
		if (arg1==null) {
			arg1 = inflater.inflate(R.layout.item_schools, null);
			holder = new Holder(arg1);
			arg1.setTag(holder);
		}else {
			holder = (Holder) arg1.getTag();
		}
		
		SchoolInfo sInfo = lSchoolInfos.get(arg0);
		
		holder.name_TextView.setText(sInfo.getSchool_name());
		holder.address_TextView.setText(sInfo.getSchool_adderss());
		new JsonImage(sInfo.getSchool_imageurl(),holder.logo_ImageView,handler).start();
		
		return arg1;
	}
	
	class Holder{
		
		private ImageView logo_ImageView;
		private TextView name_TextView;
		private TextView address_TextView;
		private TextView introduction_TextView;
		
		public Holder(View view){
			logo_ImageView =(ImageView) view.findViewById(R.id.school_logo);
			name_TextView = (TextView) view.findViewById(R.id.school_name);
			address_TextView = (TextView) view.findViewById(R.id.school_address);
			introduction_TextView = (TextView) view.findViewById(R.id.school_introduction);		
		}	
	}

}
