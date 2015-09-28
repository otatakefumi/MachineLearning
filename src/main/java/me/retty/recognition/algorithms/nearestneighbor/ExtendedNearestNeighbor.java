package me.retty.recognition.algorithms.nearestneighbor;

import javafx.util.Pair;
import me.retty.recognition.base.config.Config;
import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.modules.algorithm.AbstractDimensionClassifyAlgorithm;
import me.retty.recognition.base.modules.input.IInput;
import me.retty.recognition.base.modules.output.IOutput;
import me.retty.recognition.base.modules.output.SimpleOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by takefumiota on 2015/09/27.
 */
public class ExtendedNearestNeighbor extends AbstractDimensionClassifyAlgorithm {
    private Map<String, List<Dimension<Double>>> prototypes;
    private int protoTypeSize;

    public ExtendedNearestNeighbor(Config config) {
        super(config);
        this.prototypes = new HashMap<>();
        this.protoTypeSize = config.getIntValue("prototype_length", 1);
    }

    @Override
    protected void doLearn(IInput<Dimension<Double>> input) {
        System.out.println("Start to make prototype");
        Map<String, List<Integer>> classCountMap = new HashMap<>();
        while (input.hasNext()) {
            Pair<String, Dimension<Double>> data = input.get();
            String key = data.getKey();
            Dimension<Double> dim = data.getValue();
            int width = dim.getWidth();
            int height = dim.getHeight();
            if (!this.prototypes.containsKey(key)) {
                this.prototypes.put(key, new ArrayList<>());
                classCountMap.put(key, new ArrayList<>());
            }
            if (this.prototypes.get(key).size() < this.protoTypeSize) {
                Dimension<Double> prototype = new Dimension<>(width, height);
                for (int i=0; i<height; i++) {
                    for (int j=0; j<width; j++) {
                        prototype.setData(j, i, prototype.getDoubleValue(j, i) + dim.getDoubleValue(j, i));
                    }
                }
                this.prototypes.get(key).add(prototype);
                classCountMap.get(key).add(1);
                continue;
            }

            // select most nearest dimension
            int index = 0;
            double minDistance = Double.MAX_VALUE;
            for (int i=0; i<this.prototypes.get(key).size(); i++) {
                Dimension<Double> prototype = this.prototypes.get(key).get(i);
                if (prototype.getWidth() != dim.getWidth() || prototype.getHeight() != dim.getHeight()) {
                    throw new RuntimeException("Dimensions are not same size");
                }
                double distance = 0;
                for (int j=0; j<prototype.getHeight(); j++) {
                    for (int k=0; k<prototype.getWidth(); k++) {
                        distance += Math.pow((prototype.getDoubleValue(k, j) / classCountMap.get(key).get(i)) - dim.getDoubleValue(k, j), 2);
                    }
                }
                System.out.println(distance + " :::: " + classCountMap.get(key).get(i));
                if (distance < minDistance) {
                    index = i;
                    minDistance = distance;
                }
            }
            System.out.println("index :::::: " + index + " distance : " + minDistance);
            classCountMap.get(key).set(index, classCountMap.get(key).get(index) + 1);
            Dimension<Double> prototype = this.prototypes.get(key).get(index);
            for (int i=0; i<height; i++) {
                for (int j=0; j<width; j++) {
                    prototype.setData(j, i, prototype.getDoubleValue(j, i) + dim.getDoubleValue(j, i));
                }
            }
        }
        System.out.println("Standardize each prototypes");
        for (String key: this.prototypes.keySet()) {
            for (int i=0; i<this.prototypes.get(key).size(); i++) {
                Dimension<Double> prototype = this.prototypes.get(key).get(i);
                int width = prototype.getWidth();
                int height = prototype.getHeight();
                int count = classCountMap.get(key).get(i);
                for (int j = 0; j < height; j++) {
                    for (int k = 0; k < width; k++) {
                        prototype.setData(k, j, prototype.getDoubleValue(k, j) / count);
                    }
                }
            }
        }
        System.out.println("finish to make prototype");
    }

    @Override
    protected IOutput<String> doRecognize(Pair<String, Dimension<Double>> input) {
        double[] min = {Double.MAX_VALUE};
        String[] minKey = {""};
        for (String key: this.prototypes.keySet()) {
            this.prototypes.get(key).forEach(prototype -> {
                double distance = this.calcDistance(input.getValue(), prototype);
                if (distance < min[0]) {
                    min[0] = distance;
                    minKey[0] = key;
                }
            });
        }
        return new SimpleOutput(minKey[0], input.getKey());
    }

    private double calcDistance(Dimension<Double> a, Dimension<Double> b) {
        if (a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()) {
            throw new RuntimeException("Dimensions are not same size");
        }
        double res = 0;
        for (int i=0; i<a.getWidth(); i++) {
            for (int j=0; j<a.getHeight(); j++) {
                res += Math.pow(a.getDoubleValue(i, j) - b.getDoubleValue(i, j), 2);
            }
        }
        return Math.sqrt(res);
    }
}
