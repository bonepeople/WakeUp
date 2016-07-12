package com.bonepeople.wakeup;

import com.bonepeople.wakeup.ui.Fragment_home_body;
import com.bonepeople.wakeup.ui.Fragment_home_title;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

public class HomeActivity extends Activity
{
	public static final int WAKE_FAILED = 0;
	public static final int WAKE_SUCCESSFUL = 1;
	private static Context _context;
	private Fragment_home_body _fragment_body;
	private Fragment_home_title _fragment_title;
	public static Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case WAKE_FAILED:
				Toast.makeText(_context, "发送开机信息失败。", Toast.LENGTH_SHORT).show();
				break;
			case WAKE_SUCCESSFUL:
				Toast.makeText(_context, "开机信息已发送。", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		_context = getApplicationContext();
		_fragment_title = (Fragment_home_title) getFragmentManager().findFragmentById(R.id.fragment_home_title);
		_fragment_body = (Fragment_home_body) getFragmentManager().findFragmentById(R.id.fragment_home_body);

	}

	@Override
	public void onBackPressed()
	{
		if (!_fragment_body.onBackPressed())
			super.onBackPressed();
	}
	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent data)
	// {
	// Log.i("result", "get result");
	// }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{

		if (keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			_fragment_title.onMenuPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
