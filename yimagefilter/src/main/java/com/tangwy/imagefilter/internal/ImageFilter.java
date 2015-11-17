package com.tangwy.imagefilter.internal;

import android.graphics.Bitmap;

/**
 *
 * Created by Troy Tang on 2015-9-14.
 */
public interface ImageFilter {

    Bitmap filter(Bitmap source);

    Bitmap filter(Bitmap source, float percent);
}
