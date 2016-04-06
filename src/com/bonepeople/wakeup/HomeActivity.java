package com.bonepeople.wakeup;

import com.bonepeople.wakeup.ui.Fragment_home_body;

import android.app.Activity;
import android.os.Bundle;

public class HomeActivity extends Activity
{
	private Fragment_home_body _fragment_body;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

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
}
