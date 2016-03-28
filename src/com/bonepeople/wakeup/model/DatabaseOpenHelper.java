package com.bonepeople.wakeup.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper
{

	public DatabaseOpenHelper(Context context)
	{
		super(context, "data.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase _db)
	{
		StringBuilder _table_define = new StringBuilder();
		_table_define.append("id integer primary key autoincrement,");
		_table_define.append("name varchar(20),");
		_table_define.append("comment varchar(50),");
		_table_define.append("mac varchar(17)");
		_table_define.append("ip varchar(15)");
		
		_db.execSQL("create table computers (" + _table_define + ") ");
		_db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		
	}

}
