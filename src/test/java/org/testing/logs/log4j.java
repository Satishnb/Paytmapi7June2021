package org.testing.logs;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class log4j {
	
	static Logger logger= LogManager.getLogger(log4j.class);
 
	public static void startTestCase(String sTestCaseName){

		logger.info("****************************************************************************************");
		logger.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		logger.info("****************************************************************************************");

		}
	
	     public static void info(String message) {
		 
		     logger.info(message);
		 
		 }
		 
		 public static void warn(String message) {
		 
			 logger.warn(message);
		 
		 }
		 
		 public static void error(String message) {
		 
			 logger.error(message);
		 
		 }
		 
		 public static void fatal(String message) {
		 
			 logger.fatal(message);
		 
		 }
		 
		 public static void debug(String message) {
		 
			 logger.debug(message);
		 
		 }
		 
		 public static void trace(String message) {
			 
			 logger.trace(message);
		 
		 }
		 
		 public static void endTestCase(String eTestCaseName){

			  logger.info("****************************************************************************************");
			  logger.info("$$$$$$$$$$$$$$$$$$$$$                 "+eTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
			  logger.info("****************************************************************************************");

				}
		

}
