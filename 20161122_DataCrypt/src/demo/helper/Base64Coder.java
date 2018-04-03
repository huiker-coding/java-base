package demo.helper;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Base64Coder 
{
	public final static String ENCODING = "UTF-8";
	
	public static String encode(byte[] data) throws Exception {

		return new String(Base64.encode(data).getBytes(),ENCODING);
	}
	
	public static byte[] decode(String data) throws Exception {

		return Base64.decode(data.getBytes(ENCODING));
	}
}
