package me.retty.recognition.base.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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

    private Config(Map<String, Object> data) {
        this.data = data;
    }

    @SuppressWarnings("unchecked")
    public Config(String configPath) {
        this();
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader(new File(configPath))) {
            Map<String, Object> map = (Map<String, Object>) yaml.load(reader);
            for (String key: map.keySet()) {
                this.set(key, map.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Config set(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public String getStringValue(String key, String defaultValue) {
        System.out.println(this.data);
        if (data.containsKey(key)) {
            return this.data.get(key).toString();
        } else {
            return defaultValue;
        }
    }

    public int getIntValue(String key, int defaultValue) {
        if (data.containsKey(key) && this.data.get(key) instanceof Number) {
            return ((Number)this.data.get(key)).intValue();
        } else {
            return defaultValue;
        }
    }

    public double getDoubleValue(String key, double defaultValue) {
        if (this.data.containsKey(key) && this.data.get(key) instanceof Number) {
            return ((Number)this.data.get(key)).doubleValue();
        } else {
            return defaultValue;
        }
    }

    public Object getObject(String key) {
        if (this.data.containsKey(key)) {
            return this.data.get(key);
        } else {
            return null;
        }
    }
}
