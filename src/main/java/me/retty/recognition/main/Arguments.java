package me.retty.recognition.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by takefumiota on 2015/09/27.
 */
public class Arguments {
    Map<String, String> argMap;

    private Arguments(String... args) {
        this.argMap = new HashMap<>();
    }

    public boolean hasOption(String key) {
        return this.argMap.containsKey(key);
    }

    public boolean hasValue(String key) {
        return this.argMap.get(key) != null;
    }

    public String getOption(String key) {
        return this.argMap.get(key);
    }

    public Set<String> getKeySet() {
        return this.argMap.keySet();
    }

    public static Arguments parse(String... args) {
        Arguments res = new Arguments();
        for (int i=0; i<args.length; i++) {
            String value = args[i];
            if (!value.startsWith("-")) {
                continue;
            }

            if (i < args.length - 1) {
                String nextValue = args[i + 1];
                if (nextValue.startsWith("-")) {
                    res.argMap.put(value, null);
                } else {
                    res.argMap.put(value, nextValue);
                    i++;
                }
            } else {
                res.argMap.put(value, null);
            }
        }
        return res;
    }
}
