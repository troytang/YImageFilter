package com.tangwy.imagefilter.internal;

/**
 * Created by Troy Tang on 2015-9-14.
 */
public abstract class BaseFilter {

    /**
     * ensure x in[a,b]
     *
     * @param x
     * @param a
     * @param b
     * @return
     */
    public int clamp(int x, int a, int b) {
        return (x < a) ? a : (x > b) ? b : x;
    }

    /**
     * get color gray scale
     *
     * @param r
     * @param g
     * @param b
     * @return
     */
    public int grayScale(int r, int g, int b) {
        return clamp((int) (0.229 * r + 0.114 * g + 0.587 * b), 0, 255);
    }
}
