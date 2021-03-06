package me.retty.recognition.base.modules.output;

/**
 * Created by takefumiota on 2015/09/27.
 */
public abstract class AbstractOutput<T> implements IOutput<T> {
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

    @Override
    public T getResult() {
        return this.result;
    }

    @Override
    public T getAnswer() {
        return this.answer;
    }
}
