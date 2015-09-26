package me.retty.recognition.base.modules.input;

import me.retty.recognition.base.Config;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by takefumiota on 2015/09/26.
 */
public class AbstractInputTest {
    private DimensionInput input;

    @Before
    public void setUp() throws Exception {
        this.input = new DimensionInput(new Config().set("inputDirPath", "src/test/data/dimension"));
    }

    @Test
    public void testReset() throws Exception {

    }

    @Test
    public void testGetIndex() throws Exception {
        this.input.reset();
        this.input.get();
        assertEquals(this.input.getIndex(), 1);
    }
}