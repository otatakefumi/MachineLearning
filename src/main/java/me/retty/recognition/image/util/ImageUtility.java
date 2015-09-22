package me.retty.recognition.image.util;

import java.awt.image.BufferedImage;
import java.util.*;

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
}
