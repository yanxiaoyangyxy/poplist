package com.yxy.poplist;

import java.util.Map;

import com.example.poplist.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @classname CityListAdapter
 * @tag 获取城市列表适配器 （复用我的简历布局item_interview）
 * @author yanxiaoyang
 * @date 2016-3-9上午9:11:26
 */
public class CityListAdapter extends LZBaseAdapter {

	private Context moContext;
	private int selectedPos = -1;

	public CityListAdapter(Context poContext) {
		moContext = poContext;
	}

	public void setSelectedPosition(int pos) {
		selectedPos = pos;
		notifyDataSetChanged();
	}

	public int getSelectedPosition() {
		return selectedPos;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater loInflater = LayoutInflater.from(moContext);
			convertView = loInflater.inflate(R.layout.item_city_list, null);
			Holder loHolder = new Holder();

			loHolder.moTvCityName = (TextView) convertView
					.findViewById(R.id.item_city_list_tv_name);
			loHolder.moLlCityName = (LinearLayout) convertView
					.findViewById(R.id.item_city_list_ll_name);

			convertView.setTag(loHolder);
		}
		final Holder loHolder = (Holder) convertView.getTag();
		@SuppressWarnings("unchecked")
		Map<String, Object> lmItem = (Map<String, Object>) getItem(position);
		if (selectedPos == position) {
			loHolder.moLlCityName.setBackgroundColor(Color
					.parseColor("#EDC53B"));
			loHolder.moTvCityName.setTextColor(Color.parseColor("#FFFFFF"));
		} else {
			loHolder.moLlCityName.setBackgroundColor(Color
					.parseColor("#FFFFFF"));
			loHolder.moTvCityName.setTextColor(Color.parseColor("#333333"));
		}
		if (lmItem.containsKey("name")) {
			String lsNmae = lmItem.get("name").toString();
			if (!TextUtils.isEmpty(lsNmae)) {
				loHolder.moTvCityName.setText(lsNmae);
			}
		}

		return convertView;

	}

	private class Holder {
		private TextView moTvCityName;
		private LinearLayout moLlCityName;

	}

}
