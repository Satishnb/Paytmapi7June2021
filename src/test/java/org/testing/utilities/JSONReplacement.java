package org.testing.utilities;

import java.util.regex.Pattern;

public class JSONReplacement {
	
	public static String changeVariable(String bodyData, String variableName, String variableValue) {
		
		bodyData= bodyData.replaceAll(Pattern.quote("{{"+variableName+"}}"),variableValue);
		return bodyData;
	}

}
