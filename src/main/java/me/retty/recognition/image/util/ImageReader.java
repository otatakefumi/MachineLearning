package me.retty.recognition.image.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

/**
 * Created by takefumiota on 2015/09/20.
 */
public class ImageReader {
    public static Optional<BufferedImage> readImage(String path) {
        return ImageReader.readImage(new File(path));
    }

    public static Optional<BufferedImage> readImage(File file) {
        if (!file.exists()) {
            return Optional.ofNullable(null);
        }
        try {
            BufferedImage image = ImageIO.read(file);
            return Optional.of(image);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.ofNullable(null);
        } catch (NullPointerException e) {
            System.err.println(file.getPath());
            return Optional.ofNullable(null);
        }
    }

    public static Optional<Map<String, List<BufferedImage>>> readLearnImages(String learnImageDirPath, boolean grayScale, int width, int height) {
        File file = new File(learnImageDirPath);
        if (!file.exists() || !file.isDirectory()) {
            return Optional.ofNullable(null);
        } else {
            Map<String, List<BufferedImage>> res = new HashMap<>();
            File[] patterns = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory();
                }
            });
            Arrays.stream(patterns).forEach(p -> {
                List<BufferedImage> images = new ArrayList<>();
                String name = p.getName();
                File[] imageFiles = p.listFiles();
                Arrays.stream(imageFiles).forEach(image -> {
                    ImageReader.readImage(image).ifPresent(bi -> {
                        BufferedImage i = (BufferedImage) bi;
                        if (width > 0 && height > 0) {
                            i = ImageUtility.resizeImage(i, width, height);
                        }
                        if (grayScale) {
                            i = ImageUtility.grayScale(i);
                        }
                        images.add(i);
                    });
                });
                res.put(name, images);
            });
            return Optional.of(res);
        }
    }


    public static Optional<Map<String, List<BufferedImage>>> readLearnImages(String learnImageDirPath, boolean grayScale) {
        return ImageReader.readLearnImages(learnImageDirPath, grayScale, -1, -1);
    }
}
