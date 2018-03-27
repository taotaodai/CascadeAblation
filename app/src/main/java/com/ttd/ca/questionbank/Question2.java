package com.ttd.ca.questionbank;

import com.ttd.ca.entity.Point;
import com.ttd.ca.view.shape.Polygon;

import java.util.ArrayList;

/**
 * Created by wt on 2018/3/15.
 */

public class Question2 extends BaseQuestion {
    public Question2() {
        targetShapes = new ArrayList<>();
        targetShapes.add(new Polygon(new Point[]{new Point(0, 0), new Point(300, 0), new Point(300, 300), new Point(0, 300)}));
        targetShapes.add(new Polygon(new Point[]{new Point(100, 0), new Point(400, 0), new Point(400, 300), new Point(100, 300)}));
        targetShapes.add(new Polygon(new Point[]{new Point(200, 0), new Point(500, 0), new Point(500, 300), new Point(200, 300)}));
        targetShapes.add(new Polygon(new Point[]{new Point(100, 200), new Point(400, 200), new Point(400, 500), new Point(100, 500)}));

        shapes = new ArrayList<>();
        shapes.add(new Polygon(new Point[]{new Point(0, 0), new Point(300, 0), new Point(300, 300), new Point(0, 300)}));
        shapes.add(new Polygon(new Point[]{new Point(0, 0), new Point(300, 0), new Point(300, 300), new Point(0, 300)}));
        shapes.add(new Polygon(new Point[]{new Point(0, 0), new Point(300, 0), new Point(300, 300), new Point(0, 300)}));
        shapes.add(new Polygon(new Point[]{new Point(0, 0), new Point(300, 0), new Point(300, 300), new Point(0, 300)}));
    }
}
