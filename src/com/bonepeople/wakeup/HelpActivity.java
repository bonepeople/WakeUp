package com.bonepeople.wakeup;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HelpActivity extends Activity
{
	private WebView _web;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		_web = (WebView) findViewById(R.id.webview_help);

		_web.loadUrl("http://baike.baidu.com/link?url=0dlZFtaGLAYuloobGXYfOxmOlnRlzGgLz810J1z8LSJ5hUDlKGU3EJvAtnJcbf3smvZ5inON_d3fdx30069O3_");
		_web.setWebViewClient(new WebViewClient()
		{
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return true;
			}
		});
	}
}
