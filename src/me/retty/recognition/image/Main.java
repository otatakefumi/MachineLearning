package me.retty.recognition.image;

import me.retty.recognition.image.util.ImageReader;
import me.retty.recognition.image.util.ImageUtility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Main {
    public static String IMAGE_BASE_DIR = "/Users/takefumiota/work/machine_learning/data/";
    public static String LEARN_IMAGE_DIR = "learn/";
    public static String TEST_IMAGE_DIR = "test/";

    public static void main(String[] args) {
//        Optional<BufferedImage> op = ImageReader.readImage("/Users/takefumiota/work/machine_learning/data/learn/1/100001.jpg");
//        op.ifPresent(s -> {
//            System.out.println(s);
//            System.out.println(s.getRGB(0, 0));
//            System.out.println(ImageUtility.r(s.getRGB(0, 0)));
//            System.out.println(ImageUtility.g(s.getRGB(0, 0)));
//            System.out.println(ImageUtility.b(s.getRGB(0, 0)));
//            System.out.println(ImageUtility.r(ImageUtility.grayScale(s).getRGB(0, 0)));
//            System.out.println(ImageUtility.g(ImageUtility.grayScale(s).getRGB(0, 0)));
//            System.out.println(ImageUtility.b(ImageUtility.grayScale(s).getRGB(0, 0)));
//        });
        System.out.println("Read Learning Files");
        ImageReader.readLearnImages(IMAGE_BASE_DIR + LEARN_IMAGE_DIR);
    }
}
