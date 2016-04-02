package com.bonepeople.wakeup;

import android.os.Handler;
import android.os.Message;

public class Thread_init extends Thread
{
	private Handler handler;

	Thread_init(Handler handler)
	{
		this.handler = handler;
	}

	public void run()
	{
		long startTime = System.currentTimeMillis();

		Message msg = Message.obtain();

		msg.what = SplashActivity.INIT_PASS;
		
		long endTime = System.currentTimeMillis();
		long dtime = endTime - startTime;

		if (dtime < 2000)
		{
			try
			{
//				Thread.sleep(2000 - dtime);
				Thread.sleep(200 - dtime);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		msg.arg1 = (int) dtime;
		handler.sendMessage(msg);
	}

}
