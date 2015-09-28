package me.retty.recognition.algorithms.nearestneighbor;

import javafx.util.Pair;
import me.retty.recognition.base.config.Config;
import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.modules.algorithm.AbstractDimensionClassifyAlgorithm;
import me.retty.recognition.base.modules.input.IInput;
import me.retty.recognition.base.modules.output.IOutput;
import me.retty.recognition.base.modules.output.SimpleOutput;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by takefumiota on 2015/09/27.
 */
public class NearestNeighbor extends AbstractDimensionClassifyAlgorithm {
    private Map<String, Dimension<Double>> prototypes;

    public NearestNeighbor(Config config) {
        super(config);
        this.prototypes = new HashMap<>();
    }

    @Override
    protected void doLearn(IInput<Dimension<Double>> input) {
        System.out.println("Start to make prototype");
        Map<String, Integer> classCountMap = new HashMap<>();
        while (input.hasNext()) {
            Pair<String, Dimension<Double>> data = input.get();
            String key = data.getKey();
            Dimension<Double> dim = data.getValue();
            int width = dim.getWidth();
            int height = dim.getHeight();
            if (!this.prototypes.containsKey(key)) {
                this.prototypes.put(key, new Dimension<>(width, height));
                classCountMap.put(key, 0);
            }
            classCountMap.put(key, classCountMap.get(key) + 1);
            Dimension<Double> prototype = this.prototypes.get(key);
            for (int i=0; i<height; i++) {
                for (int j=0; j<width; j++) {
                    prototype.setData(j, i, prototype.getDoubleValue(j, i) + dim.getDoubleValue(j, i));
                }
            }
        }
        System.out.println("Standardize each prototypes");
        for (String key: this.prototypes.keySet()) {
            Dimension<Double> prototype = this.prototypes.get(key);
            int width = prototype.getWidth();
            int height = prototype.getHeight();
            int count = classCountMap.get(key);
            for (int i=0; i<height; i++) {
                for (int j=0; j<width; j++) {
                    prototype.setData(j, i, prototype.getDoubleValue(j, i) / count);
                }
            }
        }
        System.out.println("finish to make prototype");
    }

    @Override
    protected IOutput<String> doRecognize(Pair<String, Dimension<Double>> input) {
        double min = Double.MAX_VALUE;
        String minKey = "";
        for (String key: this.prototypes.keySet()) {
            double distance = this.calcDistance(input.getValue(), this.prototypes.get(key));
            if (distance < min) {
                min = distance;
                minKey = key;
            }
        }
        return new SimpleOutput(minKey, input.getKey());
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
