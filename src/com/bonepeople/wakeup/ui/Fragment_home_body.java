package com.bonepeople.wakeup.ui;

import com.bonepeople.wakeup.R;
import com.bonepeople.wakeup.model.ListAdapter_computers;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_home_body extends Fragment
{
	private Fragment_home_comment _fragment_home_comment;
	private ListView _listview;
	private ListAdapter_computers _listadapter;
	private TextView _textview_empty;
	private String _selected = "";
	private RelativeLayout _relativelayout_menu_pop_root;
	private TextImageButton _button_menu_pop_wake;
	private TextImageButton _button_menu_pop_detail;
	private TextImageButton _button_menu_pop_update;
	private TextImageButton _button_menu_pop_delete;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View _view = inflater.inflate(R.layout.fragment_home_body, container, false);

		_fragment_home_comment = (Fragment_home_comment) getFragmentManager().findFragmentById(R.id.fragment_home_comment);
		_relativelayout_menu_pop_root = (RelativeLayout) _view.findViewById(R.id.relativelayout_menu_pop_root);
		_button_menu_pop_wake = (TextImageButton) _view.findViewById(R.id.button_menu_pop_wake);
		_button_menu_pop_detail = (TextImageButton) _view.findViewById(R.id.button_menu_pop_detail);
		_button_menu_pop_update = (TextImageButton) _view.findViewById(R.id.button_menu_pop_update);
		_button_menu_pop_delete = (TextImageButton) _view.findViewById(R.id.button_menu_pop_delete);
		_listview = (ListView) _view.findViewById(R.id.listview_fragment_home_body);
		_textview_empty = (TextView) _view.findViewById(R.id.textview_empty);

		_listadapter = new ListAdapter_computers(getActivity());
		_listview.setAdapter(_listadapter);
		_listview.setEmptyView(_textview_empty);
		_listview.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				TextView _temp_text = (TextView) view.findViewById(R.id.textview_item_computer_name);
				_selected = _temp_text.getText().toString();

				menu_show();
			}

		});

		_relativelayout_menu_pop_root.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				menu_hide();
			}
		});

		_button_menu_pop_wake.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Toast.makeText(getActivity(), "wake", Toast.LENGTH_SHORT).show();
				System.out.println(v.getParent().getClass().toString());
				menu_hide();
			}
		});
		_button_menu_pop_detail.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Toast.makeText(getActivity(), "detail", Toast.LENGTH_SHORT).show();
				_fragment_home_comment.show(_selected);
				menu_hide();
			}
		});

		_button_menu_pop_update.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Toast.makeText(getActivity(), "update", Toast.LENGTH_SHORT).show();
				menu_hide();
			}
		});
		_button_menu_pop_delete.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
				menu_hide();
			}
		});

		return _view;
	}

	private void menu_show()
	{
		AnimationSet _animationset = new AnimationSet(true);
		AlphaAnimation _alpha = new AlphaAnimation(0, 1);
		_alpha.setDuration(200);
		ScaleAnimation _scale = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		_scale.setDuration(200);
		_animationset.addAnimation(_alpha);
		_animationset.addAnimation(_scale);
		_relativelayout_menu_pop_root.setVisibility(RelativeLayout.VISIBLE);
		_relativelayout_menu_pop_root.startAnimation(_animationset);
	}

	private void menu_hide()
	{
		AnimationSet _animationset = new AnimationSet(true);
		AlphaAnimation _alpha = new AlphaAnimation(1, 0);
		_alpha.setDuration(100);
		ScaleAnimation _scale = new ScaleAnimation(1f, 0.9f, 1f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		_scale.setDuration(100);
		_animationset.addAnimation(_alpha);
		_animationset.addAnimation(_scale);
		_relativelayout_menu_pop_root.setVisibility(RelativeLayout.INVISIBLE);
		_relativelayout_menu_pop_root.startAnimation(_animationset);
	}

	@Override
	public void onResume()
	{
		_listadapter.refresh_data();
		_listadapter.notifyDataSetChanged();

		super.onResume();
	}

	@Override
	public void onPause()
	{
		if (_relativelayout_menu_pop_root.getVisibility() == RelativeLayout.VISIBLE)
			menu_hide();
		super.onPause();
	}

	public boolean onBackPressed()
	{
		if (_relativelayout_menu_pop_root.getVisibility() == RelativeLayout.VISIBLE)
			menu_hide();
		else
			return _fragment_home_comment.onBackPressed();

		return true;
	}
}
