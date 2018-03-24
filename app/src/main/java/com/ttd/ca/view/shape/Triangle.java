package com.ttd.ca.view.shape;

import com.ttd.ca.entity.Point;

/**
 * Created by wt on 2018/3/13.
 */

public class Triangle extends Shape {

    public Point a;
    public Point b;
    public Point c;

    public Triangle(int w, int h, Point a, Point b, Point c) {
        super(a, w, h);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void setOrigin(Point origin) {
        a.x += origin.x - this.origin.x;
        a.y += origin.y - this.origin.y;
        b.x += origin.x - this.origin.x;
        b.y += origin.y - this.origin.y;
        c.x += origin.x - this.origin.x;
        c.y += origin.y - this.origin.y;
        super.setOrigin(origin);

    }
}
