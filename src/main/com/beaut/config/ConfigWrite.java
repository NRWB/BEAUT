package com.beaut.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/*
Normally, Java properties file is used to store project configuration data or settings.
In this tutorial, we will show you how to read and write to/from a properties file.

1. Write to properties file
Set the property value, and write it into a properties file named config.properties.

Output

config.properties
#Fri Jan 17 22:37:45 MYT 2014
dbpassword=password
database=localhost
dbuser=mkyong
P.S If file path is not specified, then this properties file will be stored in your project root folder.
*/

public class ConfigWrite {

    private String fp;
    private Properties prop;
    
    // ex. "config.properties"
    public ConfigWrite(String file) {
        this.fp = file;
        this.prop = new Properties();
    }

    public void add(String key, String value) {
        this.prop.setProperty(key, value);
    }

    public void save() {
        try (OutputStream output = new FileOutputStream(this.fp)) {
            this.prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
