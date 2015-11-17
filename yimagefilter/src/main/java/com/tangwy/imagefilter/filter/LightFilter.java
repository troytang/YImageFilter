package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;

/**
 * Created by Troy Tang on 2015-9-21.
 */
public class LightFilter extends BaseFilter implements ImageFilter {

    private int mCenterX = -1;
    private int mCenterY = -1;
    private int mRadius;
    private int mStrength;

    public LightFilter() {
        mStrength = 150;
    }

    public LightFilter centerX(int x) {
        mCenterX = x;
        return this;
    }

    public LightFilter centerY(int y) {
        mCenterY = y;
        return this;
    }

    public LightFilter radius(int radius) {
        mRadius = radius;
        return this;
    }

    public LightFilter strength(int strength) {
        mStrength = strength;
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
        mCenterX = -1 == mCenterX ? width / 2 : mCenterX;
        mCenterY = -1 == mCenterY ? height / 2 : mCenterY;
        mRadius = 0 == mRadius ? Math.min(mCenterX, mCenterY) : mRadius;
        mStrength = 0 == mStrength ? 150 : mStrength;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int pixColor;
        int pixA, pixR, pixG, pixB;
        int distance;
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < changeHeight; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                pixA = Color.alpha(pixColor);
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                distance = (int) (Math.pow((mCenterY - i), 2) + Math.pow(mCenterX - k, 2));
                if (distance < mRadius * mRadius) {
                    int result = (int) (mStrength * (1.0 - Math.sqrt(distance) / mRadius));
                    pixR = clamp(pixR + result, 0, 255);
                    pixG = clamp(pixG + result, 0, 255);
                    pixB = clamp(pixB + result, 0, 255);
                }
                pixels[width * i + k] = Color.argb(pixA, pixR, pixG, pixB);
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
