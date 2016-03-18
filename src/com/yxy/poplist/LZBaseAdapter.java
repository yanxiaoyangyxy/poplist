package com.yxy.poplist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class LZBaseAdapter extends BaseAdapter {

	private List<Map<String, Object>> mlData = new ArrayList<Map<String, Object>>();

	/**
	 * 设置数据
	 * 
	 * @param mlData
	 */
	public void setData(List<Map<String, Object>> plData) {
		mlData.clear();
		insertData(0, plData);
	}

	/**
	 * 插入数据
	 * 
	 * @param piIndex
	 * @param plData
	 */
	public void insertData(int piIndex, List<Map<String, Object>> plData) {
		mlData.addAll(piIndex, plData);
		notifyDataSetChanged();
	}

	/**
	 * 插入数据
	 * 
	 * @param piIndex
	 * @param pmData
	 */
	public void insertData(int piIndex, Map<String, Object> pmData) {
		mlData.add(piIndex, pmData);
		notifyDataSetChanged();
	}
	
	/**
	 * 删除数据
	 * 
	 */
	public void removeData(int index) {
		mlData.remove(index);
		notifyDataSetChanged();
	}
	
	/**
	 * 删除数据
	 * 
	 * @param piIndex
	 * @param pmData
	 */
	public void removeData(Map<String, Object> mItem) {
		mlData.remove(mItem);
		notifyDataSetChanged();
	}
	
	/**
	 * 获取数据
	 * 
	 * @param piIndex
	 * @param pmData
	 * @return 
	 */
	public List<Map<String, Object>> getData() {
		return mlData;
	}

	@Override
	public int getCount() {
		return mlData.size();
	}

	@Override
	public Object getItem(int position) {
		return mlData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView,
			ViewGroup parent);

}
