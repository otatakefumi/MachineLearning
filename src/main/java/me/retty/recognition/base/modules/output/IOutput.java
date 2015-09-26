package me.retty.recognition.base.modules.output;

/**
 * Created by takefumiota on 2015/09/27.
 */
public interface IOutput<T> {
    /**
     * judge whether the result is correct
     * @return true / false
     */
    boolean isCorrect();
    T getResult();
    T getAnswer();
}
