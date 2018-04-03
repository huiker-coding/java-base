package demo;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import demo.helper.Base64Coder;

/**
 * 非对称加密算法(RSA)
 * 
 * @date 20161122
 * @author z.h
 * */
public class RSACryptUtil
{
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/** (加密)使用私钥 */
	public static String encryptByPrivateKey(String dataStr, String key)
	{
		try
		{
			byte[] keyBytes = Base64Coder.decode(key);
			// 取得私钥
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] cipherTextdes = cipher.doFinal(dataStr.getBytes());
			String HexcipherText = Base64Coder.encode(cipherTextdes);

			return HexcipherText;
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("私钥加密出错");
			return null;
		}
	}

	/** (加密)使用公钥 */
	public static String encryptByPublicKey(String dataStr, String key)
	{
		try
		{
			byte[] keyBytes = Base64Coder.decode(key);

			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicKey = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] cipherTextdes = cipher.doFinal(dataStr.getBytes());
			String HexcipherText = Base64Coder.encode(cipherTextdes);

			return HexcipherText;
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("公钥加密出错");
			return null;
		}
	}

	/** (解密)使用私钥 */
	public static String decryptByPrivateKey(String dataStr, String key) throws Exception
	{
		try
		{
			byte[] keyBytes = Base64Coder.decode(key);

			// 取得私钥
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			byte[] data = Base64Coder.decode(dataStr);
			byte[] newPlainText = cipher.doFinal(data); // 输出原文
			return new String(newPlainText); // 返回明文
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("私钥解密出错");
			return null;
		}
	}

	/** (解密)使用公钥 */
	public static String decryptByPublicKey(String dataStr, String key)
	{
		try
		{
			byte[] keyBytes = Base64Coder.decode(key);
			// 取得公钥
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicKey = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			byte[] data = Base64Coder.decode(dataStr);
			byte[] newPlainText = cipher.doFinal(data); // 输出原文
			return new String(newPlainText); // 返回明文
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("公钥解密出错");
			return null;
		}
	}

	/** 取得私钥 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception
	{
		if (null == keyMap)
		{
			return null;
		}
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64Coder.encode(key.getEncoded());
	}

	/** 取得公钥 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception
	{
		if (null == keyMap)
		{
			return null;
		}
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64Coder.encode((key.getEncoded()));
	}

	/** 初始化密钥 */
	public static Map<String, Object> initKey()
	{
		try
		{
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();

			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			Map<String, Object> keyMap = new HashMap<String, Object>(2);
			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);
			return keyMap;
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.print("初始化密钥失败！");
			return null;
		}
	}

}
