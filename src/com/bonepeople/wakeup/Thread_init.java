package com.bonepeople.wakeup;

import com.bonepeople.wakeup.utils.DataUtils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class Thread_init extends Thread
{
	private Handler _handler;
	private Context _context;

	Thread_init(Context _context, Handler _handler)
	{
		this._context = _context;
		this._handler = _handler;
	}

	public void run()
	{
		long startTime = System.currentTimeMillis();
		Message msg = Message.obtain();

		if (DataUtils.init(_context))
			msg.what = SplashActivity.INIT_SUCCESSFUL;
		else
			msg.what = SplashActivity.INIT_FAILED;

		long endTime = System.currentTimeMillis();
		long dtime = endTime - startTime;

		if (dtime < 2000)
		{
			try
			{
				// Thread.sleep(2000 - dtime);
				Thread.sleep(200 - dtime);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		msg.arg1 = (int) dtime;
		_handler.sendMessage(msg);
	}

}
