package com.bonepeople.wakeup.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextImageButton extends LinearLayout
{
	private ImageView _imageview;
	private TextView _textview;

	public TextImageButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
		_imageview = new ImageView(context, attrs);
		_imageview.setFocusable(true);
		// _imageview.setPadding(0, 0, 0, 0);
		LinearLayout.LayoutParams _layout_image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		_layout_image.setMargins(15, 5, 15, 0);

		_textview = new TextView(context, attrs);
		_textview.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
		// _textview.setPadding(0, 0, 0, 0);

		setOrientation(LinearLayout.VERTICAL);

		addView(_imageview, _layout_image);

		addView(_textview);
	}

}
