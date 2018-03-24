package com.ttd.ca.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ttd.ca.R;
import com.ttd.ca.utils.ShapeUtil;
import com.ttd.ca.view.TargetShape;
import com.ttd.ca.questionbank.BaseQuestion;
import com.ttd.ca.questionbank.Question1;
import com.ttd.ca.questionbank.Question2;
import com.ttd.ca.view.MatrixView;
import com.ttd.ca.view.shape.Shape;

import java.util.List;

/**
 * Created by wt on 2018/3/12.
 */

public class MainActivity extends AppCompatActivity {
    private MatrixView mv;
    private TargetShape ts;
    private Button btnReset;
    private Button btnVerify;
    private Button btnLast;
    private Button btnNext;

    private List<Shape> shapes;
    private List<Shape> targetShapes;
    private int index;
    private BaseQuestion[] questions = {new Question1(),new Question2()};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mv = (MatrixView) findViewById(R.id.mv_main);
        ts = (TargetShape) findViewById(R.id.ts_main);
        btnReset = (Button) findViewById(R.id.btn_reset);
        btnVerify = (Button) findViewById(R.id.btn_verify);
        btnLast = (Button) findViewById(R.id.btn_last);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViews();
            }
        });
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index >= questions.length - 1){
                    index --;
                    initViews();
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index <= questions.length - 1){
                    index ++;
                    initViews();
                }
            }
        });

        initViews();
    }

    private void initViews() {
        targetShapes = questions[index].getTargetShapes();
        shapes = questions[index].getShapes();

        ts.setShapes(targetShapes);

        mv.setShapes(shapes);
        mv.setOnVerifyListener(new MatrixView.OnVerifyListener() {
            @Override
            public void onVerify() {
                if (ShapeUtil.verifyShapes(targetShapes, shapes)) {
                    Toast.makeText(MainActivity.this, "OK的", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
