package demo;

import java.util.Map;


public class Test
{

	public static void main(String args[]) throws Exception
	{
		String testStr = "傻逼吴鹏";
//		String keyStr=null;
//		keyStr=DesCryptUtil.initKey();
//		
//		System.out.println("被加密字符串为："+testStr+"\n"+"加密密钥为："+keyStr+"\n");
//		System.out.println("Step1：开始加密...........\n");
//	    String encryptStr=DesCryptUtil.encryptData(testStr,keyStr);
//	    System.out.println("Step2：加密后的字符串为："+encryptStr+"\n");
//	    
//	    System.out.println("Step3：开始解密...........\n");
//	    String decryptStr=DesCryptUtil.decryptData(encryptStr,keyStr);
//	    System.out.println("Step4：解密后的原始字符串为："+decryptStr+"\n");
		
		Map<String, Object> keyMap =RSACryptUtil.initKey();
		String privateKeyStr=RSACryptUtil.getPrivateKey(keyMap);
		String publicKeyStr=RSACryptUtil.getPublicKey(keyMap);
		//System.out.print("私钥文件为：\n"+privateKeyStr+"\n");
		//System.out.print("\n公钥文件为：\n"+publicKeyStr);
		
		String encryptStr=RSACryptUtil.encryptByPrivateKey(testStr, privateKeyStr);
		System.out.print("\n通过私钥加密后的字符串为：\n"+encryptStr+"\n");
		
		String decryptStr=RSACryptUtil.decryptByPublicKey(encryptStr, publicKeyStr);
		System.out.print("\n通过公钥解密后的字符串为：\n"+decryptStr+"\n\n");
		
		
		String encryptStr2=RSACryptUtil.encryptByPublicKey(testStr, publicKeyStr);
		System.out.print("\n通过公钥加密后的字符串为：\n"+encryptStr2+"\n");
		
		String decryptStr2=RSACryptUtil.decryptByPrivateKey(encryptStr2, privateKeyStr);
		System.out.print("\n通过私钥解密后的字符串为：\n"+decryptStr2);
		
		

	}
	
	

}
