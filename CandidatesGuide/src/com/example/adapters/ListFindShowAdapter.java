package com.example.adapters;

import java.util.List;

import com.example.candidatesguide.R;
import com.example.classinfo.SchoolFind;
import com.example.classinfo.SchoolInfoFindShow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListFindShowAdapter extends BaseAdapter {

	private List<SchoolFind> listSchoolFind;

	private LayoutInflater layoutInflater;

	public ListFindShowAdapter(Context context) {
		layoutInflater = LayoutInflater.from(context);
	}

	public void setData(List<SchoolFind> listSchoolFind) {
		this.listSchoolFind = listSchoolFind;
	}

	@Override
	public int getCount() {
		return listSchoolFind.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listSchoolFind.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		Holder holder = null;
		if (arg1 == null) {
			arg1 = layoutInflater.inflate(R.layout.item_list_find_show, null);
			holder = new Holder(arg1);
			arg1.setTag(holder);
		} else {
			holder = (Holder) arg1.getTag();
		}

		SchoolFind schoolFind = listSchoolFind.get(arg0);
		holder.universityName_item.setText(schoolFind.getSchoolName()
				.toString());
		holder.universityType_item.setText(schoolFind.getSubject().toString());
		holder.minPosion_item.setText("在"+schoolFind.getStudentAddress()+"录取最低位次:"+schoolFind.getLowRanked().toString());

		return arg1;
	}

	class Holder {
		private TextView universityName_item;
		private TextView universityType_item;
		private TextView minPosion_item;

		public Holder(View view) {
			universityName_item = (TextView) view
					.findViewById(R.id.universityName_item);
			universityType_item = (TextView) view
					.findViewById(R.id.universityType_item);
			minPosion_item = (TextView) view.findViewById(R.id.minPosion_item);
		}
	}
}
