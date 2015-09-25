package me.retty.recognition.base;

/**
 * Created by takefumiota on 2015/09/22.
 * Indicate result of pattern recognition
 */
abstract public class Result<T> {
    private Object value;
    private Object answer;

    public Result(T value) {
        this.value = value;
    }

    public T getValue() {
        return value == null ? null : (T)value;
    }

    public T getAnswer() {
        return answer == null ? null : (T)value;
    }

    public Result<T> setAnswer(T answer) {
        this.answer = answer;
        return this;
    }

    public boolean isCorrect() {
        if (this.value != null && this.answer != null && this.value.equals(this.answer)) {
            return true;
        }
        return false;
    }
}
