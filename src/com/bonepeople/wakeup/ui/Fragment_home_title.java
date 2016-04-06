package com.bonepeople.wakeup.ui;

import com.bonepeople.wakeup.EditActivity;
import com.bonepeople.wakeup.R;

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

		// TODO Auto-generated method stub
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
			Toast.makeText(getActivity(), "add", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getActivity(), EditActivity.class);
			startActivity(intent);
			break;
		case R.id.menu_home_import:
			Toast.makeText(getActivity(), "import", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_home_export:
			Toast.makeText(getActivity(), "export", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_home_help:
			Toast.makeText(getActivity(), "help", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_home_exit:
			Toast.makeText(getActivity(), "exit", Toast.LENGTH_SHORT).show();
			getActivity().finish();
			break;
		default:
			break;
		}
		return true;
	}
}
