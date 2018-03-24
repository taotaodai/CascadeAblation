package com.ttd.ca.questionbank;

import com.ttd.ca.view.shape.Shape;

import java.util.List;

/**
 * Created by wt on 2018/3/15.
 */

public class BaseQuestion {
    protected List<Shape> shapes;
    protected List<Shape> targetShapes;

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> getTargetShapes() {
        return targetShapes;
    }

    public void setTargetShapes(List<Shape> targetShapes) {
        this.targetShapes = targetShapes;
    }
}
