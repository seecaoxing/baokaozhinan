package com.example.adapters;

import java.util.List;

import com.example.candidatesguide.R;
import com.example.classinfo.SchoolInfo;
import com.example.jsons.JsonImage;

import android.R.string;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class JsonSchoolAdapter extends BaseAdapter {

	/**
	 * 為校園json数据解析介绍ListView,设置适配器
	 */

	private List<SchoolInfo> lSchoolInfos;
	private LayoutInflater inflater;
	private Context context;
	private Handler handler;

	/**
	 * 类似于findViewById()不同的是LayoutInflater用来找res/layout/下的xml布局文件，并实例化
	 * 
	 * @param context
	 */
	public JsonSchoolAdapter(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<SchoolInfo> lsInfos) {
		this.lSchoolInfos = lsInfos;

	}

	@Override
	public int getCount() {
		return lSchoolInfos.size();

	}

	@Override
	public Object getItem(int arg0) {
		return lSchoolInfos.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder = null;
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.item_schools, null);
			/**
			 * view中的setTag(object)表示给view添加一个格外的数据， 以后可以用getTag()将这个数据取出；
			 */
			holder = new Holder(arg1);
			arg1.setTag(holder);
		} else {
			holder = (Holder) arg1.getTag();
		}

		SchoolInfo sInfo = lSchoolInfos.get(arg0);
		ImageView imageView = new ImageView(context);
		holder.name_TextView.setText(sInfo.getSchool_name());
		holder.address_TextView.setText(sInfo.getSchool_adderss());
		holder.code_TextView.setText(sInfo.getSchool_Code());
		JsonImage jsonImage = new JsonImage(sInfo.getSchool_imageurl(),
				holder.logo_ImageView, handler);
		jsonImage.start();
		holder.logo_ImageView.setImageDrawable(jsonImage.getBitmapDrawable());

		return arg1;
	}

	class Holder {

		private ImageView logo_ImageView;
		private TextView name_TextView;
		@SuppressLint("NewApi")
		private TextView address_TextView;
		private TextView code_TextView;

		public Holder(View view) {
			logo_ImageView = (ImageView) view.findViewById(R.id.school_logo);
			name_TextView = (TextView) view.findViewById(R.id.school_name);
			address_TextView = (TextView) view
					.findViewById(R.id.school_address);
			code_TextView = (TextView) view.findViewById(R.id.school_code);

		}
	}

}
