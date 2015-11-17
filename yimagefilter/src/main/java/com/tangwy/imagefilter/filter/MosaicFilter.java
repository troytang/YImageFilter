package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;
import com.tangwy.jnifilter.JniFilter;

/**
 * Mosaic Style
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
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        pixels = JniFilter.mosaicFilter(pixels, width, changeHeight, mMosaicSize);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
