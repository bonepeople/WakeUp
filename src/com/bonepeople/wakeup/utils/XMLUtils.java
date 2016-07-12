package com.bonepeople.wakeup.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import com.bonepeople.wakeup.model.ComputerInfo;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;

public class XMLUtils
{
	private static final int PACKAGE = 1;
	private static final int COMPUTERS = 2;
	private static final int COMPUTERINFO = 3;
	private static final int NAME = 4;
	private static final int COMMENT = 5;
	private static final int MAC = 6;
	private static final int IP = 7;

	public static boolean export_data(Context _context)
	{
		XmlSerializer _writer = Xml.newSerializer();

		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			return false;

		File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/wakeup");
		File _file;
		if (_file_dir.mkdirs() || _file_dir.isDirectory())
			_file = new File(_file_dir, "wakeup_export.xml");
		else
			return false;

		ArrayList<ComputerInfo> _data = DataUtils.get_all();
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
			_fos.close();
		}
		catch (Exception _e)
		{
			return false;
		}
		return true;
	}

	public static boolean import_data()
	{
		ArrayList<ComputerInfo> _data = new ArrayList<ComputerInfo>();
		ComputerInfo _info = new ComputerInfo();
		XmlPullParser _reader = Xml.newPullParser();
		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			return false;

		File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/wakeup");
		File _file;
		if (_file_dir.isDirectory())
			_file = new File(_file_dir, "wakeup_export.xml");
		else
			return false;

		try
		{
			FileInputStream _fin = new FileInputStream(_file);
			_reader.setInput(_fin, "UTF-8");
			int _type = _reader.getEventType();

			while (_type != XmlPullParser.END_DOCUMENT)
			{
				switch (_type)
				{
				case XmlPullParser.START_TAG:
					switch (get_name_code(_reader.getName()))
					{
					case PACKAGE:
						if (!_reader.getAttributeValue(null, "name").equals("com.bonepeople.wakeup"))
						{
							_fin.close();
							return false;
						}
						break;
					case COMPUTERS:
						_data = new ArrayList<ComputerInfo>();
						break;
					case COMPUTERINFO:
						_info = new ComputerInfo();
						break;
					case NAME:
						_info.set_name(_reader.nextText().trim());
						break;
					case COMMENT:
						_info.set_comment(_reader.nextText().trim());
						break;
					case MAC:
						_info.set_mac(_reader.nextText().trim());
						break;
					case IP:
						_info.set_ip(_reader.nextText().trim());
						break;
					default:
						System.out.println(_reader.getName() + "未被正常识别");
					}
					break;
				case XmlPullParser.END_TAG:
					switch (get_name_code(_reader.getName()))
					{
					case COMPUTERINFO:
						if (_info.check())
							_data.add(_info);
						else
						{
							_fin.close();
							return false;
						}
						break;
					case COMPUTERS:
						_fin.close();
						return DataUtils.set_all(_data);
					}
				}

				_type = _reader.next();
			}
		}
		catch (Exception _e)
		{
			return false;
		}

		return true;
	}

	private static int get_name_code(String _name)
	{
		if (_name.equals("package"))
			return PACKAGE;
		if (_name.equals("computers"))
			return COMPUTERS;
		if (_name.equals("ComputerInfo"))
			return COMPUTERINFO;
		if (_name.equals("name"))
			return NAME;
		if (_name.equals("comment"))
			return COMMENT;
		if (_name.equals("mac"))
			return MAC;
		if (_name.equals("ip"))
			return IP;
		return 0;
	}
}
