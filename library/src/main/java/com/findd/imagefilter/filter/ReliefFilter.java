package com.findd.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.findd.imagefilter.internal.BaseFilter;
import com.findd.imagefilter.internal.ImageFilter;

/**
 * 浮雕风格
 * 算法原理：
 * R = pr - r + 127
 * G = pg - g + 127
 * B = pb - b + 127
 *
 * Created by Troy Tang on 2015-9-14.
 */
public class ReliefFilter extends BaseFilter implements ImageFilter {

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
        int preColor, pixColor;
        int pixA, pixR, pixG, pixB;
        int preR, preG, preB;
        int newR, newG, newB;
        int[] pixels = new int[width * height];
        int[] newPixles = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        newPixles[0] = pixels[0];
        for (int i = 1; i < height * width; i++) {
            if (i < changeHeight * width) {
                preColor = pixels[i - 1];
                pixColor = pixels[i];

                preR = Color.red(preColor);
                preG = Color.green(preColor);
                preB = Color.blue(preColor);

                pixA = Color.alpha(pixColor);
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                newR = clamp(preR - pixR + 127, 0, 255);
                newG = clamp(preG - pixG + 127, 0, 255);
                newB = clamp(preB - pixB + 127, 0, 255);

                newPixles[i] = Color.argb(pixA, newR, newG, newB);
            } else {
                newPixles[i] = pixels[i];
            }
        }

        bitmap.setPixels(newPixles, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
