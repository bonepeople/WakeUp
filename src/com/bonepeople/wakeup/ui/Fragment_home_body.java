package com.bonepeople.wakeup.ui;

import com.bonepeople.wakeup.R;

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
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View _view = inflater.inflate(R.layout.fragment_home_body, container, false);

		ListView _listview = (ListView) _view.findViewById(R.id.listview_fragment_home_body);
		MyAdapter _listadapter = new MyAdapter(getActivity());
		_listview.setAdapter(_listadapter);

		return _view;
	}

	private static class MyAdapter extends BaseAdapter
	{
		private static LayoutInflater _inflater;

		public MyAdapter(Context _context)
		{
			_inflater = LayoutInflater.from(_context);
		}

		private static class ViewHolder
		{
			public TextView _text_name;
			public TextView _text_comment;
		}

		@Override
		public int getCount()
		{
			return 20;
		}

		@Override
		public Object getItem(int position)
		{
			return null;
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

			_holder._text_name.setText("123");
			_holder._text_comment.setText("99999999999999999999999");

			return _view;
		}
	}
}
