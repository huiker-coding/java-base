package demo;

import java.security.MessageDigest;

/**
 * ��������㷨(MD5 & SHA1)
 * 
 * @date 20161122
 * @author z.h
 * */
public class SingleCryptUtil
{

	/** (MD5����) ����32λ���� */
	public static String str2MD5(String inStr)
	{
		if (inStr == null)
		{
			return null;
		}

		MessageDigest md = null;
		String outStr = null;
		try
		{
			md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(inStr.getBytes());
			outStr = byte2Str(digest);
		} catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
		return outStr;
	}

	
	/** (SHA1����) ����40λ���� */
	public static String str2SHA1(String inStr)
	{
		if (inStr == null)
		{
			return null;
		}

		MessageDigest md = null;
		String outStr = null;
		try
		{
			md = MessageDigest.getInstance("SHA-1"); // ѡ��SHA-1��Ҳ����ѡ��MD5
			byte[] digest = md.digest(inStr.getBytes()); // ���ص���byte[]��Ҫת��ΪString�洢�ȽϷ���
			outStr = byte2Str(digest);
		} catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
		return outStr;
	}

	private static String byte2Str(byte[] digest)
	{
		StringBuffer sb = new StringBuffer();
		String tempStr = null;

		for (int i = 0; i < digest.length; i++)
		{
			tempStr = (Integer.toHexString(digest[i] & 0xff));

			if (tempStr.length() == 1)
			{
				sb.append("0" + tempStr);
			} else
			{
				sb.append(tempStr);
			}
		}
		return sb.toString();
	}

}
