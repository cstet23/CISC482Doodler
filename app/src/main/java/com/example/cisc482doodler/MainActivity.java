package com.example.cisc482doodler;

import static java.lang.Math.random;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DoodleView doodleview = findViewById(R.id.doodleView);

        ImageButton brushColor = (ImageButton) findViewById(R.id.brushColor);
        brushColor.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                doodleview.changeColor(Color.RED);
                brushColor.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return true;
            }
        });

        ImageButton brushSize = (ImageButton) findViewById(R.id.brushSize);
        brushSize.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                doodleview.changeSize(5);
                return true;
            }
        });

        ImageButton brushOpacity = (ImageButton) findViewById(R.id.brushOpacity);
        brushOpacity.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                doodleview.changeOpacity(100);
                return true;
            }
        });

        ImageButton clearImage = (ImageButton) findViewById(R.id.clearImage);
        clearImage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                doodleview.clearImage();
                return true;
            }
        });
    }
}