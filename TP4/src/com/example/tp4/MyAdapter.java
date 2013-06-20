package com.example.tp4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private String[] data;
	private LayoutInflater inflater;

	public MyAdapter(Context context, String[] data) {
		this.data = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;
		if (convertView == null) {
			view = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			holder.textView = (TextView) view.findViewById(android.R.id.text1);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		holder.textView.setText(data[position]);
		return view;
	}

	private class ViewHolder {
		TextView textView;
	}

}
