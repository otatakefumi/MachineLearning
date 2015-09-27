package me.retty.recognition;

import me.retty.recognition.algorithms.ExtendedNearestNeighbor;
import me.retty.recognition.algorithms.NearestNeighbor;
import me.retty.recognition.base.Config;
import me.retty.recognition.base.modules.algorithm.IAlgorithm;

public class Main {
    public static String DATA_BASE_DIR = "base dir name for recognition";
    public static String LEARN_DATA_DIR = "learn data dir";
    public static String TEST_DATA_DIR = "test data dir";

    public static void main(String[] args) {
        //NearestNeighbor nn = new NearestNeighbor(null);
        IAlgorithm algorithm = new ExtendedNearestNeighbor(new Config().set("prototype_length", 250));

        algorithm.learn(new Config().set("inputDirPath", DATA_BASE_DIR + LEARN_DATA_DIR));
        algorithm.verify(new Config().set("inputDirPath", DATA_BASE_DIR + TEST_DATA_DIR));
    }
}
