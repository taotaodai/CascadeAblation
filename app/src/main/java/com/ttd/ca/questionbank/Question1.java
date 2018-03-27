package com.ttd.ca.questionbank;

import com.ttd.ca.entity.Point;
import com.ttd.ca.view.shape.Polygon;

import java.util.ArrayList;

/**
 * Created by wt on 2018/3/15.
 */
public class Question1 extends BaseQuestion {
    public Question1() {
        targetShapes = new ArrayList<>();
        targetShapes.add(new Polygon(new Point[]{new Point(0, 0), new Point(500, 0), new Point(0, 500)}, 1));
        targetShapes.add(new Polygon(new Point[]{new Point(200, 200), new Point(600, 200), new Point(600, 600), new Point(200, 600)}, 2));
        targetShapes.add(new Polygon(new Point[]{new Point(500, 300), new Point(800, 300), new Point(500, 600)}, 3));

        shapes = new ArrayList<>();
        shapes.add(new Polygon(new Point[]{new Point(0, 0), new Point(500, 0), new Point(0, 500)}, 1));
        shapes.add(new Polygon(new Point[]{new Point(0, 0), new Point(400, 0), new Point(400, 400), new Point(0, 400)}, 2));
        shapes.add(new Polygon(new Point[]{new Point(0, 0), new Point(300, 0), new Point(0, 300)}, 3));
    }
}
