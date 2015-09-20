package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;

/**
 * 底片风格
 * 算法原理：
 * R = 255 - r;
 * G = 255 - g;
 * B = 255 - b;
 *
 * Created by Troy Tang on 2015-9-14.
 */
public class BacksheetFilter extends BaseFilter implements ImageFilter {

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

                newR = clamp(255 - pixR, 0, 255);
                newG = clamp(255 - pixG, 0, 255);
                newB = clamp(255 - pixB, 0, 255);

                pixels[width * i + k] = Color.argb(pixA, newR, newG, newB);
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
