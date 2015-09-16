package com.findd.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.findd.imagefilter.internal.BaseFilter;
import com.findd.imagefilter.internal.ImageFilter;

/**
 * 马赛克风格
 * 算法原理：
 * 以MosaicSize为单位的全部像素值去最左上角的点的值
 *
 * Created by Troy Tang on 2015-9-15.
 */
public class MosaicFilter extends BaseFilter implements ImageFilter {

    private int mMosaicSize = 4;

    public MosaicFilter() {

    }

    public MosaicFilter(int mosaicSize) {
        mMosaicSize = mosaicSize;
    }

    public MosaicFilter mosaic(int mosaicSize) {
        mMosaicSize = mosaicSize;
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
        int pixColor;
        int newA = 0, newR = 0, newG = 0, newB = 0;
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < changeHeight; i++) {
            for (int k = 0; k < width; k++) {
                if (0 == i % mMosaicSize) {
                    if (0 == k % mMosaicSize) {
                        pixColor = pixels[width * i + k];
                        newA = Color.alpha(pixColor);
                        newR = Color.red(pixColor);
                        newG = Color.green(pixColor);
                        newB = Color.blue(pixColor);
                    }
                    pixels[width * i + k] = Color.argb(newA, newR, newG, newB);
                } else {
                    pixels[width * i + k] = pixels[width * (i - 1) + k];
                }
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
