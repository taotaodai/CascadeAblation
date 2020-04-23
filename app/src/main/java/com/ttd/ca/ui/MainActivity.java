package com.ttd.ca.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.debug.hv.ViewServer;
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
    private Button btnLast;
    private Button btnNext;

    private List<Shape> shapes;
    private List<Shape> targetShapes;
    private int index;
    private BaseQuestion[] questions = {new Question1(),new Question2()};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewServer.get(this).addWindow(this);

        setContentView(R.layout.activity_main);
        mv = (MatrixView) findViewById(R.id.mv_main);
        ts = (TargetShape) findViewById(R.id.ts_main);
        btnReset = (Button) findViewById(R.id.btn_reset);
        btnLast = (Button) findViewById(R.id.btn_last);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViews();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
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
