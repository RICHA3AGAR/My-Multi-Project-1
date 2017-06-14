package com.myexamples;

import java.io.File;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

//http://www.codejava.net/coding/how-to-configure-log4j-as-logging-mechanism-in-java
public class ProgrammaticLog4jExample {

	static Logger logger = Logger.getLogger(ProgrammaticLog4jExample.class);
	
	public static void main(String[] args) {
        String log4jConfigFile = System.getProperty("user.dir")
                + File.separator + "log4j.xml";
        DOMConfigurator.configure(log4jConfigFile);
 
        logger.debug("this is a debug log message");
        logger.info("this is a information log message");
        logger.warn("this is a warning log message");
    }
	
//	public static void main(String[] args) {
//        String log4jConfigFile = System.getProperty("user.dir")
//                + File.separator + "log4j.properties";
//        PropertyConfigurator.configure(log4jConfigFile);
//        logger.debug("this is a debug log message");
//        logger.info("this is a information log message");
//        logger.warn("this is a warning log message");
//    }
// 
	
	
//	public static void main(String[] args) {
//		// creates pattern layout
//		PatternLayout layout = new PatternLayout();
//		String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
//		layout.setConversionPattern(conversionPattern);
//		
//		// creates console appender
//		ConsoleAppender consoleAppender = new ConsoleAppender();
//		consoleAppender.setLayout(layout);
//		consoleAppender.activateOptions();
//
//		//creates file appender
//		FileAppender fileAppender = new FileAppender();
//		fileAppender.setFile("applog3.txt");
//		fileAppender.setLayout(layout);
//		fileAppender.activateOptions();
//
//		// configures the root logger
//		Logger rootLogger = Logger.getRootLogger();
//		rootLogger.setLevel(Level.DEBUG);
//		rootLogger.addAppender(consoleAppender);
//		rootLogger.addAppender(fileAppender);
//		
//		// creates a custom logger and log messages
//		Logger logger = Logger.getLogger(ProgrammaticLog4jExample.class);
//		logger.debug("this is a debug log message");
//		logger.info("this is a information log message");
//		logger.warn("this is a warning log message");
//		
//	}


}
