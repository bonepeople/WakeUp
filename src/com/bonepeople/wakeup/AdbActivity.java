package com.bonepeople.wakeup;

import com.bonepeople.wakeup.utils.NetWorkUtil;
import com.bonepeople.wakeup.utils.ShellUtils;
import com.bonepeople.wakeup.utils.ShellUtils.CommandResult;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AdbActivity extends Activity implements View.OnClickListener
{
	private boolean _open = false;
	private Button _button_adb;
	private TextView _text_ip, _text_screen;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adb);

		_button_adb = (Button) findViewById(R.id.button_adb);
		_text_ip = (TextView) findViewById(R.id.textview_ip);
		_text_screen = (TextView) findViewById(R.id.textview_screen);

		_button_adb.setOnClickListener(this);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Point _outSize = new Point(0, 0);
		wm.getDefaultDisplay().getSize(_outSize);
		int screen_width = _outSize.x;
		int screen_height = _outSize.y;

		String _string_screen = "您现在手机的宽度为：" + screen_width + "，高度为：" + screen_height;
		_text_screen.setText(_string_screen);
		init();
	}

	private void init()
	{
		String _command = "getprop service.adb.tcp.port";
		CommandResult _result = ShellUtils.execCommand(_command, true, true);
		System.out.println(_command + "-result:" + _result.result);
		System.out.println(_command + "-success:" + _result.successMsg);
		System.out.println(_command + "-error:" + _result.errorMsg);
		System.out.println(_command + "-over");
		String _port = _result.successMsg;
		try
		{
			if (!_port.isEmpty())
			{
				if (Integer.parseInt(_port) > 0)
				{
					_text_ip.setText("当前手机已开启远程调试功能\n" + "请链接 " + NetWorkUtil.getLocalIpAddress(this) + ":" + _port + " 进行调试");
					_open = true;
					_button_adb.setText("关闭");
				}
				else
				{
					_text_ip.setText("当前手机未开启远程调试功能");
				}
			}
			else
			{
				_text_ip.setText("当前手机未开启远程调试功能");
			}
		}
		catch (Exception e)
		{
			_text_ip.setText("当前手机未开启远程调试功能");
		}
	}

	@Override
	public void onClick(View v)
	{
		CommandResult _result;
		String _command;
		switch (v.getId())
		{
		case R.id.button_adb:
			if (_open)
			{
				// close
				int _code;
				_command = "setprop service.adb.tcp.port -1";
				_result = ShellUtils.execCommand(_command, true, true);
				System.out.println(_command + "-result:" + _result.result);
				System.out.println(_command + "-success:" + _result.successMsg);
				System.out.println(_command + "-error:" + _result.errorMsg);
				System.out.println(_command + "-over");
				_code = _result.result;

				_command = "stop adbd";
				_result = ShellUtils.execCommand(_command, true, true);
				System.out.println(_command + "-result:" + _result.result);
				System.out.println(_command + "-success:" + _result.successMsg);
				System.out.println(_command + "-error:" + _result.errorMsg);
				System.out.println(_command + "-over");
				_code += _result.result;

				_command = "start adbd";
				_result = ShellUtils.execCommand(_command, true, true);
				System.out.println(_command + "-result:" + _result.result);
				System.out.println(_command + "-success:" + _result.successMsg);
				System.out.println(_command + "-error:" + _result.errorMsg);
				System.out.println(_command + "-over");
				_code += _result.result;

				if (_code == 0)
				{
					_text_ip.setText("已关闭远程调试功能");
					_open = false;
					_button_adb.setText("开启");
				}
				else
				{
					_text_ip.setText("远程调试关闭失败");
				}
			}
			else
			{
				// open
				int _code;
				_command = "setprop service.adb.tcp.port 5555";
				_result = ShellUtils.execCommand(_command, true, true);
				System.out.println(_command + "-result:" + _result.result);
				System.out.println(_command + "-success:" + _result.successMsg);
				System.out.println(_command + "-error:" + _result.errorMsg);
				System.out.println(_command + "-over");
				_code = _result.result;

				_command = "stop adbd";
				_result = ShellUtils.execCommand(_command, true, true);
				System.out.println(_command + "-result:" + _result.result);
				System.out.println(_command + "-success:" + _result.successMsg);
				System.out.println(_command + "-error:" + _result.errorMsg);
				System.out.println(_command + "-over");
				_code += _result.result;

				_command = "start adbd";
				_result = ShellUtils.execCommand(_command, true, true);
				System.out.println(_command + "-result:" + _result.result);
				System.out.println(_command + "-success:" + _result.successMsg);
				System.out.println(_command + "-error:" + _result.errorMsg);
				System.out.println(_command + "-over");
				_code += _result.result;

				if (_code == 0)
				{
					_text_ip.setText("当前手机已开启远程调试功能\n" + "请链接 " + NetWorkUtil.getLocalIpAddress(this) + ":5555 进行调试");
					_open = true;
					_button_adb.setText("关闭");
				}
				else
				{
					_text_ip.setText("远程调试开启失败");
				}
			}
			break;
		}
	}
}
