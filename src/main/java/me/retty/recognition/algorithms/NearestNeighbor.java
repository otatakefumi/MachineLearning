package me.retty.recognition.algorithms;

import me.retty.recognition.base.AbstractRecognition;
import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.Result;
import me.retty.recognition.base.interfaces.IRecognition;

import java.util.List;
import java.util.Map;

/**
 * Created by takefumiota on 2015/09/22.
 * Algorithm: Nearest Neighbor (Using prototype)
 * for prototype, use mean value
 */
public class NearestNeighbor<T extends Number> extends AbstractRecognition<T>{
    private Map<String, Dimension<Double>> prototypes;

    @Override
    public IRecognition startLearning(Map<String, List<Dimension<T>>> learningData) {
        for (String key: learningData.keySet()) {
            int width = learningData.get(key).get(0).getWidth();
            int height = learningData.get(key).get(0).getHeight();
            Dimension<Double> prototype = new Dimension<>(width, height);
            for (int i=0; i<height; i++) {
                for (int j=0; j<width; j++) {
                    double value = 0;
                    for (Dimension<T> d: learningData.get(key)) {
                        value += (Double)d.getValue(i, j);
                    }
                    value /= learningData.get(key).size();
                    prototype.setData(j, i, value);
                }
            }
            this.prototypes.put(key, prototype);
        }
        return this;
    }

    @Override
    public Result exec(Dimension<T> pattern) {
        return null;
    }

    public Map<String,Dimension<Double>> getPrototypes() {
        return this.prototypes;
    }
}
