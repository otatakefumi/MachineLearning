package me.retty.recognition.image;

import me.retty.recognition.algorithms.NearestNeighbor;
import me.retty.recognition.base.DimensionReader;
import me.retty.recognition.base.FileUtility;
import me.retty.recognition.base.Result;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static String DATA_BASE_DIR = "/Users/takefumiota/work/machine_learning/data/";
    public static String LEARN_DATA_DIR = "learn_gray";
    public static String TEST_DATA_DIR = "test_gray";
    public static int WIDTH = 300;
    public static int HEIGHT = 300;

    public static void main(String[] args) {
        Optional<Map<String, List<String>>> learningFilesOpt = FileUtility.getPatternFilePathMap(DATA_BASE_DIR + LEARN_DATA_DIR);
        NearestNeighbor nn = new NearestNeighbor();

        if (learningFilesOpt.isPresent()) {
            System.out.println("finish to get studying data files list");
            nn.startLearning(learningFilesOpt.get());
        } else {
            System.err.println("error to get learning data files list");
            System.exit(1);
        }

        Optional<Map<String, List<String>>> testingFilesOpt = FileUtility.getPatternFilePathMap(DATA_BASE_DIR + TEST_DATA_DIR);
        if (testingFilesOpt.isPresent()) {
            System.out.println("finish to get testing data files list");
            Map<String, List<String>> testingFilesMap = testingFilesOpt.get();

            System.out.println("Start recognition");
            List<Result> results = new ArrayList<>();
            for (String key: testingFilesMap.keySet()) {
                for (String path: testingFilesMap.get(key)) {
                    DimensionReader.readDimension(path).ifPresent(dim -> {
                        results.add(nn.exec(dim).setAnswer(key));
                    });
                }
            }
            System.out.println("Finish recognition");

            int correctCount = results.stream()
                    .filter(result -> result.isCorrect())
                    .collect(Collectors.toList()).size();
            System.out.println(correctCount);
            System.out.println((((double)correctCount) / results.size() * 100) + "%");
        } else {
            System.err.println("error to get testing data files list");
            System.exit(1);
        }
    }
}
