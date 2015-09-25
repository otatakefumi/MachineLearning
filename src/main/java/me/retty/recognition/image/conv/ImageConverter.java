package me.retty.recognition.image.conv;

import me.retty.recognition.base.Dimension;
import me.retty.recognition.image.util.ImageReader;
import me.retty.recognition.image.util.ImageUtility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by takefumiota on 2015/09/24.
 */
public class ImageConverter {
    String inputPath;
    String outputPath;
    boolean grayScale;
    int width;
    int height;

    public ImageConverter(String inputPath, String outputPath, boolean grayScale, int width, int height) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.grayScale = grayScale;
        this.width = width;
        this.height = height;
    }

    public boolean exec() {
        System.out.println("Start convert images(target: " + this.inputPath + ")");
        File file = new File(inputPath);
        if (!file.exists() || !file.isDirectory()) {
            System.out.println(this.inputPath + " is not found.");
            return false;
        } else {
            File[] patterns = file.listFiles(File::isDirectory);
            System.out.println("convert patterns: " + String.join(",", Arrays.stream(patterns).map(File::getName).toArray(String[]::new)));
            Arrays.stream(patterns).forEach(this::convertEachPattern);
        }
        return false;
    }

    private boolean convertEachPattern(File patternDir) {
        if (!patternDir.exists() || !patternDir.isDirectory()) {
            System.err.println(patternDir.getPath() + " is not found.");
            return false;
        } else {
            System.out.println("pattern: " + patternDir.getName());
            File[] images = patternDir.listFiles((File f) -> f.isFile() && !f.getName().startsWith("."));
            System.out.println("convert image files: " + images.length);
            Arrays.stream(images).forEach(imageFile -> {
                this.convertFile(imageFile, this.outputPath + File.separator + patternDir.getName());
            });
        }
        return true;
    }

    private boolean convertFile(File imageFile, String outputDirPath) {
        if (!imageFile.exists() || !imageFile.isFile()) {
            return false;
        } else {
            System.out.println(imageFile.getPath());
            ImageReader.readImage(imageFile.getPath())
                    .ifPresent(image -> {
                        image = ImageUtility.resizeImage(image, this.width, this.height);
                        if (this.grayScale) {
                            Dimension<Double> d = ImageUtility.convertToDimension(ImageUtility.grayScale(image)).get(0);
                            this.outputResult(outputDirPath, imageFile.getName() + ".csv", d);
                        }
                    });
        }
        return true;
    }

    private boolean outputResult(String outputDirPath, String fileName, Dimension<Double> d) {
        File outputDir = new File(outputDirPath);
        if (!outputDir.exists()) {
            if (!outputDir.mkdirs()){
                return false;
            }
        }
        File outputFile = new File(outputDirPath + File.separator + fileName);
        try (BufferedWriter bw = Files.newBufferedWriter(outputFile.toPath(), Charset.forName("utf-8"))){
            int width = d.getWidth();
            int height = d.getHeight();
            for (int i=0; i<height; i++) {
                String line = "";
                for (int j=0; j<width; j++) {
                    line += d.getValue(j, i) + (j < width-1 ? ",": "");
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String IMAGE_BASE_DIR = "/Users/takefumiota/work/machine_learning/data/";
        String LEARN_IMAGE_DIR = "learn";
        String TEST_IMAGE_DIR = "test";
        int WIDTH = 300;
        int HEIGHT = 300;
        new ImageConverter(IMAGE_BASE_DIR + LEARN_IMAGE_DIR, IMAGE_BASE_DIR + LEARN_IMAGE_DIR + "_gray", true, WIDTH, HEIGHT)
                .exec();
        new ImageConverter(IMAGE_BASE_DIR + TEST_IMAGE_DIR, IMAGE_BASE_DIR + TEST_IMAGE_DIR + "_gray", true, WIDTH, HEIGHT)
                .exec();
    }
}
