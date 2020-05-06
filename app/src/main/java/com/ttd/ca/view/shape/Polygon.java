package com.ttd.ca.view.shape;

import androidx.annotation.NonNull;

import com.ttd.ca.entity.Point;

/**
 * Created by wt on 2018/3/15.
 */

public class Polygon extends Shape {

    public Polygon(@NonNull Point[] peaks) {
        super(peaks[0]);
        this.peaks = peaks;
        initOtherValues();
    }

    public Polygon(@NonNull Point[] peaks, int type) {
        super(peaks[0], type);
        this.peaks = peaks;
        initOtherValues();
    }

    @Override
    public void setOrigin(Point origin) {
        int disX = origin.x - this.origin.x;
        int disY = origin.y - this.origin.y;
        /*
         * 遍历所有多边形的顶点，并添加位移，即原点的位移
         */
        for (Point point : peaks) {
            point.x += disX;
            point.y += disY;
        }
        initOtherValues();
        w = xMax - xMin;
        h = yMax - yMin;

        super.setOrigin(origin);
    }

    @Override
    protected void initOtherValues() {
        super.initOtherValues();

        xMin = getMinX();
        xMax = getMaxX();
        yMin = getMinY();
        yMax = getMaxY();
    }

    private int getMinX() {
        int x = 0;
        for (int i = 0; i < peaks.length; i++) {
            Point p = peaks[i];
            if (i == 0) {
                x = p.x;
            }
            if (p.x < x) {
                x = p.x;
            }
        }
        return x;
    }

    private int getMaxX() {
        int x = 0;
        for (int i = 0; i < peaks.length; i++) {
            Point p = peaks[i];
            if (i == 0) {
                x = p.x;
            }
            if (p.x > x) {
                x = p.x;
            }
        }
        return x;
    }

    private int getMinY() {
        int y = 0;
        for (int i = 0; i < peaks.length; i++) {
            Point p = peaks[i];
            if (i == 0) {
                y = p.y;
            }
            if (p.y < y) {
                y = p.y;
            }
        }
        return y;
    }

    private int getMaxY() {
        int y = 0;
        for (int i = 0; i < peaks.length; i++) {
            Point p = peaks[i];
            if (i == 0) {
                y = p.y;
            }
            if (p.y > y) {
                y = p.y;
            }
        }
        return y;
    }
}
