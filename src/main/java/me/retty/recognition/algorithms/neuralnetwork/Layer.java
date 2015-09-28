package me.retty.recognition.algorithms.neuralnetwork;

import me.retty.recognition.base.Dimension;
import me.retty.recognition.base.config.Config;

/**
 * Created by takefumiota on 2015/09/28.
 */
public class Layer extends Dimension<Double>{
    private int type;
    private int activate;
    private Dimension[][] weight;

    private Layer(int width, int height) {
        super(width, height);
        this.weight = new Dimension[width][height];
        this.type = 1;
        this.activate = 0;
    }

    public Layer(int width, int height, String type, String activate) {
        this(width, height);
        switch (type) {
            case "input":
                this.type = 0;
                break;
            case "normal":
                this.type = 1;
                break;
            case "output":
                this.type = 2;
                break;
            default:
                this.type = 1;
        }

        switch (activate) {
            case "sigmoid":
                this.activate = 0;
                break;
            default:
                this.activate = 0;
        }

        this.init();
    }

    public Layer init() {
        System.out.println("Start to init layer (type: " + this.type + ")");
        if (this.type != 0) {
            int width = this.getWidth();
            int height = this.getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    this.weight[j][i] = new Dimension<Double>(width, height).randomize();
                }
            }
        }
        System.out.println("Finish to init layer (type: " + this.type + ")");
        return this;
    }

    public Dimension<Double> forward(Dimension<Double> input) {
        if (this.type == 0) {
            return input;
        } else {
            Dimension<Double> res = new Dimension<>(this.getWidth(), this.getHeight());
            for (int i = 0; i < this.getHeight(); i++) {
                for (int j = 0; j < this.getWidth(); j++) {
                    res.setData(j, i, this.activate(this.calcH(this.weight[j][i], input)));
                }
            }
            return res;
        }
    }

    private double calcH(Dimension weight, Dimension input) {
        int width = weight.getWidth();
        int height = weight.getHeight();
        double res = 0;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                res += weight.getDoubleValue(j, i) * input.getDoubleValue(j, i);
            }
        }
        return res;
    }

    private double activate(double value) {
        if (this.activate == 0) {
            return this.sigmoidFunction(value);
        }
        return 0;
    }

    private double sigmoidFunction(double value) {
        return 1 / (1 + Math.exp(-value));
    }
}
