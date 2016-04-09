package com.bonepeople.wakeup.utils;

import java.util.ArrayList;

import com.bonepeople.wakeup.model.Computer;
import com.bonepeople.wakeup.model.ComputerInfo;
import com.bonepeople.wakeup.model.DatabaseOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataUtils
{
	private static Context _context;
	private static DatabaseOpenHelper _openhelper;

	public static boolean init(Context _new_context)
	{
		_context = _new_context;
		_openhelper = new DatabaseOpenHelper(_context);
		return true;
	}

	public static ArrayList<Computer> get_computers()
	{
		ArrayList<Computer> _data = new ArrayList<Computer>();
		SQLiteDatabase _db = _openhelper.getReadableDatabase();
		Cursor _temp_cursor = _db.query("computers", new String[] { "name", "comment" }, null, null, null, null, "id");
		while (_temp_cursor.moveToNext())
		{
			Computer _temp_computer = new Computer();
			_temp_computer.set_name(_temp_cursor.getString(0));
			_temp_computer.set_comment(_temp_cursor.getString(1));
			_data.add(_temp_computer);
		}
		_temp_cursor.close();
		_db.close();
		return _data;
	}

	public static boolean has_computer_name(String _name)
	{
		SQLiteDatabase _db = _openhelper.getReadableDatabase();
		Cursor _temp_cursor = _db.query("computers", new String[] { "name" }, "name=?", new String[] { _name }, null, null, "id");
		if (_temp_cursor.moveToNext())
		{
			_temp_cursor.close();
			_db.close();
			return true;
		}
		_temp_cursor.close();
		_db.close();
		return false;
	}

	public static void add_computerinfo(ComputerInfo _info)
	{

		SQLiteDatabase _db = _openhelper.getWritableDatabase();
		_db.insert("computers", null, _info.get_values());
		_db.close();
		// /data/data/com.bonepeople.wakeup/databases
		// sqlite> insert into computers (id,name,comment,mac,ip) values('1','home','home lan','ad-ac-qf-gu-4r-h9','192.168.1.255');
	}

	public static int delete_computerinfo(String _name)
	{
		int _temp_n;
		SQLiteDatabase _db = _openhelper.getWritableDatabase();
		_temp_n = _db.delete("computers", "name=?", new String[] { _name });
		return _temp_n;
	}

	public static int update_computerinfo(ComputerInfo _info, int _id)
	{
		int _temp_n;
		SQLiteDatabase _db = _openhelper.getWritableDatabase();
		_temp_n = _db.update("computers", _info.get_values(), "id=?", new String[] { String.valueOf(_id) });
		return _temp_n;
	}

	public static int update_computerinfo(ComputerInfo _info, String _name)
	{
		int _temp_n;
		SQLiteDatabase _db = _openhelper.getWritableDatabase();
		_temp_n = _db.update("computers", _info.get_values(), "name=?", new String[] { _name });
		return _temp_n;
	}

	public static ComputerInfo get_computerinfo(String _name)
	{
		SQLiteDatabase _db = _openhelper.getReadableDatabase();
		ComputerInfo _temp_info = new ComputerInfo();
		Cursor _temp_cursor = _db.query("computers", null, "name=?", new String[] { _name }, null, null, "id");
		if (_temp_cursor.moveToNext())
		{
			_temp_info.set_id(_temp_cursor.getInt(0));
			_temp_info.set_name(_temp_cursor.getString(1));
			_temp_info.set_comment(_temp_cursor.getString(2));
			_temp_info.set_mac(_temp_cursor.getString(3));
			_temp_info.set_ip(_temp_cursor.getString(4));
		}
		else
		{
			// can not go there
		}

		_temp_cursor.close();
		_db.close();
		return _temp_info;
	}

	public static ArrayList<ComputerInfo> get_all()
	{
		ArrayList<ComputerInfo> _data = new ArrayList<ComputerInfo>();
		SQLiteDatabase _db = _openhelper.getReadableDatabase();
		Cursor _temp_cursor = _db.query("computers", null, null, null, null, null, "id");
		while (_temp_cursor.moveToNext())
		{
			ComputerInfo _temp_info = new ComputerInfo();
			_temp_info.set_name(_temp_cursor.getString(1));
			_temp_info.set_comment(_temp_cursor.getString(2));
			_temp_info.set_mac(_temp_cursor.getString(3));
			_temp_info.set_ip(_temp_cursor.getString(4));
			_data.add(_temp_info);
		}
		return _data;
	}

	public static boolean set_all(ArrayList<ComputerInfo> _data)
	{
		try
		{
			for (ComputerInfo computerInfo : _data)
			{
				String _name = computerInfo.get_name();
				if (has_computer_name(_name))
				{
					// update
					update_computerinfo(computerInfo, _name);
				}
				else
				{
					// add
					add_computerinfo(computerInfo);
				}
			}
		}
		catch (Exception _e)
		{
			return false;
		}
		return true;
	}
}
