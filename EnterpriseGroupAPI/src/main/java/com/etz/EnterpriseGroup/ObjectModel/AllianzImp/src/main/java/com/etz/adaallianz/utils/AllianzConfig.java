package com.etz.adaallianz.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class AllianzConfig {


    private static final Logger log;

    static {
        log = Logger.getLogger(AllianzConfig.class.getName());
    }

    public static String getValue(final String key) {
        final Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(new File("cfg\\allianz.properties"));
            //input = new FileInputStream(new File("D:\\vasgate\\cfg\\sochitel.properties"));

            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException ex) {
            System.out.println("Exception:: " + ex.getMessage());
            AllianzConfig.log.info((String) ("System.out.println(\"Exception:: \" + ex.getMessage());->PROCESS EXCEPTION=>" + ex.getMessage()));
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("Exception:: " + e.getMessage());
                    AllianzConfig.log.info((String) ("System.out.println(\"Exception:: \" + ex.getMessage());->CONFIG EXCEPTION=>" + e.getMessage()));

                }
            }
        }
    }


}
