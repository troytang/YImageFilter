package com.findd.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.findd.imagefilter.internal.BaseFilter;
import com.findd.imagefilter.internal.ImageFilter;

/**
 * 暗调风格
 * 算法原理：
 * R = r * r / 255
 * G = g * g / 255
 * B = b * b / 255
 *
 * Created by Troy Tang on 2015-9-15.
 */
public class DarkFilter extends BaseFilter implements ImageFilter {

    @Override
    public Bitmap filter(Bitmap source) {
        return filter(source, 1.0f);
    }

    @Override
    public Bitmap filter(Bitmap source, float percent) {
        int width = source.getWidth();
        int height = source.getHeight();
        int changeHeight = (int) (percent * height);
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int pixColor;
        int pixA, pixR, pixG, pixB;
        int newR, newG, newB;
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < changeHeight; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                pixA = Color.alpha(pixColor);
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                newR = (int) (Math.pow(pixR, 2) / 255);
                newG = (int) (Math.pow(pixG, 2) / 255);
                newB = (int) (Math.pow(pixB, 2) / 255);

                pixels[width * i + k] = Color.argb(pixA, newR, newG, newB);
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
