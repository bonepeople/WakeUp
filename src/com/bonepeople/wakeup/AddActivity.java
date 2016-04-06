package com.bonepeople.wakeup;

import com.bonepeople.wakeup.model.ComputerInfo;
import com.bonepeople.wakeup.utils.DataUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity
{
	// Intent _intent;
	private Button _button_ok;
	private Button _button_cancel;
	private EditText _edittext_add_name;
	private EditText _edittext_add_comment;
	private EditText _edittext_add_mac;
	private EditText _edittext_add_ip;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		// _intent = this.getIntent();
		_button_ok = (Button) findViewById(R.id.button_add_ok);
		_button_cancel = (Button) findViewById(R.id.button_add_cancel);
		_edittext_add_name = (EditText) findViewById(R.id.edittext_add_name);
		_edittext_add_comment = (EditText) findViewById(R.id.edittext_add_comment);
		_edittext_add_mac = (EditText) findViewById(R.id.edittext_add_mac);
		_edittext_add_ip = (EditText) findViewById(R.id.edittext_add_ip);

		_button_ok.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				String _string_name, _string_comment, _string_mac, _string_ip;

				_string_name = _edittext_add_name.getText().toString().trim();
				_string_comment = _edittext_add_comment.getText().toString().trim();
				_string_mac = _edittext_add_mac.getText().toString().trim();
				_string_ip = _edittext_add_ip.getText().toString().trim();

				if (_string_name.isEmpty() || _string_mac.isEmpty() || _string_ip.isEmpty())
				{
					Toast.makeText(AddActivity.this, "请完善以上信息", Toast.LENGTH_SHORT).show();
					return;
				}
				if (!check_name(_string_name))
				{
					Toast.makeText(AddActivity.this, "名称重复", Toast.LENGTH_SHORT).show();
					_edittext_add_name.requestFocus();
					return;
				}
				if (!check_mac(_string_mac))
				{
					Toast.makeText(AddActivity.this, "MAC地址错误", Toast.LENGTH_SHORT).show();
					_edittext_add_mac.requestFocus();
					return;
				}
				if (!check_ip(_string_ip))
				{
					Toast.makeText(AddActivity.this, "IP地址错误", Toast.LENGTH_SHORT).show();
					_edittext_add_ip.requestFocus();
					return;
				}

				DataUtils.add_computerinfo(new ComputerInfo(_string_name, _string_comment, _string_mac, _string_ip));
				Toast.makeText(AddActivity.this, "ok", Toast.LENGTH_SHORT).show();
				// setResult(1, _intent);
				finish();
			}

			private boolean check_name(String _name)
			{
				return !DataUtils.has_computer_name(_name);
			}

			private boolean check_mac(String _mac)
			{
				String[] _temp_string = _mac.split("-");
				if (_temp_string.length == 6)
				{
					for (String string : _temp_string)
					{
						if (string.length() != 2)
							return false;
					}
				}
				else
					return false;
				return true;
			}

			private boolean check_ip(String _ip)
			{
				String[] _temp_string = _ip.split("[.]");
				if (_temp_string.length == 4)
				{
					for (String string : _temp_string)
					{
						if (string.isEmpty() || Integer.parseInt(string) > 255)
							return false;
					}
				}
				else
					return false;
				return true;
			}
		});

		_button_cancel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				finish();
			}
		});

	}
}
