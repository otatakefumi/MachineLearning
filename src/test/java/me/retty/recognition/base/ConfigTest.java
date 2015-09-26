package me.retty.recognition.base;

import junit.framework.TestCase;
import org.junit.Before;

/**
 * Created by takefumiota on 2015/09/26.
 */
public class ConfigTest extends TestCase {
    private Config config;

    @Before
    public void setUp() throws Exception {
        this.config = new Config().set("test", "testValue");
    }

    public void testSet() throws Exception {
        this.config.set("12345", "intellij");
        assertEquals(this.config.getStringValue("12345", "12345"), "intellij");
    }

    public void testGetStringValue() throws Exception {
        assertEquals(this.config.getStringValue("test", "test"), "testValue");
    }
}