package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;

/**
 * Created by Troy Tang on 2015-9-15.
 */
public class PunchFilter extends BaseFilter implements ImageFilter {

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

                newR = clamp(pixR < 128 ? grayR(pixR) : 256 - grayR(pixR), 0, 255);
                newG = clamp(pixG < 128 ? grayG(pixG) : 256 - grayG(pixG), 0, 255);
                newB = clamp(pixB / 2 + 0x25, 0, 255);

                pixels[width + i + k] = Color.argb(pixA, newR, newG, newB);
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private int grayR(int rValue) {
        if (128 > rValue) {
            return (int) (Math.pow(rValue, 3) / 64 / 256);
        } else {
            return (int) (Math.pow(256 - rValue, 3) / 64 / 256);
        }
    }

    private int grayG(int gbValue) {
        if (128 > gbValue) {
            return (int) (Math.pow(gbValue, 2) / 128);
        } else {
            return (int) (Math.pow(256 - gbValue, 2) / 128);
        }
    }
}
