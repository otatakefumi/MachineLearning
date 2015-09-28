package me.retty.recognition.algorithms.neuralnetwork;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javafx.util.Pair;
import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.config.Config;
import me.retty.recognition.base.modules.algorithm.AbstractDimensionClassifyAlgorithm;
import me.retty.recognition.base.modules.input.IInput;
import me.retty.recognition.base.modules.output.IOutput;

/**
 * Created by takefumiota on 2015/09/28.
 */
public class NeuralNetwork extends AbstractDimensionClassifyAlgorithm {
    List<Layer> layers;
    double threshold;

    public NeuralNetwork(Config config) {
        super(config);
        this.layers = new ArrayList<>();
        System.out.println("Start to prepare layers");
        for (Object layerSetting: (List)config.getObject("layers")) {
            Map layerSettingMap = (Map)layerSetting;
            this.layers.add(
                    new Layer(
                            Integer.parseInt(layerSettingMap.get("dimension").toString()),
                            1,
                            layerSettingMap.get("type").toString(),
                            layerSettingMap.get("activating").toString()
                    )
            );
        }
        System.out.println("Finish to prepare layers");
        this.threshold = config.getDoubleValue("threshold", 0.04);
    }

    @Override
    protected void doLearn(IInput<Dimension<Double>> input) {
        //input.reset();

        // forward propagation
        while (input.hasNext()) {
            Pair<String, Dimension<Double>> data = input.get();
            Dimension<Double> dim = data.getValue();
            for (Layer layer: this.layers) {
                dim = layer.forward(dim);
            }
            System.out.println(dim);
        }
    }

    @Override
    protected IOutput<String> doRecognize(Pair<String, Dimension<Double>> input) {
        return null;
    }
}
