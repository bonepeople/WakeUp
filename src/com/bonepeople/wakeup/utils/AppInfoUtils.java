package com.bonepeople.wakeup.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class AppInfoUtils
{
	public static String getAppVersion(Context context)
	{
		PackageManager pm = context.getPackageManager();
		try
		{
			PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
			return info.versionName;
		}
		catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		return "";
	}
}
