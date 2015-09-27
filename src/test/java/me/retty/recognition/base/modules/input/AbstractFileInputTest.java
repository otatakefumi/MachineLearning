package me.retty.recognition.base.modules.input;

import javafx.util.Pair;
import me.retty.recognition.base.config.Config;
import me.retty.recognition.base.Dimension;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by takefumiota on 2015/09/27.
 */
public class AbstractFileInputTest {
    private DimensionInput input;

    @Before
    public void setUp() throws Exception {
        this.input = new DimensionInput(new Config().set("inputDirPath", "src/test/data/dimension"));
    }

    @Test
    public void testHasNext() throws Exception {
        this.input.reset();
        assertEquals(this.input.hasNext(), true);
        this.input.get();
        this.input.get();
        assertEquals(this.input.hasNext(), false);
    }

    @Test
    public void testGet() throws Exception {
        this.input.reset();
        Pair<String, Dimension<Double>> data = this.input.get();
        assertEquals(data.getKey(), "1");
        assertEquals(data.getValue().getValue(0, 0).doubleValue(), 208.0d, 0.001d);
        assertEquals(data.getValue().getValue(data.getValue().getWidth() - 1, data.getValue().getHeight() - 1).doubleValue(), 235.0d, 0.001d);
    }
}