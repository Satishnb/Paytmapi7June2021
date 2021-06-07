package org.testing.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class JsonFileHandling {
	
	public static String ReadJson(String path) throws FileNotFoundException {
		File f= new File(path);
		FileReader fr= new FileReader(f);
		JSONTokener js= new JSONTokener(fr);
		JSONObject ob= new JSONObject(js);
		return ob.toString();
		
		
	}

}
