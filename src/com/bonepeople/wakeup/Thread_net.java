package com.bonepeople.wakeup;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.util.Log;

public class Thread_net extends Thread
{
	private String _ip;

	public Thread_net(String _ip)
	{
		this._ip = _ip;
	}

	public void run()
	{
		try
		{
			boolean _temp_bool = InetAddress.getByName(this._ip).isAnyLocalAddress();
			
			Log.i("net", _ip);
			Log.i("net", String.valueOf(_temp_bool));
			
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
