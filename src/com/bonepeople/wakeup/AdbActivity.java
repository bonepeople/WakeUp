package com.bonepeople.wakeup;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.bonepeople.wakeup.utils.ShellUtils;
import com.bonepeople.wakeup.utils.ShellUtils.CommandResult;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdbActivity extends Activity implements View.OnClickListener
{
	private Button _button_open;
	private TextView _text_ip;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adb);

		_button_open = (Button) findViewById(R.id.button_open);
		_text_ip = (TextView) findViewById(R.id.textview_ip);

		_button_open.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		Toast.makeText(this, "open", Toast.LENGTH_SHORT).show();
		System.out.println("send su");

		CommandResult _result = ShellUtils.execCommand("setprop service.adb.tcp.port 5555", true, true);
		System.out.println("result:" + _result.result);
		System.out.println("success:" + _result.successMsg);
		System.out.println("error:" + _result.errorMsg);
		System.out.println("over");
	}
}
