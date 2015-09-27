package me.retty.recognition.main;

import me.retty.recognition.algorithms.ExtendedNearestNeighbor;
import me.retty.recognition.base.config.Config;
import me.retty.recognition.base.modules.algorithm.IAlgorithm;

public class Main {

    public static void main(String[] args) {
        String LEARN_DATA_DIR = "learn data dir";
        String TEST_DATA_DIR = "test data dir";

        Arguments options = Arguments.parse(args);
        if (options.hasValue("-c")) {
            Config config = new Config(options.getOption("-c"));
            LEARN_DATA_DIR = config.getStringValue("LearningDataDirPath", LEARN_DATA_DIR);
            LEARN_DATA_DIR = config.getStringValue("TestingDataDirPath", LEARN_DATA_DIR);
        }

        //NearestNeighbor nn = new NearestNeighbor(null);
        IAlgorithm algorithm = new ExtendedNearestNeighbor(new Config().set("prototype_length", 250));

        algorithm.learn(new Config().set("inputDirPath", LEARN_DATA_DIR));
        algorithm.verify(new Config().set("inputDirPath", TEST_DATA_DIR));
    }
}
