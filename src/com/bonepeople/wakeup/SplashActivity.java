package com.bonepeople.wakeup;

import com.bonepeople.wakeup.utils.AppInfoUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity
{
	public static final int INIT_FAILED = 0;
	public static final int INIT_SUCCESSFUL = 1;
	public static final int INIT_UPDATE = 2;

	private TextView textView_splash_version;
	private RelativeLayout relativeLayout_splash_root;

	private Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case INIT_FAILED:
				Toast.makeText(getApplicationContext(), "程序加载失败，请重新安装", Toast.LENGTH_SHORT).show();
				textView_splash_version.setText("程序加载失败，请重新安装");
				break;
			case INIT_SUCCESSFUL:
				load_HomeActivity();
				break;
			case INIT_UPDATE:
				Toast.makeText(getApplicationContext(), "版本过时，需要更新。", Toast.LENGTH_SHORT).show();
				load_HomeActivity();
			}
			// Toast.makeText(getApplicationContext(), "后台用时：" + String.valueOf(msg.arg1), Toast.LENGTH_SHORT).show();
		};
	};

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		relativeLayout_splash_root = (RelativeLayout) this.findViewById(R.id.relativeLayout_splash_root);
		textView_splash_version = (TextView) this.findViewById(R.id.textView_splash_version);
		textView_splash_version.setText("版本号：" + AppInfoUtils.getAppVersion(this));

		AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(2000);
		relativeLayout_splash_root.startAnimation(aa);

		Thread_init thread_init = new Thread_init(getApplicationContext(), handler);
		thread_init.start();

	}

	private void load_HomeActivity()
	{
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}
}
