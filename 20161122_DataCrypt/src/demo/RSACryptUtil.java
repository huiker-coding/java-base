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
 * �ǶԳƼ����㷨(RSA)
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

	/** (����)ʹ��˽Կ */
	public static String encryptByPrivateKey(String dataStr, String key)
	{
		try
		{
			byte[] keyBytes = Base64Coder.decode(key);
			// ȡ��˽Կ
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
			System.out.println("˽Կ���ܳ���");
			return null;
		}
	}

	/** (����)ʹ�ù�Կ */
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
			System.out.println("��Կ���ܳ���");
			return null;
		}
	}

	/** (����)ʹ��˽Կ */
	public static String decryptByPrivateKey(String dataStr, String key) throws Exception
	{
		try
		{
			byte[] keyBytes = Base64Coder.decode(key);

			// ȡ��˽Կ
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			byte[] data = Base64Coder.decode(dataStr);
			byte[] newPlainText = cipher.doFinal(data); // ���ԭ��
			return new String(newPlainText); // ��������
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("˽Կ���ܳ���");
			return null;
		}
	}

	/** (����)ʹ�ù�Կ */
	public static String decryptByPublicKey(String dataStr, String key)
	{
		try
		{
			byte[] keyBytes = Base64Coder.decode(key);
			// ȡ�ù�Կ
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicKey = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			byte[] data = Base64Coder.decode(dataStr);
			byte[] newPlainText = cipher.doFinal(data); // ���ԭ��
			return new String(newPlainText); // ��������
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("��Կ���ܳ���");
			return null;
		}
	}

	/** ȡ��˽Կ */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception
	{
		if (null == keyMap)
		{
			return null;
		}
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64Coder.encode(key.getEncoded());
	}

	/** ȡ�ù�Կ */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception
	{
		if (null == keyMap)
		{
			return null;
		}
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64Coder.encode((key.getEncoded()));
	}

	/** ��ʼ����Կ */
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
			System.out.print("��ʼ����Կʧ�ܣ�");
			return null;
		}
	}

}
