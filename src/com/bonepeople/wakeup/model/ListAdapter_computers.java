package com.bonepeople.wakeup.model;

import java.util.ArrayList;

import com.bonepeople.wakeup.R;
import com.bonepeople.wakeup.utils.DataUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter_computers extends BaseAdapter
{
	private Context _context;
	private LayoutInflater _inflater;
	private ArrayList<Computer> _data;

	private static class ViewHolder
	{
		public TextView _text_name;
		public TextView _text_comment;
	}

	public ListAdapter_computers(Context _context)
	{
		this._context = _context;
		_inflater = LayoutInflater.from(_context);
		_data = new ArrayList<Computer>();
	}

	public void refresh_data()
	{
		_data = DataUtils.get_computers(_context);
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
