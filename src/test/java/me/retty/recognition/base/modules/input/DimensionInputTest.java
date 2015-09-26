package me.retty.recognition.base.modules.input;

import me.retty.recognition.base.Config;
import me.retty.recognition.base.Dimension;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by takefumiota on 2015/09/27.
 */
public class DimensionInputTest {
    private DimensionInput input;

    @Before
    public void setUp() throws Exception {
        this.input = new DimensionInput(new Config().set("inputDirPath", "src/test/data/dimension"));
    }

    @Test
    public void testLoadData() throws Exception {
        Dimension<Double> data = this.input.loadData("./src/test/data/dimension/1/100001.csv");
        assertEquals(data.getValue(0, 0).doubleValue(), 208.0d, 0.001d);
        assertEquals(data.getValue(data.getWidth() - 1, data.getHeight() - 1).doubleValue(), 235.0d, 0.001d);
    }
}