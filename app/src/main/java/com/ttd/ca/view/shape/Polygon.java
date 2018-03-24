package com.ttd.ca.view.shape;

import android.support.annotation.NonNull;

import com.ttd.ca.entity.Point;

/**
 * Created by wt on 2018/3/15.
 */

public class Polygon extends Shape {

    public Polygon(@NonNull Point[] peaks, int w, int h) {
        super(peaks[0], w, h);
        this.peaks = peaks;
    }

    public Polygon(@NonNull Point[] peaks, int w, int h, int type) {
        super(peaks[0], w, h, type);
        this.peaks = peaks;
    }

    @Override
    public void setOrigin(Point origin) {
        int disX = origin.x - this.origin.x;
        int disY = origin.y - this.origin.y;
        /**
         * 遍历所有多边形的顶点，并添加位移，即原点的位移
         */
        for (int i = 0; i < peaks.length; i++) {
            Point point = peaks[i];
//            Log.i("jjjjjjjjjj", point.x + "+" + origin.x + "-" + this.origin.x);
            point.x += disX;
            point.y += disY;

//            Log.i("kkkkkkkkkk", i+":"+point.x);
//            Log.i("kkkkkkkkkk", "点" + i + "坐标[" + point.x + "," + point.y + "]");
        }
        super.setOrigin(origin);
    }
}
