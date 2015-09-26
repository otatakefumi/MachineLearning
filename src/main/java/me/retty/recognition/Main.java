package me.retty.recognition;

import me.retty.recognition.algorithms.NearestNeighbor;
import me.retty.recognition.base.Config;

public class Main {
    public static String DATA_BASE_DIR = "/Users/takefumiota/work/machine_learning/data/";
    public static String LEARN_DATA_DIR = "learn_gray";
    public static String TEST_DATA_DIR = "test_gray";
//    public static int WIDTH = 300;
//    public static int HEIGHT = 300;

    public static void main(String[] args) {
        NearestNeighbor nn = new NearestNeighbor();

        nn.learn(new Config().set("inputDirPath", DATA_BASE_DIR + LEARN_DATA_DIR));
        nn.verify(new Config().set("inputDirPath", DATA_BASE_DIR + TEST_DATA_DIR));
    }
}
