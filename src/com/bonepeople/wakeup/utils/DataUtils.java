package com.bonepeople.wakeup.utils;

import com.bonepeople.wakeup.model.ComputerInfo;
import com.bonepeople.wakeup.model.DatabaseOpenHelper;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

public class DataUtils
{
	public static void Save_ComputerInfo(Activity _activity,ComputerInfo _info)
	{

		DatabaseOpenHelper _openhelper = new DatabaseOpenHelper(_activity);
		SQLiteDatabase _db = _openhelper.getWritableDatabase();
		_db.insert("computers", null, _info.get_values());
		_db.close();
	}
}
