package me.retty.recognition.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by takefumiota on 2015/09/26.
 */
public class Config {
    private Map<String, Object> data;

    public Config() {
        this.data = new HashMap<>();
    }

    public Config set(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public String getStringValue(String key, String defaultValue) {
        if (data.containsKey(key)) {
            return this.data.get(key).toString();
        } else {
            return defaultValue;
        }
    }
}
