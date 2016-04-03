package com.bonepeople.wakeup.model;

public class Computer
{
	private String _name = "空空如也";
	private String _comment = "通过右上角的菜单添加电脑吧";

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
}
