package com.findd.imagefilter;

import android.graphics.Bitmap;

import com.findd.imagefilter.internal.ImageFilter;

/**
 * Created by Troy Tang on 2015-9-14.
 */
public class YImageFilter {

    public static Bitmap filter(ImageFilter imageFilter, Bitmap bitmap) {
        return imageFilter.filter(bitmap);
    }

    public static Bitmap filter(ImageFilter imageFilter, Bitmap bitmap, float percent) {
        return imageFilter.filter(bitmap, percent);
    }
}
