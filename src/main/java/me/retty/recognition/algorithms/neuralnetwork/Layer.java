package me.retty.recognition.algorithms.neuralnetwork;

import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.config.Config;

/**
 * Created by takefumiota on 2015/09/28.
 */
public class Layer extends Dimension<Double>{
    Dimension[][] weight;
    private int outWidth, outHeight;

    public Layer(int inWidth, int inHeight, int outWidth, int outHeight) {
        super(1, 1);
        this.outWidth = outWidth;
        this.outHeight = outHeight;
        this.weight = new Dimension[outHeight][outWidth];
        for (int i=0; i<outHeight; i++) {
            for (int j=0; j<outWidth; j++) {
                this.weight[i][j] = new Dimension<>(inWidth, inHeight).randomize();
            }
        }
    }

    public Dimension<Double> forward(Dimension<Double> input) {
        Dimension<Double> res = new Dimension<>(this.outWidth, this.outHeight);
        for (int i=0; i<this.outHeight; i++) {
            for (int j=0; j<this.outWidth; j++) {
                res.setData(j, i, sigmoid(this.calcH(input, this.weight[i][j])));
            }
        }
        return res;
    }

    private double calcH(Dimension<Double> input, Dimension weight) {
        double res = 0;
        for (int i=0; i<input.getHeight(); i++) {
            for (int j=0; j<input.getWidth(); j++) {
                res += weight.getDoubleValue(j, i) * input.getDoubleValue(j, i);
            }
        }
        return res;
    }

    private double sigmoid(double value) {
        return 1 / (1 + Math.exp(-value));
    }
}
