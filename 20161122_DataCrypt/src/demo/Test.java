package demo;

import java.util.Map;


public class Test
{

	public static void main(String args[]) throws Exception
	{
		String testStr = "ɵ������";
//		String keyStr=null;
//		keyStr=DesCryptUtil.initKey();
//		
//		System.out.println("�������ַ���Ϊ��"+testStr+"\n"+"������ԿΪ��"+keyStr+"\n");
//		System.out.println("Step1����ʼ����...........\n");
//	    String encryptStr=DesCryptUtil.encryptData(testStr,keyStr);
//	    System.out.println("Step2�����ܺ���ַ���Ϊ��"+encryptStr+"\n");
//	    
//	    System.out.println("Step3����ʼ����...........\n");
//	    String decryptStr=DesCryptUtil.decryptData(encryptStr,keyStr);
//	    System.out.println("Step4�����ܺ��ԭʼ�ַ���Ϊ��"+decryptStr+"\n");
		
		Map<String, Object> keyMap =RSACryptUtil.initKey();
		String privateKeyStr=RSACryptUtil.getPrivateKey(keyMap);
		String publicKeyStr=RSACryptUtil.getPublicKey(keyMap);
		//System.out.print("˽Կ�ļ�Ϊ��\n"+privateKeyStr+"\n");
		//System.out.print("\n��Կ�ļ�Ϊ��\n"+publicKeyStr);
		
		String encryptStr=RSACryptUtil.encryptByPrivateKey(testStr, privateKeyStr);
		System.out.print("\nͨ��˽Կ���ܺ���ַ���Ϊ��\n"+encryptStr+"\n");
		
		String decryptStr=RSACryptUtil.decryptByPublicKey(encryptStr, publicKeyStr);
		System.out.print("\nͨ����Կ���ܺ���ַ���Ϊ��\n"+decryptStr+"\n\n");
		
		
		String encryptStr2=RSACryptUtil.encryptByPublicKey(testStr, publicKeyStr);
		System.out.print("\nͨ����Կ���ܺ���ַ���Ϊ��\n"+encryptStr2+"\n");
		
		String decryptStr2=RSACryptUtil.decryptByPrivateKey(encryptStr2, privateKeyStr);
		System.out.print("\nͨ��˽Կ���ܺ���ַ���Ϊ��\n"+decryptStr2);
		
		

	}
	
	

}
