package com.bonepeople.wakeup.ui;

import com.bonepeople.wakeup.R;
import com.bonepeople.wakeup.model.ComputerInfo;
import com.bonepeople.wakeup.utils.DataUtils;

import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Fragment_home_comment extends Fragment
{
	private LinearLayout _linearlayout_root;
	private TextView _text_name;
	private TextView _text_comment;
	private TextView _text_mac;
	private TextView _text_ip;
	private String _selected = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View _view = inflater.inflate(R.layout.fragment_home_comment, container, false);

		_linearlayout_root = (LinearLayout) _view.findViewById(R.id.linearlayout_fragment_home_comment_root);
		_text_name = (TextView) _view.findViewById(R.id.textview_fragment_home_comment_name);
		_text_comment = (TextView) _view.findViewById(R.id.textview_fragment_home_comment_comment);
		_text_mac = (TextView) _view.findViewById(R.id.textview_fragment_home_comment_mac);
		_text_ip = (TextView) _view.findViewById(R.id.textview_fragment_home_comment_ip);

		_linearlayout_root.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				hide();
			}
		});

		return _view;
	}

	private void refresh_data()
	{
		ComputerInfo _temp_info = DataUtils.get_computerinfo(_selected);
		_text_name.setText(_temp_info.get_name());
		_text_comment.setText(_temp_info.get_comment());
		_text_mac.setText(_temp_info.get_mac());
		_text_ip.setText(_temp_info.get_ip());

		if (_text_comment.getLineCount() > 5)
			_text_comment.setMovementMethod(ScrollingMovementMethod.getInstance());
		else
			_text_comment.setMovementMethod(null);
	}

	public void show(String _name)
	{
		AnimationSet _animationset = new AnimationSet(true);
		AlphaAnimation _alpha = new AlphaAnimation(0, 1);
		_alpha.setDuration(200);
		ScaleAnimation _scale = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		_scale.setDuration(200);
		_animationset.addAnimation(_alpha);
		_animationset.addAnimation(_scale);
		_linearlayout_root.setVisibility(RelativeLayout.VISIBLE);
		_linearlayout_root.startAnimation(_animationset);

		_selected = _name;
		refresh_data();
	}

	private void hide()
	{
		AnimationSet _animationset = new AnimationSet(true);
		AlphaAnimation _alpha = new AlphaAnimation(1, 0);
		_alpha.setDuration(100);
		ScaleAnimation _scale = new ScaleAnimation(1f, 0.9f, 1f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		_scale.setDuration(100);
		_animationset.addAnimation(_alpha);
		_animationset.addAnimation(_scale);
		_linearlayout_root.setVisibility(RelativeLayout.INVISIBLE);
		_linearlayout_root.startAnimation(_animationset);
	}

	@Override
	public void onPause()
	{
		if (_linearlayout_root.getVisibility() == RelativeLayout.VISIBLE)
			hide();
		super.onPause();
	}

	public boolean onBackPressed()
	{
		if (_linearlayout_root.getVisibility() == RelativeLayout.VISIBLE)
			hide();
		else
			return false;
		return true;
	}
}
