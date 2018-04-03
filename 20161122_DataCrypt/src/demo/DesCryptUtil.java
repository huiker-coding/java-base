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
 * �ԳƼ����㷨(DES&DESede)
 * 
 * @date 20161122
 * @author z.h
 * */
public class DesCryptUtil
{
	private static final String IV_PARAMETER_SPEC = "12345678"; // IV��������Ϊ8λ�ֽڳ���
	private static final String TRANSFORMATION = "DES/OFB/PKCS5Padding"; // DES&DESede
	private static final String ALGORITHM = "DES"; // DES&DESede

	/** ������Կ���ɼ����ַ��� */
	public static String encryptData(String dataStr, String keyStr)
	{
		try
		{
			IvParameterSpec IvParameters = new IvParameterSpec(IV_PARAMETER_SPEC.getBytes());
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, str2Key(keyStr), IvParameters);// cipher�����ʼ��������Ϊ����
			byte[] cipherTextdes = cipher.doFinal(dataStr.getBytes());// �������ݼ��ܣ��������
			String HexcipherText = Base64Coder.encode(cipherTextdes);

			return HexcipherText;// �����ַ����͵�����
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("���ܳ���");
			return null;
		}
	}

	/** ������Կ���������ַ��� */
	public static String decryptData(String dataStr, String keyStr)
	{
		try
		{
			byte[] data = Base64Coder.decode(dataStr);
			IvParameterSpec IvParameters = new IvParameterSpec(IV_PARAMETER_SPEC.getBytes());
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, str2Key(keyStr), IvParameters);

			byte[] newPlainText = cipher.doFinal(data); // ���ԭ��
			return new String(newPlainText); // ��������
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("���ܳ���");
			return null;
		}
	}

	/** ������Կ�ַ��� */
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
			System.out.println("������Կ�ַ�������");
			return null;
		}
	}

	/** ת����Կ�����ַ�����ԿתΪ��Կ���� */
	private static SecretKey str2Key(String key) throws Exception
	{
		// ��ʹ��DESede�㷨ʱʹ����������
		// DESedeKeySpec dks = new DESedeKeySpec(Base64Coder.decode(key));
		DESKeySpec dks = new DESKeySpec(Base64Coder.decode(key));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(dks);

		// ��ʹ�������ԳƼ����㷨ʱ����AES��Blowfish���㷨ʱ�������������滻�������д���
		// SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
		return secretKey;
	}

}
