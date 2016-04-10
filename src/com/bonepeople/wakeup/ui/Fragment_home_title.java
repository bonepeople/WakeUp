package com.bonepeople.wakeup.ui;

import com.bonepeople.wakeup.EditActivity;
import com.bonepeople.wakeup.HelpActivity;
import com.bonepeople.wakeup.R;
import com.bonepeople.wakeup.utils.XMLUtils;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment_home_title extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_home_title, container, false);
		setHasOptionsMenu(true);
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(R.menu.menu_home, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.menu_home_add:
			Intent _intent_add = new Intent(getActivity(), EditActivity.class);
			_intent_add.putExtra("type", "add");
			startActivity(_intent_add);
			break;
		case R.id.menu_home_import:
			if (XMLUtils.import_data())
				Toast.makeText(getActivity(), "信息已导入.", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(getActivity(), "信息导入失败。", Toast.LENGTH_SHORT).show();
			getActivity().getFragmentManager().findFragmentById(R.id.fragment_home_body).onResume();
			break;
		case R.id.menu_home_export:
			if (XMLUtils.export_data(getActivity()))
				Toast.makeText(getActivity(), "信息已成功导出。", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(getActivity(), "信息导出失败。", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_home_help:
			Intent _intent_help = new Intent(getActivity(), HelpActivity.class);
			startActivity(_intent_help);
			break;
		case R.id.menu_home_exit:
			getActivity().finish();
			break;
		default:
			break;
		}
		return true;
	}
}
