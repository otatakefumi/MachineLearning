package me.retty.recognition.base.interfaces;

import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by takefumiota on 2015/09/22.
 * Base infterfaces for pattern recognition
 */
public interface IRecognition<T extends Number> {
    /**
     * start learning for recognition
     * @param learningData list of learning data
     * @return own self(type: ? implements IRecognition)
     */
    public IRecognition startLearning(Map<String,List<String>> learningFiles);

    /**
     * Execute recognition
     * @param pattern pattern data for recognition
     * @return Result
     */
    public Result exec(Dimension<T> pattern);
}
