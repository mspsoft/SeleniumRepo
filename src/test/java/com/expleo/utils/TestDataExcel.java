package com.expleo.utils;

import java.util.HashMap;
import java.util.Map;

public class TestDataExcel {
    private static ThreadLocal<Map<String, String>> threadLocalData = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, String value) {
        threadLocalData.get().put(key, value);
    }

    public static String get(String key) {
        return threadLocalData.get().get(key);
    }

    public static void clear() {
        threadLocalData.remove();
    }
    
    public static Map<String, String> getAllData() {
        return new HashMap<>(threadLocalData.get());
    }
}
