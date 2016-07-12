package com.bonepeople.wakeup;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.bonepeople.wakeup.model.ComputerInfo;
import com.bonepeople.wakeup.utils.DataUtils;

import android.os.Message;

public class Thread_net extends Thread
{
	private String _string_ip;
	private String _string_mac;

	public Thread_net(String _name)
	{
		ComputerInfo _info = DataUtils.get_computerinfo(_name);
		this._string_mac = _info.get_mac();
		this._string_ip = _info.get_ip();
	}

	public void run()
	{
		Message msg = Message.obtain();

		if (send_message())
			msg.what = HomeActivity.WAKE_SUCCESSFUL;
		else
			msg.what = HomeActivity.WAKE_FAILED;
		HomeActivity.handler.sendMessage(msg);
	}

	public boolean send_message()
	{
		DatagramSocket ds = null;
		int port = 1000;
		byte[] destMac = getMacBytes(_string_mac);
		if (destMac == null)
			return false;

		try
		{
			InetAddress destHost = InetAddress.getByName(_string_ip);
			// construct packet data
			byte[] magicBytes = new byte[102];
			// 将数据包的前6位放入0xFF即 "FF"的二进制
			for (int i = 0; i < 6; i++)
				magicBytes[i] = (byte) 0xFF;
			// 从第7个位置开始把mac地址放入16次
			for (int i = 0; i < 16; i++)
			{
				for (int j = 0; j < destMac.length; j++)
				{
					magicBytes[6 + destMac.length * i + j] = destMac[j];
				}
			}
			// create packet
			DatagramPacket dp = null;
			dp = new DatagramPacket(magicBytes, magicBytes.length, destHost, port);
			ds = new DatagramSocket();
			ds.send(dp);
		}
		catch (Exception e)
		{
			return false;
		}
		ds.close();
		return true;
	}

	private byte[] getMacBytes(String macStr) throws IllegalArgumentException
	{
		byte[] bytes = new byte[6];
		String[] hex = macStr.split("(\\:|\\-)");
		try
		{
			for (int i = 0; i < 6; i++)
			{
				bytes[i] = (byte) Integer.parseInt(hex[i], 16);
			}
		}
		catch (NumberFormatException e)
		{
			return null;
		}
		return bytes;
	}
}
