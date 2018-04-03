package demo;

import java.security.MessageDigest;

/**
 * 单向加密算法(MD5 & SHA1)
 * 
 * @date 20161122
 * @author z.h
 * */
public class SingleCryptUtil
{

	/** (MD5加密) 生成32位密文 */
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

	
	/** (SHA1加密) 生成40位密文 */
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
			md = MessageDigest.getInstance("SHA-1"); // 选择SHA-1，也可以选择MD5
			byte[] digest = md.digest(inStr.getBytes()); // 返回的是byte[]，要转化为String存储比较方便
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
