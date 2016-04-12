package com.bonepeople.wakeup.model;

import java.util.regex.Pattern;

import android.content.ContentValues;

public class ComputerInfo
{
	private int _id;
	private String _name;
	private String _comment;
	private String _mac;
	private String _ip;

	public ComputerInfo()
	{
		this._id = 0;
		this._name = "";
		this._comment = "";
		this._mac = "";
		this._ip = "";
	}

	public ComputerInfo(String _name, String _comment, String _mac, String _ip)
	{
		this._name = _name;
		this._comment = _comment;
		this._mac = _mac;
		this._ip = _ip;
	}

	public int get_id()
	{
		return this._id;
	}

	public void set_id(int _id)
	{
		this._id = _id;
	}

	public String get_name()
	{
		return _name;
	}

	public void set_name(String _name)
	{
		this._name = _name;
	}

	public String get_comment()
	{
		return _comment;
	}

	public void set_comment(String _comment)
	{
		this._comment = _comment;
	}

	public String get_mac()
	{
		return _mac;
	}

	public void set_mac(String _mac)
	{
		this._mac = _mac;
	}

	public String get_ip()
	{
		return _ip;
	}

	public void set_ip(String _ip)
	{
		this._ip = _ip;
	}

	public boolean check()
	{
		return check_name() && check_comment() && check_mac() && check_ip();
	}

	private boolean check_name()
	{
		if (this._name.length() > 20 || this._name.isEmpty())
			return false;
		else
			return true;
	}

	private boolean check_comment()
	{
		if (this._comment.length() > 50 || this._comment.isEmpty())
			return false;
		else
			return true;
	}

	private boolean check_mac()
	{
		return Pattern.matches("([0-9A-Fa-f]{2}-){5}[0-9A-Fa-f]{2}", this._mac);
	}

	private boolean check_ip()
	{
		if (Pattern.matches("(\\d{1,3}\\.){3}\\d{1,3}", this._ip))
		{
			String[] _temp_string = this._ip.split("[.]");
			for (String string : _temp_string)
			{
				if (Integer.parseInt(string) > 255)
					return false;
			}
			return true;
		}
		else
			return false;
	}

	public ContentValues get_values()
	{
		ContentValues _values = new ContentValues();
		_values.put("name", String.valueOf(this._name));
		_values.put("comment", String.valueOf(this._comment));
		_values.put("mac", String.valueOf(this._mac));
		_values.put("ip", String.valueOf(this._ip));
		return _values;
	}

}
