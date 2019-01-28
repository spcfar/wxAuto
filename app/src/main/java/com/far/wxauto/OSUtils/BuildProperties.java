package com.far.wxauto.OSUtils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class BuildProperties {
    private final Properties properties = new Properties();

    private BuildProperties()
            throws IOException {
        this.properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
    }

    public static BuildProperties newInstance()
            throws IOException {
        return new BuildProperties();
    }

    public boolean containsKey(Object paramObject) {
        return this.properties.containsKey(paramObject);
    }

    public boolean containsValue(Object paramObject) {
        return this.properties.containsValue(paramObject);
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        return this.properties.entrySet();
    }

    public String getProperty(String paramString) {
        return this.properties.getProperty(paramString);
    }

    public String getProperty(String paramString1, String paramString2) {
        return this.properties.getProperty(paramString1, paramString2);
    }

    public boolean isEmpty() {
        return this.properties.isEmpty();
    }

    public Set<Object> keySet() {
        return this.properties.keySet();
    }

    public Enumeration<Object> keys() {
        return this.properties.keys();
    }

    public int size() {
        return this.properties.size();
    }

    public Collection<Object> values() {
        return this.properties.values();
    }
}
