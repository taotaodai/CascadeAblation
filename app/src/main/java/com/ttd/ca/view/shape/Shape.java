package com.ttd.ca.view.shape;


import android.graphics.Region;

import com.ttd.ca.entity.Point;
import com.ttd.ca.entity.PointT;

import java.io.Serializable;

/**
 * Created by wt on 2018/3/12.
 */

public class Shape implements Serializable {
    /**
     * 原点
     */
    public Point origin;
    /**
     * 手指移动图形时的触摸点
     */
    public PointT tp;
    /**
     * 组成图形的各个顶点
     */
    Point[] peaks;
    /**
     * 图形的宽度
     */
    public int w;
    /**
     * 图形的高度
     */
    public int h;
    /**
     * 最左侧点的x坐标
     */
    public int xMin;
    /**
     * 最右侧点的x坐标
     */
    public int xMax;
    /**
     * 最上方点的y坐标
     */
    public int yMin;
    /**
     * 最下方点的y坐标
     */
    public int yMax;

    private Region region;
    private int type;

    public Shape(Point origin) {
        this.origin = origin;
        this.tp = new PointT(origin.x, origin.y);
    }

    public Shape(Point origin,int type) {
        this.origin = origin;
        this.tp = new PointT(origin.x, origin.y);
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

    protected void initOtherValues() {

    }
}
