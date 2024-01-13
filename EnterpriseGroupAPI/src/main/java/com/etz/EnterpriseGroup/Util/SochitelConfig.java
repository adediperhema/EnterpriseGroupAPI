/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etz.EnterpriseGroup.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author rhema.adedipe
 */
public class SochitelConfig {

	private static final Logger log;

	static {
		log = Logger.getLogger(SochitelConfig.class.getName());
	}

	public static String getValue(final String key) {
		final Properties prop = new Properties();
		InputStream input = null;
		try {
//		input = new FileInputStream(new File("D:\\vasgate\\cfg\\sochitel.properties"));
			// input = new FileInputStream(new
			// File("C:\\Users\\rhema.adedipe\\eclipse-workspace\\EnterpriseGroup\\Enterprise_group.properties"));
			// input = new FileInputStream(new
			// File("D:\\vasgate\\cfg\\Enterprise_group.properties"));
			input = new FileInputStream(new File("cfg" + File.separator + "Enterprise_group.properties"));
			
			prop.load(input);
			return prop.getProperty(key);
		} catch (IOException ex) {
			System.out.println("Exception:: " + ex.getMessage());
			SochitelConfig.log
					.info((Object) ("System.out.println(\"Exception:: \" + ex.getMessage());->PROCESS EXCEPTION=>"
							+ ex.getMessage()));
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("Exception:: " + e.getMessage());
					SochitelConfig.log.info(
							(Object) ("System.out.println(\"Exception:: \" + ex.getMessage());->CONFIG EXCEPTION=>"
									+ e.getMessage()));
				}
			}
		}
	}
}
