package com.cj.bootstrap.util;

import java.util.HashMap;
import java.util.Map;

public class ByteLoader extends ClassLoader {
    public Map<Object, Object> classes = new HashMap<>();

    public Class<?> findClass(String s) {
        Class<?> clazz = null;
        try {
            super.findClass(s);
        } catch (Exception ignored) {}
        try {
            if (this.classes.containsKey(s)) {
                clazz = defineClass(s, (byte[]) this.classes.get(s), 0, ((byte[])this.classes.get(s)).length);
            }
        } catch (Throwable err) {
            err.printStackTrace();
        }
        return clazz;
    }
}
