package me.retty.recognition.base.interfaces;

import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.Result;

import java.util.*;

/**
 * Created by takefumiota on 2015/09/22.
 * Base infterfaces for pattern recognition
 */
public interface IRecognition {
    /**
     * set learning data
     * @param learningData list of learning data
     * @return own self(type: ? implements IRecognition)
     */
    public IRecognition setLearnintData(List<Dimension<?>> learningData);

    /**
     * set testing data
     * @param testingData list of testing data
     * @return own self(type: ? implements IRecognition)
     */
    public IRecognition setTestingData(List<Dimension<?>> testingData);

    /**
     * Execute recognition
     * @return Result
     */
    public Result exec();
}
