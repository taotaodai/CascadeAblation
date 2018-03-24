package com.ttd.ca.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ttd.ca.entity.Point;

import static com.ttd.ca.utils.ShapeUtil.UNIT_SIZE;

/**
 * Created by wt on 2018/3/14.
 */

public class WorkBench extends View{
    public static final Point[][] DOT_MATRIX = new Point[10][10];

    public WorkBench(Context context) {
        super(context);
        initDotMatrix();
    }

    public WorkBench(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDotMatrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawDotMatrix(canvas);
    }

    private void initDotMatrix() {
        for (int i = 0; i < DOT_MATRIX.length; i++) {
            Point[] row = DOT_MATRIX[i];
            for (int j = 0; j < row.length; j++) {
                row[j] = new Point(j * UNIT_SIZE, i * UNIT_SIZE);
            }
        }
    }

    private void drawDotMatrix(Canvas canvas) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.GRAY);

        for (Point[] row : DOT_MATRIX) {
            for (Point point : row) {
                canvas.drawCircle(point.x, point.y, 10, p);
            }
        }

    }
}
