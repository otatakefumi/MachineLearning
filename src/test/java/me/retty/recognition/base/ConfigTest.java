package me.retty.recognition.base;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by takefumiota on 2015/09/26.
 */
public class ConfigTest {
    private Config config;

    @Before
    public void setUp() throws Exception {
        this.config = new Config().set("test", "testValue")
                .set("int_test", 1)
                .set("double_test", 1.2d);
    }

    @Test
    public void testSet() throws Exception {
        this.config.set("12345", "intellij");
        assertEquals(this.config.getStringValue("12345", "12345"), "intellij");
    }

    @Test
    public void testGetStringValue() throws Exception {
        assertEquals(this.config.getStringValue("test", "test"), "testValue");
    }

    @Test
    public void testGetIntValue() throws Exception {
        assertEquals(this.config.getIntValue("int_test", 2), 1);
        assertEquals(this.config.getIntValue("int_test_", 3), 3);
        assertEquals(this.config.getIntValue("test", 2), 2);
        assertEquals(this.config.getIntValue("double_test", 4), 1);
    }
}