package me.retty.recognition.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.retty.recognition.base.Dimension;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by takefumiota on 2015/09/22.
 */
public class NearestNeighborTest {
    private Map<String, List<Dimension<Double>>> learningData;
    private Map<String, List<Dimension<Double>>> testData;


    /**
     * making test data
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.learningData = new HashMap<>();
        Dimension<Double> aDim1 = new Dimension<>(5,1);
        aDim1.setData(0,0,5d);
        aDim1.setData(1,0,4d);
        aDim1.setData(2,0,3d);
        aDim1.setData(3,0,2d);
        aDim1.setData(4,0,1d);
        Dimension<Double> aDim2 = new Dimension<>(5,1);
        aDim2.setData(0,0,4d);
        aDim2.setData(1,0,3d);
        aDim2.setData(2,0,2d);
        aDim2.setData(3,0,1d);
        aDim2.setData(4,0,0d);
        Dimension<Double> aDim3 = new Dimension<>(5,1);
        aDim3.setData(0,0,3d);
        aDim3.setData(1,0,2d);
        aDim3.setData(2,0,1d);
        aDim3.setData(3,0,0d);
        aDim3.setData(4,0,-1d);
        Dimension<Double> bDim1 = new Dimension<>(5,1);
        bDim1.setData(0,0,6d);
        bDim1.setData(1,0,5d);
        bDim1.setData(2,0,4d);
        bDim1.setData(3,0,3d);
        bDim1.setData(4,0,2d);
        Dimension<Double> bDim2 = new Dimension<>(5,1);
        bDim2.setData(0,0,7d);
        bDim2.setData(1,0,6d);
        bDim2.setData(2,0,5d);
        bDim2.setData(3,0,4d);
        bDim2.setData(4,0,3d);
        Dimension<Double> bDim3 = new Dimension<>(5,1);
        bDim3.setData(0,0,8d);
        bDim3.setData(1,0,7d);
        bDim3.setData(2,0,6d);
        bDim3.setData(3,0,5d);
        bDim3.setData(4,0,4d);
        Dimension<Double> cDim1 = new Dimension<>(5,1);
        cDim1.setData(0,0,-5d);
        cDim1.setData(1,0,-5d);
        cDim1.setData(2,0,-5d);
        cDim1.setData(3,0,-5d);
        cDim1.setData(4,0,-5d);
        Dimension<Double> cDim2 = new Dimension<>(5,1);
        cDim2.setData(0,0,-5d);
        cDim2.setData(1,0,-5d);
        cDim2.setData(2,0,-5d);
        cDim2.setData(3,0,-5d);
        cDim2.setData(4,0,-5d);
        Dimension<Double> cDim3 = new Dimension<>(5,1);
        cDim3.setData(0,0,-5d);
        cDim3.setData(1,0,-5d);
        cDim3.setData(2,0,-5d);
        cDim3.setData(3,0,-5d);
        cDim3.setData(4,0,-5d);
        List<Dimension<Double>> list = new ArrayList<>();
        list.add(aDim1);
        list.add(aDim2);
        list.add(aDim3);
        this.learningData.put("a", list);
        list = new ArrayList<>();
        list.add(bDim1);
        list.add(bDim2);
        list.add(bDim3);
        this.learningData.put("b", list);
        list = new ArrayList<>();
        list.add(cDim1);
        list.add(cDim2);
        list.add(cDim3);
        this.learningData.put("c", list);

        this.testData = new HashMap<>();
        list = new ArrayList<>();
        list.add(aDim1);
        this.testData.put("a", list);
        list = new ArrayList<>();
        list.add(bDim1);
        list.add(bDim3);
        this.testData.put("b", list);
        list = new ArrayList<>();
        list.add(cDim1);
        this.testData.put("c", list);
    }


    @Test
    public void testStartLearning() throws Exception {
        NearestNeighbor nn = new NearestNeighbor();
    }

    @Test
    public void testExec() throws Exception {
        NearestNeighbor nn = new NearestNeighbor();
    }

    @Test
    public void testGetPrototypes() throws Exception {
        NearestNeighbor nn = new NearestNeighbor();
    }
}