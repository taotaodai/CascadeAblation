package com.ttd.ca.view.shape;


import android.graphics.Region;

import com.ttd.ca.entity.Point;
import com.ttd.ca.entity.PointT;

import java.io.Serializable;

/**
 * Created by wt on 2018/3/12.
 */

public class Shape implements Serializable{
    /**
     * 原点
     */
    public Point origin;
    /**
     * 手指移动图形时的触摸点
     */
    public PointT tp;
    protected Point[] peaks;
    public int w;
    public int h;

    private Region region;
    private int type;

    public Shape(Point origin, int w, int h) {
        this.origin = origin;
        this.tp = new PointT(origin.x, origin.y);
        this.w = w;
        this.h = h;
    }

    public Shape(Point origin, int w, int h, int type) {
        this.origin = origin;
        this.tp = new PointT(origin.x, origin.y);
        this.w = w;
        this.h = h;
        this.type = type;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Point[] getPeaks() {
        return peaks;
    }

    public void setPeaks(Point[] peaks) {
        this.peaks = peaks;
    }

    public int getType() {
        return type;
    }
}
