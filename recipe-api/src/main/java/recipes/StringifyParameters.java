package recipes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class StringifyParameters {
	
	//This method specifically creates a string for the GET request parameters
	public static String stringifyParameters(Map<String, String> p) throws UnsupportedEncodingException{
		StringBuilder str = new StringBuilder();
		
		for(Map.Entry<String, String> entr: p.entrySet()) {
			str.append(URLEncoder.encode(entr.getKey(), "UTF-8"));
			str.append("=");
			str.append(URLEncoder.encode(entr.getValue(), "UTF-8"));
			str.append("&");
		}
		
		String strResult = str.toString();
		return strResult;
	}

}
