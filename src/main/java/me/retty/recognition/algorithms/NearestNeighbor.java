package me.retty.recognition.algorithms;

import me.retty.recognition.base.*;
import me.retty.recognition.base.interfaces.IRecognition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by takefumiota on 2015/09/22.
 * Algorithm: Nearest Neighbor (Using prototype)
 * for prototype, use mean value
 */
public class NearestNeighbor extends AbstractRecognition<Double>{
    private Map<String, Dimension<Double>> prototypes;

    public NearestNeighbor() {
        this.prototypes = new HashMap<>();
    }

    @Override
    public IRecognition startLearning(Map<String, List<String>> learningFiles) {
        System.out.println("Start to make prototype");
        for (String key: learningFiles.keySet()) {
            Optional<Dimension<Double>> dimOpt = DimensionReader.readDimension(learningFiles.get(key).get(0));
            if (!dimOpt.isPresent()) {
                System.err.println("fail to read");
                continue;
            }
            int width = dimOpt.get().getWidth();
            int height = dimOpt.get().getHeight();
            int[] count = {0};
            Dimension<Double> prototype = new Dimension<>(width, height);
            for (String path: learningFiles.get(key)) {
                DimensionReader.readDimension(path).ifPresent(dim -> {
                    System.out.println(path);
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            prototype.setData(j, i, prototype.getValue(j, i) + dim.getValue(j, i));
                        }
                    }
                    count[0]++;
                });
            }
            for (int i=0; i<height; i++) {
                for (int j=0; j<width; j++) {
                    prototype.setData(j, i, prototype.getValue(j, i) / count[0]);
                }
            }
            this.prototypes.put(key, prototype);
        }
        System.out.println("Finish to make prototype");
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Result exec(Dimension<Double> pattern) {
        double min = Double.MAX_VALUE;
        String minKey = "";
        for (String key: this.prototypes.keySet()) {
            double distance = this.calcDistance(pattern, this.prototypes.get(key));
            if (distance < min) {
                min = distance;
                minKey = key;
            }
        }
        return new StringResult(minKey);
    }

    public Map<String,Dimension<Double>> getPrototypes() {
        return this.prototypes;
    }

    private double calcDistance(Dimension<Double> a, Dimension<Double> b) {
        if (a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()) {
            throw new RuntimeException("Dimensions are not same size");
        }
        double res = 0;
        for (int i=0; i<a.getWidth(); i++) {
            for (int j=0; j<a.getHeight(); j++) {
                res += Math.pow(a.getValue(i, j) - b.getValue(i, j), 2);
            }
        }
        return Math.sqrt(res);
    }
}
