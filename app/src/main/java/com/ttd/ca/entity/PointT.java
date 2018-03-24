package com.ttd.ca.entity;

import java.io.Serializable;

/**
 * Created by wt on 2018/3/15.
 */

public class PointT implements Serializable{
    public float x;
    public float y;

    public PointT(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
