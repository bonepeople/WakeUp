package com.bonepeople.wakeup;

import com.bonepeople.wakeup.utils.AppInfoUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends Activity
{
	private TextView textView_splash_version;
	private RelativeLayout relativeLayout_splash_root;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		relativeLayout_splash_root = (RelativeLayout) this.findViewById(R.id.relativeLayout_splash_root);
		textView_splash_version = (TextView) this.findViewById(R.id.textView_splash_version);
		textView_splash_version.setText("°æ±¾ºÅ£º" + AppInfoUtils.getAppVersion(this));

		AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(2000);
		relativeLayout_splash_root.startAnimation(aa);
	}

}
