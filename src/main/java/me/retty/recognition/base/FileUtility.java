package me.retty.recognition.base;

import java.io.File;
import java.util.*;

/**
 * Created by takefumiota on 2015/09/24.
 */
public class FileUtility {
    public static Optional<Map<String, List<String>>> getPatternFilePathMap(String inputDirPath) {
        File dir = new File(inputDirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return Optional.ofNullable(null);
        }
        File[] patterns = dir.listFiles(File::isDirectory);
        Map<String, List<String>> res = new HashMap<>();
        System.out.println("target patterns: " + String.join(",", Arrays.stream(patterns).map(File::getName).toArray(String[]::new)));
        Arrays.stream(patterns).forEach(pattern -> {
            System.out.println("pattern: " + pattern.getName());
            List<String> fileList = new ArrayList<>();
            File[] files = pattern.listFiles((File f) -> f.isFile() && !f.getName().startsWith("."));
            Arrays.stream(files).forEach(file -> fileList.add(file.getPath()));
            if (fileList.size() > 0) {
                res.put(pattern.getName(), fileList);
            }
        });
        return Optional.of(res);
    }

    public static void main(String[] args) {
        String DATA_BASE_DIR = "/Users/takefumiota/work/machine_learning/data/";
        String LEARN_DATA_DIR = "learn_gray";
        String TEST_DATA_DIR = "test_gray";
        System.out.println(FileUtility.getPatternFilePathMap(DATA_BASE_DIR + LEARN_DATA_DIR).get().get("1").size());
        System.out.println(FileUtility.getPatternFilePathMap(DATA_BASE_DIR + TEST_DATA_DIR).get().get("1").size());
    }
}
