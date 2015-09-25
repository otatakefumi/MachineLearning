package me.retty.recognition.image.util;

import me.retty.recognition.base.Dimension;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

/**
 * Created by takefumiota on 2015/09/20.
 */
public class ImageUtility {
    public static int a(int c){
        return c>>>24;
    }
    public static int r(int c){
        return c>>16&0xff;
    }
    public static int g(int c){
        return c>>8&0xff;
    }
    public static int b(int c){
        return c&0xff;
    }
    public static int rgb(int r,int g,int b){
        return 0xff000000 | r <<16 | g <<8 | b;
    }
    public static int argb(int a,int r,int g,int b){
        return a<<24 | r <<16 | g <<8 | b;
    }

    public static BufferedImage grayScale(BufferedImage image) {
        BufferedImage res = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        res.getGraphics().drawImage(image, 0, 0, null);
        return res;
    }

    public static List<BufferedImage> grayScale(List<BufferedImage> images) {
        List<BufferedImage> res = new ArrayList<>();
        images.forEach(image -> res.add(ImageUtility.grayScale(image)));
        return res;
    }

    public static List<Dimension<Double>> convertToDimension(BufferedImage image) {
        List<Dimension<Double>> res = new ArrayList<>();
        int width = image.getWidth();
        int height = image.getHeight();
        if (image.getType() == BufferedImage.TYPE_BYTE_GRAY) {
            Dimension<Double> d = new Dimension<>(width, height);
            for (int i=0; i<height; i++) {
                for (int j=0; j<width; j++) {
                    d.setData(j, i, (double)ImageUtility.r(image.getRGB(j, i)));
                }
            }
            res.add(d);
        }
        return res;
    }

    public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        if (image.getWidth() == width && image.getHeight() == height) {
            return image;
        }

        // 縮小比率の計算
        BigDecimal bdW = new BigDecimal(width);
        bdW = bdW.divide(new BigDecimal(image.getWidth()), 8, BigDecimal.ROUND_HALF_UP);
        BigDecimal bdH = new BigDecimal(height);
        bdH = bdH.divide(new BigDecimal(image.getHeight()), 8, BigDecimal.ROUND_HALF_UP);
        // 縦横比は変えずに最大幅、最大高さを超えないように比率を指定する
        if (bdH.compareTo(bdW) < 0) {
            width = -1;
        }
        else {
            height = -1;
        }

        // スケールは以下から選択
        // Image.SCALE_AREA_AVERAGING 遅いがなめらか
        // Image.SCALE_DEFAULT 普通 速度はFASTと変わらない
        // Image.SCALE_FAST 早くて汚い
        // Image.SCALE_REPLICATE わからん そこそこ汚い
        // Image.SCALE_SMOOTH 遅くてなめらか
        Image targetImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Image -> BufferedImageの変換
        BufferedImage targetBufferedImage = new BufferedImage(targetImage.getWidth(null), targetImage.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = targetBufferedImage.createGraphics();
        g.drawImage(targetImage, 0, 0, null);

        return targetBufferedImage;
    }
}
