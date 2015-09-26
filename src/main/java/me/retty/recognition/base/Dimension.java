package me.retty.recognition.base;

/**
 * Created by takefumiota on 2015/09/22.
 * Indicate dimension vector that used for pattern recognition(input, weight, and so on)
 */
public class Dimension<T extends Number> {
    /**
     * width and height of this dimension
     */
    protected int width, height;
    /**
     * actual data for this dimension
     */
    protected Number[][] data;

    /**
     * constructor
     * @param width width of this dimension
     * @param height height of this dimension
     */
    public Dimension(int width, int height) {
        if (width <= 0) {
            width = 1;
        }
        if (height <= 0) {
            height = 1;
        }
        this.width = width;
        this.height = height;
        this.data = new Number[height][width];
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                this.data[i][j] = 0;
            }
        }
    }

    /**
     * get value of this dimension
     * @param x x-axis of value
     * @param y y-axis of value
     * @return value of this dimension(type: T)
     */
    public Number getValue(int x, int y) {
        return this.data[y][x];
    }

    public double getDoubleValue(int x, int y) {
        return this.data[y][x].doubleValue();
    }

    /**
     * set value on this dimension
     * @param x x-axis you wanna set value
     * @param y y-axis you wanna set value
     * @param value value you wanna set
     * @return this dimension after set value(type: Dimension&lt;T&gt;)
     */
    public Dimension<T> setData(int x, int y, T value) {
        data[y][x] = value;
        return this;
    }

    /**
     * get the width of this dimension
     * @return width of this dimension(type: int)
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * get the height of this dimension
     * @return width of this dimension(type: int)
     */
    public int getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        String res = "Dimension (" + this.width + " x " + this.height + ") [\n";
        for (int i=0; i<height; i++) {
            res += "   ";
            for (int j=0; j<width; j++) {
                res += this.data[i][j] + (j < width-1 ? ", " : "");
            }
            res += "\n" + (i < height-1 ? "" : "]");
        }
        return res;
    }
}
