package me.retty.recognition.algorithms.neuralnetwork;

import me.retty.recognition.base.Dimension;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 * Created by takefumiota on 2015/10/09.
 */
public class SimpleLayer {
    private int inWidth;
    private int outWidth;

    private double[][] weight;
    private double[] bias;
    private Dimension<Double> out;

    private SimpleLayer parent;
    private SimpleLayer child;

    private static double RO = 0.8;

    public SimpleLayer(int inWidth, int outWidth) {
        this.inWidth = inWidth;
        this.outWidth = outWidth;
        this.weight = new double[outWidth][inWidth];
        this.bias = new double[outWidth];
        for (int i=0; i<outWidth; i++) {
            for (int j=0; j<inWidth; j++) {
                this.weight[i][j] = (Math.random() * 2) - 1;
            }
            this.bias[i] = (Math.random() * 2) - 1;
        }
        this.out = new Dimension<>(outWidth, 1);
    }

    public SimpleLayer setParent(SimpleLayer parent) {
        this.parent = parent;
        return this;
    }

    public SimpleLayer setChild(SimpleLayer child) {
        this.child = child;
        return this;
    }

    public int getInWidth() {
        return this.inWidth;
    }

    public int getOutWidth() {
        return this.outWidth;
    }

    public Dimension<Double> forward(Dimension<Double> input) {
        for (int i=0; i<this.outWidth; i++) {
            double val = this.bias[i];
            for (int j=0; j<this.inWidth; j++) {
                val += this.weight[i][j] * input.getDoubleValue(j, 0);
            }
            this.out.setData(i, 0, this.sigmoid(val));
        }
        if (this.child != null) {
            return this.child.forward(this.out);
        }
        return this.out;
    }

    public double sigmoid(double value) {
        return 1 / (1 + Math.exp(-value));
    }

    public void back(Dimension<Double> epsilon) {
        if (this.parent == null) {
            return;
        }

        Dimension<Double> parentEpsilon = new Dimension<>(this.inWidth, 1);
        for (int i=0; i<this.inWidth; i++) {
            double val = 0;
            for (int j=0; j<this.outWidth; j++) {
                val += epsilon.getDoubleValue(j, 0) * this.weight[j][i];
            }
            double g = this.parent.out.getDoubleValue(i, 0);
            parentEpsilon.setData(i, 0, val * g * (1 - g));
        }

        Dimension<Double> parentOut = this.parent.out;
        for (int i=0; i<this.outWidth; i++) {
            for (int j=0; j<this.inWidth; j++) {
                this.weight[i][j] -= RO * epsilon.getDoubleValue(i, 0) * parentOut.getDoubleValue(j, 0);
            }
            this.bias[i] -= RO * epsilon.getDoubleValue(i, 0);
        }

        this.parent.back(parentEpsilon);
    }
}
