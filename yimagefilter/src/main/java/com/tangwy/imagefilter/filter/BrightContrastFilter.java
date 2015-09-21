package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;

/**
 * Created by Troy Tang on 2015-9-21.
 */
public class BrightContrastFilter extends BaseFilter implements ImageFilter {

    private int mBrightness = 100;
    private float mContrast = 1.0f;

    public BrightContrastFilter brightness(int brightness) {
        mBrightness = brightness;
        return this;
    }

    public BrightContrastFilter contrast(float contrast) {
        mContrast = contrast;
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
                pixColor = Color.argb(Color.alpha(pixColor), clamp(Color.red(pixColor) + mBrightness, 0, 255),
                        clamp(Color.green(pixColor) + mBrightness, 0, 255), clamp(Color.blue(pixColor) + mBrightness, 0, 255));
                if (1.0f < mContrast) {
                    pixColor = Color.argb(Color.alpha(pixColor), clamp(128 + (int) ((Color.red(pixColor) - 128) * mContrast), 0, 255),
                            clamp(128 + (int) ((Color.green(pixColor) - 128) * mContrast), 0, 255),
                            clamp(128 + (int) ((Color.blue(pixColor) - 128) * mContrast), 0, 255));

                }
                pixels[width * i + k] = pixColor;
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
