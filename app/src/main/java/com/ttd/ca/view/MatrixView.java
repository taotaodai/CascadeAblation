package com.ttd.ca.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ttd.ca.entity.Point;
import com.ttd.ca.utils.ShapeUtil;
import com.ttd.ca.view.shape.Shape;

import java.util.ArrayList;
import java.util.List;

import static com.ttd.ca.utils.ShapeUtil.UNIT_SIZE;
import static com.ttd.ca.view.WorkBench.DOT_MATRIX;
import static com.ttd.ca.view.WorkBench.SIZE;

/**
 * Created by wt on 2018/3/12.
 */

public class MatrixView extends View {

    private int[] wh = new int[2];
    private List<Shape> shapes;
    private OnVerifyListener onVerifyListener;

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

    public MatrixView(Context context) {
        super(context);
        init();
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnVerifyListener(OnVerifyListener onVerifyListener) {
        this.onVerifyListener = onVerifyListener;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (shapes == null) {
            return;
        }

        @SuppressLint("DrawAllocation") Region region = new Region(0, 0, getMeasuredWidth(), getMeasuredHeight());
        @SuppressLint("DrawAllocation") ShapeUtil shapeUtil= new ShapeUtil(region);
        shapeUtil.createShapes(canvas, shapes);
    }

    private void init(){
    }


    /**
     * 为了提高游戏体验，
     * 计算原点距离点阵中最近的点，并将原点设为该点
     *
     */
    private void autoPressClose(Shape shape) {
        int lineIndex = shape.origin.x / UNIT_SIZE;
        int rowIndex = shape.origin.y / UNIT_SIZE;

        Point sp = new Point(shape.origin.x, shape.origin.y);

        Point outSize = verifyOutofRange(sp, shape);
        if (outSize != null) {
            shape.setOrigin(outSize);
        } else {
            /*
             * 1.获取点阵中和原点距离较近的点，至多四个点
             */
            Point p1 = null;
            try {
                p1 = DOT_MATRIX[rowIndex][lineIndex];
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            Point p2 = null;
            try {
                p2 = DOT_MATRIX[rowIndex][lineIndex + 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            Point p3 = null;

            try {
                p3 = DOT_MATRIX[rowIndex + 1][lineIndex];
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            Point p4 = null;
            try {
                p4 = DOT_MATRIX[rowIndex + 1][lineIndex + 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            List<Float> targets = new ArrayList<>();
            List<Point> adjacent = new ArrayList<>();
            if (p1 != null) {
                adjacent.add(p1);
                targets.add(getDistance(sp, p1));
            }

            if (p2 != null) {
                adjacent.add(p2);
                targets.add(getDistance(sp, p2));
            }

            if (p3 != null) {
                adjacent.add(p3);
                targets.add(getDistance(sp, p3));
            }

            if (p4 != null) {
                adjacent.add(p4);
                targets.add(getDistance(sp, p4));
            }
            /*
             * 2.获取与原点距离最近的点
             */
            int minIndex = getMinIndex(targets);

            int x = adjacent.get(minIndex).x;
            int y = adjacent.get(minIndex).y;
            shape.setOrigin(new Point(x, y));
        }
    }

    /**
     * 根据两点距离公式计算出各点到原点的距离，
     * 这里偷懒一下，没加根号，但不影响结果
     *
     */
    private float getDistance(Point p1, Point p2) {
        return (float) (Math.pow(Math.abs(p2.x - p1.x), 2) + Math.pow(Math.abs(p2.y - p1.y), 2));
    }

    /**
     * 获得最小距离的下标
     *
     */
    public static int getMinIndex(List<Float> arr) {
        int minIndex = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < arr.get(minIndex)) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private Point verifyOutofRange(Point sp, Shape shape) {
        Point vp = null;
        int wbSize = (SIZE - 1) * ShapeUtil.UNIT_SIZE;
        /*
         * 当原点超出左右边界时
         */
        boolean outofX = false;
        if (shape.xMin < 0) {
            vp = new Point(sp.x + Math.abs(shape.xMin), sp.y);
            outofX = true;
        } else if (shape.xMax > wbSize) {
            vp = new Point(sp.x - (shape.xMax - wbSize), sp.y);
            outofX = true;
        }

        boolean outofY = false;
        if (shape.yMin < 0) {
            int dis = Math.abs(shape.yMin);
            if (vp == null) {
                vp = new Point(sp.x, sp.y + dis);
            } else {
                vp.y = sp.y + dis;
            }
            outofY = true;
        } else if (shape.yMax > wbSize) {
            int dis = shape.yMax - wbSize;
            if (vp == null) {
                vp = new Point(sp.x, sp.y - dis);
            } else {
                vp.y = sp.y - dis;
            }
            outofY = true;
        }

        if (vp != null) {
            if (!outofX) {
                int temp = sp.x / ShapeUtil.UNIT_SIZE;
                if (sp.x - temp * ShapeUtil.UNIT_SIZE < ShapeUtil.UNIT_SIZE * (temp + 1) - sp.x) {
                    vp.x = temp * ShapeUtil.UNIT_SIZE;
                } else {
                    vp.x = (temp + 1) * ShapeUtil.UNIT_SIZE;
                }
            }
            if (!outofY) {
                int temp = sp.y / ShapeUtil.UNIT_SIZE;
                if (sp.y - temp * ShapeUtil.UNIT_SIZE < ShapeUtil.UNIT_SIZE * (temp + 1) - sp.y) {
                    vp.y = temp * ShapeUtil.UNIT_SIZE;
                } else {
                    vp.y = (temp + 1) * ShapeUtil.UNIT_SIZE;
                }
            }
        }

        return vp;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Shape shape = getSelectedShape(event);

        if (shape == null) {
            return true;
        }

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                setShapeTouchPoint(shape, event);
                break;
            case MotionEvent.ACTION_MOVE:
                setShapeNewPlace(shape, event);
                setShapeTouchPoint(shape, event);
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                autoPressClose(shape);
                setShapeTouchPoint(shape, event);

                postInvalidate();
                if (onVerifyListener != null) {
                    onVerifyListener.onVerify();
                }
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * 确定图形移动时手指的接触点
     */
    private void setShapeTouchPoint(Shape shape, MotionEvent event) {
        shape.tp.x = (int) event.getX();
        shape.tp.y = (int) event.getY();
    }

    /**
     * 确定图形移动后的新位置(改变原点位置)
     *
     */
    private void setShapeNewPlace(Shape shape, MotionEvent event) {
        shape.setOrigin(new Point(Math.round(shape.getOrigin().x + event.getX() - shape.tp.x),
                Math.round(shape.getOrigin().y + event.getY() - shape.tp.y)));
    }

    private Shape latestShape;

    private Shape getSelectedShape(MotionEvent event) {
        if (latestShape == null) {
            for (int i = 0; i < shapes.size(); i++) {
                Shape shape = shapes.get(i);
                if (isShapeSelected(event, shape)) {
//                    Log.i("aaaaaaaaaaa", "选中" + shape.getClass().getSimpleName() + "i");
                    latestShape = shape;
                    return latestShape;
                }
            }
        } else {
            if (!isShapeSelected(event, latestShape)) {
                latestShape = null;
                return getSelectedShape(event);
            }
        }

        return latestShape;
    }

    /**
     * 判断手指是否触摸到该图形
     *
     */
    private boolean isShapeSelected(MotionEvent event, Shape shape) {
        return shape.getRegion().contains(((int) event.getX()), ((int) event.getY()));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(ShapeUtil.UNIT_SIZE * (SIZE - 1), ShapeUtil.UNIT_SIZE * (SIZE - 1));
    }

    public interface OnVerifyListener {
        void onVerify();
    }
}
