package me.retty.recognition.base.modules.input;

import javafx.util.Pair;

/**
 * Created by takefumiota on 2015/09/26.
 */
public interface IInput<T> {
    /**
     * get next data
     * @return input data
     */
    Pair<String, T> get();

    /**
     * judge whether having next data or not
     * @return true / false
     */
    boolean hasNext();

    /**
     * reset index of data
     */
    void reset();
}
