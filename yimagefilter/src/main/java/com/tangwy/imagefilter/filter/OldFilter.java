package com.tangwy.imagefilter.filter;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.tangwy.imagefilter.internal.BaseFilter;
import com.tangwy.imagefilter.internal.ImageFilter;
import com.tangwy.jnifilter.JniFilter;

/**
 * Old Style
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
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        pixels = JniFilter.oldFilter(pixels, width, changeHeight);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
