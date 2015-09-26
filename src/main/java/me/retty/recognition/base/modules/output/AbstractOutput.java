package me.retty.recognition.base.modules.output;

/**
 * Created by takefumiota on 2015/09/27.
 */
public abstract class AbstractOutput<T> implements IOutput {
    protected T result;
    protected T answer;

    public AbstractOutput(T result, T answer) {
        this.result = result;
        this.answer = answer;
    }

    @Override
    public boolean isCorrect() {
        return result.equals(answer);
    }
}
