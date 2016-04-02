package com.bonepeople.wakeup.ui;

import java.util.ArrayList;

import com.bonepeople.wakeup.R;
import com.bonepeople.wakeup.model.Computer;
import com.bonepeople.wakeup.utils.DataUtils;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_home_body extends Fragment
{
	private ListView _listview;
	private MyAdapter _listadapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View _view = inflater.inflate(R.layout.fragment_home_body, container, false);

		_listview = (ListView) _view.findViewById(R.id.listview_fragment_home_body);
		_listadapter = new MyAdapter(getActivity());
		_listview.setAdapter(_listadapter);
		return _view;
	}

	@Override
	public void onResume()
	{
		_listadapter.refresh_data();
		_listadapter.notifyDataSetChanged();
		// TODO Auto-generated method stub
		super.onResume();
	}

	private static class MyAdapter extends BaseAdapter
	{
		private Context _context;
		private LayoutInflater _inflater;
		private ArrayList<Computer> _data;

		public MyAdapter(Context _context)
		{
			this._context = _context;
			_inflater = LayoutInflater.from(_context);
		}

		public void refresh_data()
		{
			_data = DataUtils.get_computers(_context);
		}
		private static class ViewHolder
		{
			public TextView _text_name;
			public TextView _text_comment;
		}

		@Override
		public int getCount()
		{
			return _data.size();
		}

		@Override
		public Computer getItem(int position)
		{
			return _data.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View _view = convertView;
			ViewHolder _holder;

			if (convertView == null)
			{
				_view = _inflater.inflate(R.layout.item_computer, null);
				_holder = new ViewHolder();
				_holder._text_name = (TextView) _view.findViewById(R.id.textview_item_computer_name);
				_holder._text_comment = (TextView) _view.findViewById(R.id.textview_item_computer_comment);
				_view.setTag(_holder);
			}
			else
			{
				_holder = (ViewHolder) _view.getTag();
			}

			Computer _temp_computer = getItem(position);
			_holder._text_name.setText(_temp_computer.get_name());
			_holder._text_comment.setText(_temp_computer.get_comment());

			return _view;
		}
	}
}
