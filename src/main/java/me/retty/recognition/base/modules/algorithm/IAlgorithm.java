package me.retty.recognition.base.modules.algorithm;

import me.retty.recognition.base.config.Config;
import me.retty.recognition.base.modules.output.IOutput;

/**
 * Created by takefumiota on 2015/09/27.
 */
public interface IAlgorithm<T> {
    /**
     * start learning for recognition
     * @param config configure to indicate input data
     * @return own self(type: ? implements IRecognition)
     */
    IAlgorithm learn(Config config);

    /**
     * Execute recognition
     * @param config configure to indicate target data
     * @return result of recognition
     */
    IOutput<T> recognize(Config config);

    void verify(Config config);
}
