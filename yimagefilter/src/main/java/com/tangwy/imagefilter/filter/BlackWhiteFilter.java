package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;

/**
 * White and Black
 * R = G = B = 0.229r + 0.114g + 0.587b
 *
 * Created by Troy Tang on 2015-9-15.
 */
public class BlackWhiteFilter extends BaseFilter implements ImageFilter {

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
        int newRGB;
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < changeHeight; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                pixA = Color.alpha(pixColor);
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                newRGB = grayScale(pixR, pixG, pixB);

                pixels[width * i + k] = Color.argb(pixA, newRGB, newRGB, newRGB);
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
