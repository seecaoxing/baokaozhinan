package com.example.adapters;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 在资料分享页面的fragment设置适配器
 * @author see
 *
 */
public class MyFragmentAdapter extends FragmentPagerAdapter{

	private List<Fragment> listfrag;
	
	
	public MyFragmentAdapter(FragmentManager fm,List<Fragment> listfrag) {
		super(fm);
		this.listfrag = listfrag;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return listfrag.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listfrag.size();
	}

}
