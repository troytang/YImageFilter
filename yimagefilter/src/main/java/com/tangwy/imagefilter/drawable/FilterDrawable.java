package com.tangwy.imagefilter.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.tangwy.imagefilter.YImageFilter;
import com.tangwy.imagefilter.internal.ImageFilter;

/**
 * Created by Troy Tang on 2015-9-14.
 */
public class FilterDrawable extends Drawable implements Animatable {

    ImageView mParent;
    Bitmap mBitmap;
    Animation mAnimation;

    ImageFilter mImageFilter;
    float mPercent;

    public FilterDrawable(ImageView parent, ImageFilter imageFilter, Bitmap bitmap) {
        this(parent, imageFilter, bitmap, false);
    }

    public FilterDrawable(final ImageView parent, ImageFilter imageFilter, final Bitmap bitmap, final boolean isCut) {
        mParent = parent;
        mBitmap = bitmap;
        mImageFilter = imageFilter;
        setupAnimation();
        mParent.post(new Runnable() {
            @Override
            public void run() {
                createBitmap(parent, bitmap, isCut);
            }
        });
    }

    private void setPercent(float percent) {
        mPercent = percent;
        invalidateSelf();
    }

    private void setupAnimation() {
        mAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setPercent(interpolatedTime);
            }
        };

        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setDuration(1500);
    }

    private void createBitmap(View view, Bitmap bitmap, boolean isCut) {
        Matrix matrix = new Matrix();
        float scale;
        int widthOffset, heightOffset;
        if ((float) view.getWidth() / (float) bitmap.getWidth() > (float) view.getHeight() / (float) bitmap.getHeight()
                && (1 < (float) view.getWidth() / (float) bitmap.getWidth() || isCut)) {
            scale = (float) view.getWidth() / (float) bitmap.getWidth();
            widthOffset = 0;
            heightOffset = (int) (bitmap.getHeight() - (float) view.getHeight() * (float) bitmap.getWidth() / (float) view.getWidth()) / 2;
        } else {
            scale = (float) view.getHeight() / (float) bitmap.getHeight();
            heightOffset = 0;
            widthOffset = (int) (bitmap.getWidth() - (float) view.getWidth() * (float) bitmap.getHeight() / (float) view.getHeight()) / 2;
        }
        matrix.postScale(scale, scale);
        Bitmap scaleBmp;
        if (0 == view.getMeasuredWidth() && 0 == view.getMeasuredHeight()) {
            scaleBmp = Bitmap.createBitmap(bitmap);
        } else {
            if (isCut) {
                scaleBmp = Bitmap.createBitmap(bitmap, widthOffset, heightOffset,
                        bitmap.getWidth() - widthOffset * 2, bitmap.getHeight() - heightOffset * 2, matrix, true);
            } else {
                scaleBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }

        }
        mBitmap = scaleBmp;
    }

    @Override
    public void start() {
        mAnimation.reset();
        mParent.startAnimation(mAnimation);
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {

        final int saveCount = canvas.save();

        Matrix matrix = new Matrix();
        Bitmap bmp = YImageFilter.filter(mImageFilter, mBitmap, mPercent);
        int widthOffset = mParent.getWidth() > bmp.getWidth() ? (mParent.getWidth() - bmp.getWidth()) / 2 : 0 ;
        int heightOffset = mParent.getHeight() > bmp.getHeight() ? (mParent.getHeight() - bmp.getHeight()) / 2 : 0 ;
        canvas.translate(widthOffset, heightOffset);
        canvas.drawBitmap(bmp, matrix, null);
        bmp.recycle();
        bmp = null;

        canvas.restoreToCount(saveCount);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
