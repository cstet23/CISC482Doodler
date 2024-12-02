package com.example.cisc482doodler;

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

import top.defaults.colorpicker.ColorPickerPopup;

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
                //doodleview.changeColor(Color.RED);
                //brushColor.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                new ColorPickerPopup.Builder(MainActivity.this).initialColor(
                    Color.RED)
                        .enableBrightness(true) // color brightness slider
                        .enableAlpha(false) // alpha slider
                        .okTitle("Pick Color") // accept button
                        .cancelTitle("Cancel") // close button
                        .showIndicator(true) // chosen color
                        .showValue(true) // selected hex code
                    .build().show(v,new ColorPickerPopup.ColorPickerObserver() {
                        @Override
                        public void
                        onColorPicked(int color) {
                            // set returned color
                            doodleview.setColor(color);

                            // set button bg
                            brushColor.setBackgroundTintList(ColorStateList.valueOf(color));
                        }
                });
                return true;
            }

        });

        ImageButton brushSize = (ImageButton) findViewById(R.id.brushSize);
        brushSize.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                doodleview.setSize(5);
                return true;
            }
        });

        ImageButton brushOpacity = (ImageButton) findViewById(R.id.brushOpacity);
        brushOpacity.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                doodleview.setOpacity(100);
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