package com.tv.orangetv.adapter;

//import android.R;
import java.util.List;


import com.tv.orangetv.R;
import com.tv.orangetv.tvs.Tvs;
import com.tv.orangetv.utils.HttpUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TvAdapter extends BaseAdapter {
    
	private Context context;
	
	private List<Tvs> newsList;
	
	public TvAdapter(Context context,List<Tvs> newsList){
		
		this.context=context;
		this.newsList=newsList;
		
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.newsList.size();
	}

	@Override
	public Tvs getItem(int position) {
		// TODO Auto-generated method stub
		return this.newsList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.tv_item, null);
		}
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvtitle);
		TextView tvDesc = (TextView) convertView.findViewById(R.id.tvdesc);
		TextView tvTime = (TextView) convertView.findViewById(R.id.tvtime);
		ImageView ivPic = (ImageView) convertView.findViewById(R.id.ivpic);
		
		
		Tvs tvs = newsList.get(position);
		tvTitle.setText(tvs.getTitle());
		//tvDesc.setText(tvs.getDesc());
		//tvTime.setText(tvs.getTime());
		
		String pic_url = tvs.getPic_url();
		HttpUtils.setPicBitmap(ivPic, pic_url);		
		return convertView;
	}
	

}
