package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;

/**
 * 怀旧风格
 * 算法原理：
 * R = 0.393r + 0.769g + 0.189b
 * G = 0.349r + 0.686g + 0.168b
 * B = 0.272r + 0.534g + 0.131b
 *
 * Created by Troy Tang on 2015-9-14.
 */
public class OldFilter extends BaseFilter implements ImageFilter {

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
        int pixR, pixG, pixB;
        int newA, newR, newG, newB;
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < changeHeight; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                newA = Color.alpha(pixColor);
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                newR = clamp((int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB), 0, 255);
                newG = clamp((int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB), 0, 255);
                newB = clamp((int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB), 0, 255);
                pixels[width * i + k] = Color.argb(newA, newR, newG, newB);
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
