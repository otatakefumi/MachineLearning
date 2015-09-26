package me.retty.recognition.base.modules.input;

import me.retty.recognition.base.Config;
import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.DimensionReader;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by takefumiota on 2015/09/26.
 */
public class DimensionInput extends AbstractFileInput<Dimension<Double>> {
    public DimensionInput(Config config) {
        super(config);
    }

    @Override
    protected Dimension<Double> loadData(String dataFilePath) {
        Optional<Dimension<Double>> dimOpt = DimensionReader.readDimension(dataFilePath);
        if (dimOpt.isPresent()) {
            return dimOpt.get();
        } else {
            return null;
        }
    }
}
