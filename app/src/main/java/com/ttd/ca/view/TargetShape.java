package com.ttd.ca.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import com.ttd.ca.utils.ShapeUtil;
import com.ttd.ca.view.shape.Shape;

import java.util.List;

/**
 * Created by wt on 2018/3/15.
 */

public class TargetShape extends View {
    private List<Shape> shapes;
    int[] wh = new int[2];

    public TargetShape(Context context) {
        super(context);
        init();
    }

    public TargetShape(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Deprecated
    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
        ShapeUtil.getWH(shapes, wh);
        requestLayout();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (shapes == null) {
            return;
        }

        shapeUtil.createShapes(canvas, shapes);
    }

    private ShapeUtil shapeUtil;
    private void init(){
        Region region = new Region(0, 0, wh[0], wh[1]);
        shapeUtil = new ShapeUtil(region);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(wh[0], wh[1]);
    }
}
