package com.bonepeople.wakeup.ui;

import com.bonepeople.wakeup.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_home_body extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View _view = inflater.inflate(R.layout.fragment_home_body, container, false);

		ListView _listview = (ListView) _view.findViewById(R.id.listview_fragment_home_body);
		MyAdapter _listadapter = new MyAdapter();
		_listview.setAdapter(_listadapter);

		return _view;
	}

	private class MyAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return 20;
		}

		@Override
		public Object getItem(int position)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			// TODO Auto-generated method stub
			View _view = View.inflate(getActivity().getApplicationContext(), R.layout.item_computer, null);
			TextView _text_name = (TextView) _view.findViewById(R.id.textview_item_computer_name);
			TextView _text_comment = (TextView) _view.findViewById(R.id.textview_item_computer_comment);

			_text_name.setText("123");
			_text_comment.setText("99999999999999999999999");

			return _view;
		}

	}

}
