package demo;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import demo.helper.Base64Coder;

/**
 * 对称加密算法(DES&DESede)
 * 
 * @date 20161122
 * @author z.h
 * */
public class DesCryptUtil
{
	private static final String IV_PARAMETER_SPEC = "12345678"; // IV向量必须为8位字节长度
	private static final String TRANSFORMATION = "DES/OFB/PKCS5Padding"; // DES&DESede
	private static final String ALGORITHM = "DES"; // DES&DESede

	/** 根据密钥生成加密字符串 */
	public static String encryptData(String dataStr, String keyStr)
	{
		try
		{
			IvParameterSpec IvParameters = new IvParameterSpec(IV_PARAMETER_SPEC.getBytes());
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, str2Key(keyStr), IvParameters);// cipher对象初始化，设置为加密
			byte[] cipherTextdes = cipher.doFinal(dataStr.getBytes());// 结束数据加密，输出密文
			String HexcipherText = Base64Coder.encode(cipherTextdes);

			return HexcipherText;// 返回字符串型的密文
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("加密出错");
			return null;
		}
	}

	/** 根据密钥解析加密字符串 */
	public static String decryptData(String dataStr, String keyStr)
	{
		try
		{
			byte[] data = Base64Coder.decode(dataStr);
			IvParameterSpec IvParameters = new IvParameterSpec(IV_PARAMETER_SPEC.getBytes());
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, str2Key(keyStr), IvParameters);

			byte[] newPlainText = cipher.doFinal(data); // 输出原文
			return new String(newPlainText); // 返回明文
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("解密出错");
			return null;
		}
	}

	/** 生成密钥字符串 */
	public static String initKey() throws Exception
	{
		return initKey(null);
	}

	public static String initKey(String seed)
	{
		try
		{
			SecureRandom secureRandom = (null != seed) ? new SecureRandom(Base64Coder.decode(seed)) : new SecureRandom();
			KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
			kg.init(secureRandom);
			SecretKey secretKey = kg.generateKey();

			return Base64Coder.encode(secretKey.getEncoded());
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("生成密钥字符串出错");
			return null;
		}
	}

	/** 转换密钥，将字符串密钥转为密钥对象 */
	private static SecretKey str2Key(String key) throws Exception
	{
		// 当使用DESede算法时使用下述代码
		// DESedeKeySpec dks = new DESedeKeySpec(Base64Coder.decode(key));
		DESKeySpec dks = new DESKeySpec(Base64Coder.decode(key));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(dks);

		// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
		// SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
		return secretKey;
	}

}
