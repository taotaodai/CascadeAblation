package com.ttd.ca.entity;

import java.io.Serializable;

/**
 * Created by wt on 2018/3/12.
 */

public class Point implements Serializable{
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
