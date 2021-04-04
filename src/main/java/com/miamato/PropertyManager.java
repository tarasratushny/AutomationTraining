package com.miamato;

import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PropertyManager {

    private static final Logger logger = LogManager.getLogger(PropertyManager.class.getSimpleName());
    //private static PropertyManager instance = null;
    private static final Properties properties = new Properties();

    public PropertyManager(String testDataFileName){
        loadProperties(System.getProperty("test.data.folder") + testDataFileName);
    }

    /*private String getTestDataFileName(String testDataFileName){
        return System.getProperty("test.data.file.name") == null ?
            "default.properties" : System.getProperty("test.data.file.name");
    }*/

    public String getProperty(String propertyName){
    //    if(instance == null)
    //        instance = new PropertyManager();
        return properties.getProperty(propertyName);
    }

    private void loadProperties(String filePath){
        logger.info("Trying to access property file: " + filePath);
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            properties.load(inputStream);
        } catch (Exception e) {
            logger.error("Property file with path: " + filePath + " cannot be opened");
            LogUtil.logStackTrace(e, logger);
        }
    }
}
