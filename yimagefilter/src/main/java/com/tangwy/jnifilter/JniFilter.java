package com.tangwy.jnifilter;

/**
 * Created by Troy Tang on 2015-11-17.
 */
public class JniFilter {

    static {
        System.loadLibrary("jnifilter");
    }

    public native static int[] oldFilter(int[] pixels, int width, int height);

    public native static int[] blackWhiteFilter(int[] pixels, int width, int height);

    public native static int[] backSheetFilter(int[] pixels, int width, int height);

    public native static int[] darkFilter(int[] pixels, int width, int height);

    public native static int[] blockFilter(int[] pixels, int width, int height, int threshold);

    public native static int[] brightFilter(int[] pixels, int width, int height, int brightness, float contrast);

    public native static int[] gammaFilter(int[] pixels, int width, int height, double gamma);

    public native static int[] mosaicFilter(int[] pixels, int width, int height, int size);

    public native static int[] blurFilter(int[] pixels, int width, int height, float radius);

    public native static String fromJni();
}
