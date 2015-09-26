package me.retty.recognition.base.modules.output;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by takefumiota on 2015/09/27.
 */
public class SimpleOutputTest {
    private SimpleOutput output;
    private SimpleOutput outputError;

    @Before
    public void setUp() throws Exception {
        this.output = new SimpleOutput("1", "1");
        this.outputError = new SimpleOutput("1", "2");
    }

    @Test
    public void testIsCorrect() throws Exception {
        assertEquals(this.output.isCorrect(), true);
        assertEquals(this.outputError.isCorrect(), false);
    }
}