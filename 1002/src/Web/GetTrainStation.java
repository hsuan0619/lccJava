package Web;

/**
 * 如果要連線到加密網站需要使用java ssl的連線
 */


import java.net.URL;
import java.io.*;
import org.json.*;
import DB.TrainSQL;

public class GetTrainStation {
	public static void main(String[] args) throws Exception {
		getStation();
	}
	static public void getStation() throws Exception{
		TrainSQL train = new TrainSQL();
		FileInputStream fis = null;
		BufferedReader br = null;
		String fileName = "train.json";
		
		try {
			fis = new FileInputStream(fileName);
			InputStreamReader reader = new InputStreamReader(fis,"utf-8");
			br = new BufferedReader(reader);
			
			StringBuilder sb=new StringBuilder();
			String data;
			while((data = br.readLine())!=null) {
				//將每一行抓取並新增進去sb之中
				sb.append(data);
			}
			//System.out.println(sb.toString());
			
			JSONArray arr = new JSONArray(sb.toString());
			
			String stationName,station;
			
			for(int i=0;i<arr.length();i++) {
				JSONObject obj=arr.getJSONObject(i);
				stationName = obj.getString("stationName");
				train.inser(stationName);
			}
			
		}finally{
			br.close();
		}
		
		
		
	}
}
