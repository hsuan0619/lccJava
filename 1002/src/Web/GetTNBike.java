/**
 * 從往上抓json格式的文件
 * 將文件中的資料分別取出
 */
package Web;

import java.net.URL;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetTNBike {
	public Object[][] getBike() throws Exception{
		
		String url = "http://tbike-data.tainan.gov.tw/Service/StationStatus/Json\r\n";
		
		InputStream is = new URL(url).openStream();
		
		Object[][] data = null;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			int cp;
			
			//物件型態，其實跟字串很像
			//差在不會將字串池刪掉，可以去做字串內容的修改
			StringBuilder sb=new StringBuilder();
			while((cp = br.read())!=-1) {
				//將每一行抓取並新增進去sb之中
				sb.append((char)cp);
			}
			
			JSONArray arr = new JSONArray(sb.toString());
			
			data= new Object[arr.length()][3];
			
			for(int i=0;i<arr.length();i++) {
				JSONObject obj=arr.getJSONObject(i);
				//getString是抓取字串類型的value
				//後面的"放keyValue"
				data[i][0]=obj.getString("StationName");
				data[i][1]=obj.getInt("AvaliableBikeCount");
				data[i][2]=obj.getInt("AvaliableSpaceCount");
			}	
		}finally{
			is.close();
		}
		
		return data;
		
	}
}
