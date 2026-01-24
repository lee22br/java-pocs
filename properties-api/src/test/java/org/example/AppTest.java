package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class AppTest {
    private Properties appProps;

    @BeforeEach
    public void setup() throws Exception {
        appProps = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("app.properties");
        appProps.load(is);
    }
    @Test
    public void testGetProperty(){
        String version = appProps.getProperty("version");
        String name = appProps.getProperty("name", "defaultName");
        String date = appProps.getProperty("date", "1982-04-28");
        String appDownloadAddr = appProps.getProperty("downloadAddr");
        String[] x = appProps.getProperty("list").split(",");

        assertEquals("1.0", version);
        assertEquals("TestApp", name);
        assertEquals("2016-11-12", date);
        assertEquals(6, x.length);
        assertNull(appDownloadAddr);
    }

    @Test
    public void testSetProperty(){
        appProps.setProperty("version", "2.0");
        appProps.setProperty("date", "2000-01-01");

        String version = appProps.getProperty("version");
        String date = appProps.getProperty("date");

        assertEquals("2.0", version);
        assertEquals("2000-01-01", date);
    }

    @Test
    public void testRemoveProperty(){
        String versionBeforeRemoval = appProps.getProperty("version");
        assertEquals("1.0", versionBeforeRemoval);

        appProps.remove("version");
        String versionAfterRemoval = appProps.getProperty("version");
        assertNull(versionAfterRemoval);
    }

    @Test
    public void testStoreProperty() throws IOException {
        File file = new File("src/test/resources/app.properties");
        appProps.setProperty("data2", "2026-01-19");
        OutputStream os = new FileOutputStream(file);
        appProps.store(os,"teste");
    }
}
