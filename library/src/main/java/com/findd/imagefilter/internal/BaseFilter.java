package com.findd.imagefilter.internal;

/**
 * Created by Troy Tang on 2015-9-14.
 */
public abstract class BaseFilter {

    /**
     * 确保x值在区间[a,b]
     *
     * @param x
     * @param a
     * @param b
     * @return
     */
    public int clamp(int x, int a, int b) {
        return (x < a) ? a : (x > b) ? b : x;
    }

}
