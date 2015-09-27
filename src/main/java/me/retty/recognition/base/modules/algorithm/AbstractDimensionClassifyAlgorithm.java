package me.retty.recognition.base.modules.algorithm;

import me.retty.recognition.base.Config;
import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.modules.input.DimensionInput;
import me.retty.recognition.base.modules.input.IInput;

/**
 * Created by takefumiota on 2015/09/27.
 */
public abstract class AbstractDimensionClassifyAlgorithm extends AbstractAlgorithm<Dimension<Double>, String> {
    public AbstractDimensionClassifyAlgorithm(Config config) {
        super(config);
    }

    @Override
    protected IInput<Dimension<Double>> getInput(Config config) {
        return new DimensionInput(config);
    }
}
