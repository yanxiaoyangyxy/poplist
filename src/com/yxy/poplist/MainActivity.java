package com.yxy.poplist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.poplist.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;

public class MainActivity extends Activity {
	private PopupWindow Xuelipw;
	private CityListAdapter Xueliadapter;
	private Animation moAnimationBgAlpha;
	private String lsXueli;
	private String lsXueliId;
	private TextView motvXueli;
	private Button moBtnXueli;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		motvXueli = (TextView) findViewById(R.id.main_tv_xueli);
		moBtnXueli = (Button) findViewById(R.id.main_btn_xueli);
		moBtnXueli.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showXueliWindow(MainActivity.this,
						MainActivity.this.findViewById(R.id.main_tv_xueli));
			}
		});
	}

	/****************** 学历选择器 ********************/
	@SuppressLint("InflateParams")
	private void showXueliWindow(Context context, View parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View vPopupWindow = inflater.inflate(R.layout.pop_select_city, null,
				false);
		TextView moTvCancel = (TextView) vPopupWindow
				.findViewById(R.id.pop_select_city_tv_cancle);
		TextView moTvOk = (TextView) vPopupWindow
				.findViewById(R.id.pop_select_city_tv_ok);
		ListView moLvCity = (ListView) vPopupWindow
				.findViewById(R.id.pop_select_ciy_lv);
		Xueliadapter = new CityListAdapter(MainActivity.this);
		moLvCity.setAdapter(Xueliadapter);
		Xueliadapter.setData(getData());

		Xuelipw = new PopupWindow(vPopupWindow, LayoutParams.MATCH_PARENT,
				1000, true);
		ColorDrawable cd = new ColorDrawable(0x000000);
		moAnimationBgAlpha = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.comment_alpha_in);
		vPopupWindow.startAnimation(moAnimationBgAlpha);
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.4f;
		getWindow().setAttributes(lp);
		Xuelipw.setBackgroundDrawable(cd);
		Xuelipw.setOutsideTouchable(true);
		Xuelipw.showAtLocation((View) parent.getParent(), Gravity.BOTTOM, 0, 0);
		Xuelipw.setFocusable(true);
		Xuelipw.update();
		Xuelipw.setOnDismissListener(new OnDismissListener() {
			public void onDismiss() {
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1f;
				getWindow().setAttributes(lp);
			}
		});
		moTvCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Xuelipw.dismiss();

			}
		});
		moLvCity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Xueliadapter.setSelectedPosition(position);
				lsXueli = getData().get(position).get("name").toString();
				lsXueliId = getData().get(position).get("id").toString();
			}
		});
		moTvOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(lsXueli)) {
					motvXueli.setText(lsXueli);
				}
				Xuelipw.dismiss();

			}
		});
	}

	// 设置学历数据
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> llList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "大专");
		map.put("id", "0");
		llList.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "本科");
		map.put("id", "1");
		llList.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "研究生");
		map.put("id", "2");
		llList.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "博士");
		map.put("id", "3");
		llList.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "博士后");
		map.put("id", "4");
		llList.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "其他");
		map.put("id", "5");
		llList.add(map);
		return llList;
	}
}
