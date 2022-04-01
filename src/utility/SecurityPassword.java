package utility;

import java.security.MessageDigest;

public class SecurityPassword {
	public static String encoding(String pw) {
		String encodingString = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(pw.getBytes());
			byte[] encodeData = md.digest();
			for(int i = 0; i < encodeData.length; i++) {
				encodingString += Integer.toHexString(encodeData[i]&0xFF);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return encodingString;
	}
}
