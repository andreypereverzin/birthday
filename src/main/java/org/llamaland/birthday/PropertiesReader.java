package org.llamaland.birthday;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public Properties readProperties(String fileName) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        Properties props = new java.util.Properties();
        props.load(in);
        return props;
    }
}
