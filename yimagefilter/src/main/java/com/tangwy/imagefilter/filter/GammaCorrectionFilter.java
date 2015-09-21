package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;

/**
 * Created by Troy Tang on 2015-9-21.
 */
public class GammaCorrectionFilter extends BaseFilter implements ImageFilter {

    private double mGamma;
    private double[] mGammaTable;

    public GammaCorrectionFilter() {
        this(2.2d);
    }

    public GammaCorrectionFilter(double gamma) {
        mGamma = gamma;
        mGammaTable = new double[256];
        initGammaTable();
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
        int pixColor;
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < changeHeight; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                pixels[width * i + k] = Color.argb(Color.alpha(pixColor), clamp((int) mGammaTable[Color.red(pixColor)], 0, 255),
                        clamp((int) mGammaTable[Color.green(pixColor)], 0, 255), clamp((int) mGammaTable[Color.blue(pixColor)], 0, 255));
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private void initGammaTable() {
        double inverseGamma = 1.0d / mGamma;
        for (int i = 0; i < 256; i++) {
            mGammaTable[i] = Math.pow(((double)i / 256d), inverseGamma) * 256d;
        }
    }
}
