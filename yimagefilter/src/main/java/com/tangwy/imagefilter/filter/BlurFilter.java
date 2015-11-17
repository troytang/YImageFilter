package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;
import com.tangwy.jnifilter.JniFilter;

/**
 * Created by Troy Tang on 2015-9-16.
 */
public class BlurFilter extends BaseFilter implements ImageFilter {

    private int mBlurRadius = 15;

    public BlurFilter() {

    }

    public BlurFilter radius(int blurRadius) {
        mBlurRadius = blurRadius;
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
        int changeHeight = clamp((int) (percent * height), 1, height);
        int[] pixels = new int[width * height];
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        pixels = JniFilter.blurFilter(pixels, width, changeHeight, mBlurRadius);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
