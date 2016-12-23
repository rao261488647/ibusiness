package com.ibusiness.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 文件管理通用类
 * 
 * @author JiangBo
 *
 */
public class FileCommon {
    /**
     * 压缩图片功能函数
     * @param quality // 0.1f
     */
    public static boolean doCompressImage(String path, String fileName, float quality) {
        try {
            Image image = javax.imageio.ImageIO.read(new File(path + fileName));
            // 获取原图片宽度，高度
            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);
            image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_AREA_AVERAGING);
            // Make a BufferedImage from the Image.
            BufferedImage mBufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
            // 初始化Graphics2D对象
            Graphics2D g2 = mBufferedImage.createGraphics();
            g2.drawImage(image, 0, 0, imageWidth, imageHeight, Color.white, null);
            g2.dispose();
            float[] kernelData2 = { -0.125f, -0.125f, -0.125f, -0.125f, 2, -0.125f, -0.125f, -0.125f, -0.125f };
            Kernel kernel = new Kernel(3, 3, kernelData2);
            ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
            mBufferedImage = cOp.filter(mBufferedImage, null);
            // 输出流
            FileOutputStream out = new FileOutputStream(path + fileName);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(mBufferedImage);
            param.setQuality(quality, true);
            encoder.setJPEGEncodeParam(param);
            encoder.encode(mBufferedImage);
            //
            
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * 根据source图创建一张缩小的target图片
     */
    public static boolean createSmall(String source, String target, int width, int height) {
        try {
            // 设置图片比例
            ImageScale(source, target, width, height);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 设置图片比例
     */
    private static void ImageScale(String sourceImg, String targetImg, int width, int height) {
        try {
            Image image = ImageIO.read(new File(sourceImg));
            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);
            // 取得图片比率
            float scale = getRatio(imageWidth, imageHeight, width, height);
            imageWidth = (int) (scale * imageWidth);
            imageHeight = (int) (scale * imageHeight);

            // 创建一个扩展版的图象
            image = image.getScaledInstance(imageWidth, imageHeight, 16);

            // 将一副图片加载到内存中
            BufferedImage mBufferedImage = new BufferedImage(imageWidth, imageHeight, 1);
            // 创建一个2D图形
            Graphics2D g2 = mBufferedImage.createGraphics();
            g2.drawImage(image, 0, 0, imageWidth, imageHeight, Color.white, null);
            g2.dispose();

            float[] kernelData2 = {-0.125F, -0.125F, -0.125F, -0.125F, 2.0F, -0.125F, -0.125F, -0.125F, -0.125F };
            Kernel kernel = new Kernel(3, 3, kernelData2);
            // 根据Kernel,边缘条件和 RenderingHint对象(可以为 null)实例化ConvolveOp
            ConvolveOp cOp = new ConvolveOp(kernel, 1, null);
            mBufferedImage = cOp.filter(mBufferedImage, null);
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(mBufferedImage);
            out.close();
        } catch (FileNotFoundException localFileNotFoundException) {
        } catch (IOException localIOException) {
        }
    }
    /**
     * 取得图片比率
     * @return
     */
    private static float getRatio(int width, int height, int maxWidth, int maxHeight) {
        float Ratio = 1.0F;
        float widthRatio = (float)maxWidth / width;
        float heightRatio = (float)maxHeight / height;
        if ((widthRatio < 1.0D) || (heightRatio < 1.0D)) {
            Ratio = widthRatio <= heightRatio ? widthRatio : heightRatio;
        }
        return Ratio;
    }
}
