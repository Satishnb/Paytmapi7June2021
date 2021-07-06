package org.testing.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParser;

public class JsonFileHandling {
	
	public static String ReadJson(String path) throws FileNotFoundException {
		File f= new File(path);
		FileReader fr= new FileReader(f);
		JSONTokener js= new JSONTokener(fr);
		try {	
			JSONObject ob= new JSONObject(js);
			return ob.toString();
		} 
		catch (Exception e) {
		}
		File f1= new File(path);
		FileReader fr1= new FileReader(f1);
		JSONTokener js1= new JSONTokener(fr1);
		
		try {
			JSONArray array= new JSONArray(js1);	
			return array.toString();


		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
		
	}

}
