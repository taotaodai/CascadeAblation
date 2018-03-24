package com.ttd.ca.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region;

import com.ttd.ca.entity.Point;
import com.ttd.ca.view.shape.Circle;
import com.ttd.ca.view.shape.Polygon;
import com.ttd.ca.view.shape.Shape;
import com.ttd.ca.view.shape.Square;
import com.ttd.ca.view.shape.Triangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by wt on 2018/3/15.
 */

public class ShapeUtil {
    private Region region;

    public static final int UNIT_SIZE = 100;

    public ShapeUtil(Region region) {
        this.region = region;
    }

    public void createShapes(Canvas canvas, List<Shape> shapes) {
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int sc = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            if (i > 0) {
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
            }
            createShape(shape, canvas, mPaint);

        }

        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }

    private void createShape(Shape shape, Canvas canvas, Paint p) {
//        if (region == null) {
//            region = new Region(0, 0, getMeasuredWidth(), getMeasuredHeight());
//        }

        p.setColor(0xFF66AAFF);
        Region r = new Region();
        Path path = new Path();
        if (shape instanceof Circle) {
            path.addCircle(shape.getOrigin().x, shape.getOrigin().y, shape.w, Path.Direction.CW);

        } else if (shape instanceof Square) {
            path.addRect(shape.getOrigin().x, shape.getOrigin().y, shape.getOrigin().x + shape.w, shape.getOrigin().y + shape.h, Path.Direction.CW);
        } else if (shape instanceof Triangle) {
            Triangle t = ((Triangle) shape);
            path.moveTo(t.a.x, t.a.y);
            path.lineTo(t.b.x, t.b.y);
            path.lineTo(t.c.x, t.c.y);
            path.close();
        } else if (shape instanceof Polygon) {
            Point[] peaks = ((Polygon) shape).getPeaks();
            for (int i = 0; i < peaks.length; i++) {
                Point point = peaks[i];
                if (i == 0) {
                    path.moveTo(point.x, point.y);
                } else {
                    path.lineTo(point.x, point.y);
                }
            }
        }

        r.setPath(path, region);
        canvas.drawPath(path, p);
        shape.setRegion(r);

    }

    public static void getWH(List<Shape> shapes, int[] wh) {
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        for (int i = 0; i < shapes.size(); i++) {
            Point[] peaks = shapes.get(i).getPeaks();
            for (int j = 0; j < peaks.length; j++) {
                xs.add(peaks[j].x);
                ys.add(peaks[j].y);
            }
        }

        Collections.sort(xs);
        Collections.sort(ys);

        wh[0] = xs.get(xs.size() - 1);
        wh[1] = ys.get(ys.size() - 1);
    }

    /**
     * 以原点为参照，根据其他图形和原点的相对位移，来判断待层叠图形是否和目标图形匹配。
     * 这里选择目标图形的第一个子图形的原点作为基准点，由于待层叠图形中有可能有多个子图形与基准图形形状一致，
     * 所以还需要遍历每种情况。
     * 当每个子图形的相对位移以及形状都匹配时，视为验证成功。
     */
    public static boolean verifyShapes(List<Shape> targets, List<Shape> shapes) {

        int matchCount = 0;
        for (int i = 0; i < targets.size(); i++) {
            matchCount = 0;
            List<Shape> t = new ArrayList<>(Arrays.asList(new Shape[targets.size()]));
            List<Shape> s = new ArrayList<>(Arrays.asList(new Shape[shapes.size()]));

            Collections.copy(t, targets);
            Collections.copy(s, shapes);

            /**
             *
             * 1.目标图形的原点：固定已知
             */
            Shape shapeT = targets.get(0);
            Point originT = shapeT.getOrigin();

            Point originS;

            Shape tempS = shapes.get(i);
            /**
             * 2.将与基准图一致那个图的原点作为待层叠图的基准点
             */
            if (tempS.getType() == shapeT.getType()) {
                originS = tempS.getOrigin();
                for (int j = 0; j < t.size(); j++) {
                    Shape target = t.get(j);
                    Point pt = target.getOrigin();
                    for (int m = 0; m < s.size(); m++) {
                        Shape shape = s.get(m);
                        Point ps = shape.getOrigin();
                        /**
                         * 3.验证与基准图形的相对位移。如果相对位移一致，并且形状一致，即有一个图形匹配
                         */
                        if ((pt.x - originT.x == ps.x - originS.x)
                                && (pt.y - originT.y == ps.y - originS.y)
                                && target.getType() == shape.getType()) {
                            matchCount++;
                            s.remove(m);
                            t.remove(target);
                            j--;
                            break;
                        }
                    }
                }
            }
            if(matchCount == targets.size()){
                return true;
            }
        }
        return matchCount == targets.size();
    }

}
