package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;

/**
 * Created by Troy Tang on 2015-9-21.
 */
public class BlockFilter extends BaseFilter implements ImageFilter {

    private int mThreshold = 100;

    public BlockFilter() {

    }

    public BlockFilter(int threshold) {
        mThreshold = threshold;
    }

    public BlockFilter threshold(int threshold) {
        mThreshold = threshold;
        return this;
    }

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
        int pixColor, grayScale;
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < changeHeight; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                grayScale = grayScale(Color.red(pixColor), Color.green(pixColor), Color.blue(pixColor));
                pixels[width * i + k] = grayScale >= mThreshold ? Color.argb(Color.alpha(pixColor), 255, 255, 255)
                        : Color.argb(Color.alpha(pixColor), 0, 0 ,0);
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
