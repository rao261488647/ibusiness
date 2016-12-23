package com.ibusiness.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * properties文件读取类
 * 
 * @author JiangBo
 *
 */
public class PropertiesReader {
    private String fileName = "";
    private Properties property = null;

    public PropertiesReader(String fileName) {
        try {
            this.fileName = fileName;
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
            property = new Properties();
            property.load(in);
            in.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public String getValue(String key) {
        return property.getProperty(key);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
