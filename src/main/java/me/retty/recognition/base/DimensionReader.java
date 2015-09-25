package me.retty.recognition.base;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by takefumiota on 2015/09/24.
 */
public class DimensionReader {
    public static Optional<Dimension<Double>> readDimension(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return Optional.ofNullable(null);
        }
        try {
            System.out.println("read file: " + filePath);
            List<String> lines = Files.readAllLines(file.toPath());
            int width = lines.get(0).split(",").length;
            int height = lines.size();
            Dimension<Double> res = new Dimension<>(width, height);
            for (int i = 0; i < height; i++) {
                String[] data = lines.get(i).split(",");
                for (int j = 0; j < width; j++) {
                    res.setData(j, i, Double.valueOf(data[j]));
                }
            }
            System.out.println("finish to read file: " + filePath);
            return Optional.of(res);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.ofNullable(null);
        }
    }

    public static Optional<Map<String, List<Dimension<Double>>>> readDimensions(String inputDirPath) {
        File dir = new File(inputDirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return Optional.ofNullable(null);
        }
        File[] patterns = dir.listFiles(File::isDirectory);
        Map<String, List<Dimension<Double>>> res = new HashMap<>();
        System.out.println("target patterns: " + String.join(",", Arrays.stream(patterns).map(File::getName).toArray(String[]::new)));
        Arrays.stream(patterns).forEach(pattern -> {
            System.out.println("pattern: " + pattern.getName());
            List<Dimension<Double>> dimList = new ArrayList<>();
            File[] dimensions = pattern.listFiles((File f) -> f.isFile() && !f.getName().startsWith("."));
            Arrays.stream(dimensions).forEach(dimension -> {
                DimensionReader.readDimension(dimension.getPath()).ifPresent(dimList::add);
            });
            if (dimList.size() > 0) {
                res.put(pattern.getName(), dimList);
            }
        });
        return Optional.of(res);
    }

    public static void main(String[] args) {
        String IMAGE_BASE_DIR = "/Users/takefumiota/work/machine_learning/data/";
        String LEARN_DATA_DIR = "learn_gray";
        String TEST_DATA_DIR = "test_gray";

    }
}