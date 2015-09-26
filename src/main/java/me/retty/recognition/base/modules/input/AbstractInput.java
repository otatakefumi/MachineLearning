package me.retty.recognition.base.modules.input;

/**
 * Created by takefumiota on 2015/09/26.
 */
public abstract class AbstractInput<T> implements IInput<T>{
    protected int index;

    public AbstractInput() {
        this.index = 0;
    }

    @Override
    public void reset() {
        this.index = 0;
    }

    public int getIndex() {
        return this.index;
    }
}
