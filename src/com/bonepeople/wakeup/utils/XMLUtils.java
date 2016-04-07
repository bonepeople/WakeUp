package com.bonepeople.wakeup.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlSerializer;

import com.bonepeople.wakeup.model.ComputerInfo;

import android.content.Context;
import android.util.Xml;

public class XMLUtils
{

	public static boolean export_data(Context _context)
	{
		ArrayList<ComputerInfo> _data = DataUtils.get_all();
		XmlSerializer _writer = Xml.newSerializer();
		File _file = new File(_context.getFilesDir(), "wakeup_export.xml");
		try
		{
			FileOutputStream _fos = new FileOutputStream(_file);
			_writer.setOutput(_fos, "utf-8");
			_writer.startDocument("utf-8", null);

			_writer.startTag(null, "package");
			_writer.attribute(null, "name", "com.bonepeople.wakeup");

			_writer.startTag(null, "computers");

			for (int _temp_i = 0; _temp_i < _data.size(); _temp_i++)
			{
				ComputerInfo _temp_info = _data.get(_temp_i);
				_writer.startTag(null, "ComputerInfo");

				_writer.startTag(null, "name");
				_writer.text(_temp_info.get_name());
				_writer.endTag(null, "name");

				_writer.startTag(null, "comment");
				_writer.text(_temp_info.get_comment());
				_writer.endTag(null, "comment");

				_writer.startTag(null, "mac");
				_writer.text(_temp_info.get_mac());
				_writer.endTag(null, "mac");

				_writer.startTag(null, "ip");
				_writer.text(_temp_info.get_ip());
				_writer.endTag(null, "ip");

				_writer.endTag(null, "ComputerInfo");
			}

			_writer.endTag(null, "computers");

			_writer.endTag(null, "package");

			_writer.endDocument();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
}
