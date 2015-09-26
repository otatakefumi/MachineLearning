package me.retty.recognition.base.modules.algorithm;

import javafx.util.Pair;
import me.retty.recognition.base.Config;
import me.retty.recognition.base.modules.input.IInput;
import me.retty.recognition.base.modules.output.IOutput;

/**
 * Created by takefumiota on 2015/09/27.
 */
public abstract class AbstractAlgorithm<T> implements IAlgorithm {
    @Override
    public IAlgorithm learn(Config config) {
        IInput<T> input = this.getInput(config);
        this.doLearn(input);
        return this;
    }

    @Override
    public IOutput recognize(Config config) {
        IInput<T> input = this.getInput(config);
        Pair<String, T> data = input.get();
        return this.doRecognize(data);
    }

    /**
     * get input
     * @param config configure to indicate input data
     * @return instance of input class
     */
    protected abstract IInput<T> getInput(Config config);

    /**
     * execute learning process
     * @param input instance of input class
     */
    protected abstract void doLearn(IInput<T> input);

    /**
     * execute recognize
     * @param input instance of input class
     * @return recognition result
     */
    protected abstract IOutput doRecognize(Pair<String, T> input);
}
