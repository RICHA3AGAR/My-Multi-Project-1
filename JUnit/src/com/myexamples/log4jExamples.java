package com.myexamples;

import org.apache.log4j.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.appender.rolling.*;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;


public class log4jExamples {

	/* Get actual class name to be printed on */
	static Logger logger = Logger.getLogger(log4jExamples.class.getName());
	
//	public static final String PATTERN_LAYOUT = "[%d] [%t] [%-5level] - %msg (%logger{1}:%L) %n%throwable";
//    public static final String LOG_FILE_NAME = "app.log";
//    public static final String LOG_FILE_NAME_PATTERN = LOG_FILE_NAME + "-yyyy.MM.dd";

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
    	logger.debug("Hello this is a debug message");
		logger.info("Hello this is an info message");

		logger.setLevel(Level.WARN);
		Thread.sleep(3000);
		logger.trace("Trace Message!");
		Thread.sleep(2000);
		logger.debug("Debug Message!");
		logger.info("Info Message!");
		logger.warn("Warn Message!");
		logger.error("Error Message!");
		logger.fatal("Fatal Message!");
		logger.debug("Hello this is an debug message");
		Thread.sleep(3500);
		logger.info("Hello this is an info message");
		
    }
    
	public static void Config() throws IOException, SQLException {
		
		PatternLayout patternLayout1 = new PatternLayout();   
		FileAppender logFileAppender = new FileAppender();
		logFileAppender.setLayout(patternLayout1);
		logFileAppender.setName("myFileAppender");
		
		String file1 = "C:\\Temp\\logging"+  ".log" ;
		logFileAppender.setFile(file1); 
		logFileAppender.activateOptions();
		logger.addAppender(logFileAppender); 
		
		ConsoleAppender ca = new ConsoleAppender();
		ca.setName("myConsoleAppender");
		PatternLayout patternLayout2 = new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN);
		patternLayout2.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
		ca.setLayout(patternLayout2);
	    ca.activateOptions();
	    logger.addAppender(ca);

//	    
//	    PatternLayout layout = new PatternLayout(PATTERN_LAYOUT);  
//        String oneDay = TimeUnit.DAYS.toMillis(1) + "";
//        String oneMB = (1024 * 1024) + "";
//        final TimeBasedTriggeringPolicy timeBasedTriggeringPolicy = TimeBasedTriggeringPolicy.createPolicy(oneDay,
//                "true");
//        final SizeBasedTriggeringPolicy sizeBasedTriggeringPolicy = SizeBasedTriggeringPolicy.createPolicy(oneMB);
//        final CompositeTriggeringPolicy policy = CompositeTriggeringPolicy.createPolicy(timeBasedTriggeringPolicy,
//                sizeBasedTriggeringPolicy);
//        final DefaultRolloverStrategy strategy = DefaultRolloverStrategy.createStrategy("7", "1", null,
//                Deflater.DEFAULT_COMPRESSION + "", this);
//        Appender appender = new RollingFileAppender ( LOG_FILE_NAME, LOG_FILE_NAME_PATTERN, "true",
//                "app-log-file-appender", "true", "true", policy, strategy, layout, null, null, null, null, null);
//        logger.addAppender(appender);
        
        
	    
	    
	    
	    PatternLayout patternLayout3 = new PatternLayout();   
	    RollingFileAppender rollingFileAppender = new RollingFileAppender();
	    
	    rollingFileAppender.setThreshold(Priority.DEBUG);
	    rollingFileAppender.setAppend(false);
	    rollingFileAppender.setMaxFileSize("2KB");
	    rollingFileAppender.setMaxBackupIndex(2);
	    rollingFileAppender.setImmediateFlush(true);
	    

	    		
	    
	    rollingFileAppender.setLayout(patternLayout3);
	    rollingFileAppender.setName("myrollingFileAppender");
	    //rollingFileAppender.setThreshold("DEBUG");
		
	    String file2 = "C:\\Temp\\logging"+  ".log" ;
	    rollingFileAppender.setFile(file2); 
	   
	    rollingFileAppender.activateOptions();
		logger.addAppender(logFileAppender); 
		
		
		
		//BasicConfigurator.configure();

		

	}
}

// }
