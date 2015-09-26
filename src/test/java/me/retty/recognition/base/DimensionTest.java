package me.retty.recognition.base;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by takefumiota on 2015/09/25.
 */
public class DimensionTest {
    private Dimension<Integer> dim;

    @Before
    public void setUp() throws Exception {
        this.dim = new Dimension<>(3, 3);
        this.dim.setData(0, 0, 1);
        this.dim.setData(0, 1, 2);
        this.dim.setData(0, 2, 3);
        this.dim.setData(1, 0, 4);
        this.dim.setData(1, 1, 5);
        this.dim.setData(1, 2, 6);
        this.dim.setData(2, 0, 7);
        this.dim.setData(2, 1, 8);
        this.dim.setData(2, 2, 9);
    }

    @Test
    public void testGetValue() throws Exception {
        assertEquals(this.dim.getValue(0, 0).intValue(), 1);
        assertEquals(this.dim.getValue(2, 2).intValue(), 9);
    }

    @Test
    public void testSetData() throws Exception {
        this.dim.setData(1, 1, 25);
        assertEquals(this.dim.getValue(1, 1).intValue(), 25);
    }

    @Test
    public void testGetWidth() throws Exception {
        assertEquals(this.dim.getWidth(), 3);
    }

    @Test
    public void testGetHeight() throws Exception {
        assertEquals(this.dim.getHeight(), 3);
    }
}