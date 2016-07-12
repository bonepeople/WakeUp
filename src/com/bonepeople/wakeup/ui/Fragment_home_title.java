package com.bonepeople.wakeup.ui;

import com.bonepeople.wakeup.AdbActivity;
import com.bonepeople.wakeup.EditActivity;
import com.bonepeople.wakeup.HelpActivity;
import com.bonepeople.wakeup.R;
import com.bonepeople.wakeup.utils.XMLUtils;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Fragment_home_title extends Fragment
{
	private ImageButton _imagebutton;
	private ListView _listview;
	private PopupWindow _popupwindow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_home_title, container, false);
		_imagebutton = (ImageButton) view.findViewById(R.id.imagebutton_fragment_home_title);
		create_popupwindow();
		_imagebutton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onMenuPressed();
			}
		});
		_imagebutton.setOnLongClickListener(new OnLongClickListener()
		{

			@Override
			public boolean onLongClick(View v)
			{
				Intent _adb = new Intent(getActivity(), AdbActivity.class);
				startActivity(_adb);
				return true;
			}
		});

		return view;
	}

	public void onMenuPressed()
	{
		_popupwindow.showAsDropDown(_imagebutton);
	}

	private void create_popupwindow()
	{
		View _view = LayoutInflater.from(getActivity()).inflate(R.layout.menu_system, null);
		_popupwindow = new PopupWindow(_view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		_popupwindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.frame));

		_listview = (ListView) _view.findViewById(R.id.listview_fragment_home_title);
		ArrayAdapter<String> _adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_menu, R.id.textview_item_menu, new String[] { "添加电脑", "从SD卡导入", "导出到SD卡", "帮助", "退出" });
		_listview.setAdapter(_adapter);

		_listview.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				switch (position)
				{
				case 0:
					Intent _intent_add = new Intent(getActivity(), EditActivity.class);
					_intent_add.putExtra("type", "add");
					startActivity(_intent_add);
					break;
				case 1:
					if (XMLUtils.import_data())
						Toast.makeText(getActivity(), "信息已导入.", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(getActivity(), "信息导入失败。", Toast.LENGTH_SHORT).show();
					getActivity().getFragmentManager().findFragmentById(R.id.fragment_home_body).onResume();
					break;
				case 2:
					if (XMLUtils.export_data(getActivity()))
						Toast.makeText(getActivity(), "信息已成功导出。", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(getActivity(), "信息导出失败。", Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Intent _intent_help = new Intent(getActivity(), HelpActivity.class);
					startActivity(_intent_help);
					break;
				case 4:
					getActivity().finish();
					break;
				default:
					break;
				}
				_popupwindow.dismiss();
			}
		});
		_listview.setOnKeyListener(new OnKeyListener()
		{
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event)
			{
				if (keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN)
				{
					_popupwindow.dismiss();
					return true;
				}
				return false;
			}
		});
	}
}
