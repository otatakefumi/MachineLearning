package me.retty.recognition.algorithms.neuralnetwork;

import javafx.util.Pair;
import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.config.Config;
import me.retty.recognition.base.modules.algorithm.AbstractDimensionClassifyAlgorithm;
import me.retty.recognition.base.modules.input.IInput;
import me.retty.recognition.base.modules.output.IOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by takefumiota on 2015/10/09.
 */
public class NeuralNetwork extends AbstractDimensionClassifyAlgorithm {
    List<SimpleLayer> layers;
    List<String> classes;

    public NeuralNetwork(Config config) {
        super(config);
        this.layers = new ArrayList<>();
        List layersInfo = (List) config.getObject("layers");
        //create layer and set parent layer
        SimpleLayer parent = null;
        for (int i = 0; i < layersInfo.size(); i++) {
            Map info = (Map) layersInfo.get(i);
            int outWidth = Integer.parseInt(info.get("dimension").toString());
            int inWidth = i == 0 ? outWidth : parent.getOutWidth();
            System.out.println(inWidth + ":" + outWidth);
            SimpleLayer layer = new SimpleLayer(inWidth, outWidth).setParent(parent);
            this.layers.add(layer);
            parent = layer;
        }
        // set child layer
        for (int i = this.layers.size() - 2; i >= 0; i--) {
            this.layers.get(i).setChild(this.layers.get(i + 1));
        }

        this.classes = new ArrayList<>();
        ((List<Object>) config.getObject("classes")).forEach(obj -> {
            this.classes.add(obj.toString());
        });
    }

    @Override
    protected void doLearn(IInput<Dimension<Double>> input) {
        int okCount = 0;
        while (true) {
            Pair<String, Dimension<Double>> data = input.get();
            Dimension<Double> result = this.layers.get(0).forward(data.getValue().serialize().multiply(1d / 255));
            int correctIndex = this.classes.indexOf(data.getKey());
            double diff = 0;
            for (int i=0; i<result.getWidth(); i++) {
                double b = i == correctIndex ? 1 : 0;
                double g = result.getDoubleValue(i, 0);
                result.setData(i, 0, (g - b) * g * (1 - g));
                diff += Math.pow(g - b, 2) / 2;
            }
            this.layers.get(this.layers.size() - 1).back(result);
//            System.out.println(result);
            System.out.println("diff: " + diff);
            if (diff < 0.005) {
                okCount++;
            }
            if(!input.hasNext()) {
                if (okCount == input.count()) {
                    break;
                }
                input.reset();
            }
        }
    }

    @Override
    protected IOutput<String> doRecognize(Pair<String, Dimension<Double>> input) {
        return null;
    }
}
