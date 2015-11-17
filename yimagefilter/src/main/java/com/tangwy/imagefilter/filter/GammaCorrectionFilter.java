package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;
import com.tangwy.jnifilter.JniFilter;

/**
 * Created by Troy Tang on 2015-9-21.
 */
public class GammaCorrectionFilter extends BaseFilter implements ImageFilter {

    private double mGamma;

    public GammaCorrectionFilter() {
        this(2.2d);
    }

    public GammaCorrectionFilter(double gamma) {
        mGamma = gamma;
    }

    public GammaCorrectionFilter gamma(double gamma) {
        mGamma = gamma;
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
        pixels = JniFilter.gammaFilter(pixels, width, changeHeight, mGamma);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
