package DB;
import java.security.MessageDigest;

/**
 * 雜湊加密
 * @author user
 *
 */
import java.security.MessageDigest;

public class AccountMD5 {
	public static String getMD5(String msg) {
		//16進制
		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(msg.getBytes("UTF-8"));
			
			//要使用Hash計算方式，來產生128位的長整數
			byte[] bytes = messageDigest.digest();
			StringBuffer sb = new StringBuffer();
			
			for(Byte b: bytes) {
				//將b資料右移四位，取得char中前四位的轉換
				sb.append(hexDigits[(b>>4 & 0x0f)]);
				sb.append(hexDigits[b & 0x0f]);
			}
			msg = sb.toString();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
}
