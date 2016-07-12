package com.bonepeople.wakeup;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import com.bonepeople.wakeup.utils.NetWorkUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdbActivity extends Activity implements View.OnClickListener
{
	private Button _button_open;
	private TextView _text_ip;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adb);

		_button_open = (Button) findViewById(R.id.button_open);
		_text_ip = (TextView) findViewById(R.id.textview_ip);

		_button_open.setOnClickListener(this);
	}

	public String getLocalIpAddress()
	{
		try
		{
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
			{
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress())
					{
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		}
		catch (SocketException ex)
		{
			Log.e("adb activity", ex.toString());
		}
		return null;
	}

	public static void printParameter(NetworkInterface ni) throws Exception
	{
		System.out.println(" Name = " + ni.getName());
		System.out.println(" Display Name = " + ni.getDisplayName());
		System.out.println(" Is up = " + ni.isUp());
		System.out.println(" Support multicast = " + ni.supportsMulticast());
		System.out.println(" Is loopback = " + ni.isLoopback());
		System.out.println(" Is virtual = " + ni.isVirtual());
		System.out.println(" Is point to point = " + ni.isPointToPoint());
		System.out.println(" Hardware address = " + ni.getHardwareAddress());
		System.out.println(" MTU = " + ni.getMTU());

		System.out.println("================ List of Interface Addresses:");
		List<InterfaceAddress> list = ni.getInterfaceAddresses();
		Iterator<InterfaceAddress> it = list.iterator();

		while (it.hasNext())
		{
			InterfaceAddress ia = it.next();
			System.out.println(" Address = " + ia.getAddress());
			System.out.println(" Broadcast = " + ia.getBroadcast());
			System.out.println(" Network prefix length = " + ia.getNetworkPrefixLength());
			System.out.println("");
		}
		System.out.println("================ List over");
	}

	@Override
	public void onClick(View v)
	{
		// Toast.makeText(this, "open", Toast.LENGTH_SHORT).show();
		// System.out.println("send su");
		//
		// CommandResult _result = ShellUtils.execCommand("setprop service.adb.tcp.port 5555", true, true);
		// System.out.println("result:" + _result.result);
		// System.out.println("success:" + _result.successMsg);
		// System.out.println("error:" + _result.errorMsg);
		// System.out.println("over");

		try
		{
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
			{
				NetworkInterface intf = en.nextElement();
				printParameter(intf);
			}
		}
		catch (Exception ex)
		{
			Log.e("adb activity", ex.toString());
		}

		_text_ip.setText(NetWorkUtil.getLocalIpAddress(this));
	}
}
