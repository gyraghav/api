package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	
	public int response_STATUS_CODE_200=200;
	
	
	
	public Properties prop;
	public FileInputStream fis=null;
	
	public TestBase() {
		try {
			prop=new Properties();
			fis=new FileInputStream(System.getProperty("user.dir")+ "/config/config.properties");
			prop.load(fis);
			
		}catch(Exception e) {
			System.out.println("got exception while reading config: "+e.getMessage());
		}
		
	}

}
