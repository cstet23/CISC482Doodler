package com.example.cisc482doodler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

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

        ImageButton brushColor = findViewById(R.id.brushColor);
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, doodleview.getColor(), new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                doodleview.setColor(color);
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Log.d("colorCancel", "nevermind");
            }
        });
        brushColor.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                dialog.show();
                return true;
            }
        });

        ImageButton brushSize = findViewById(R.id.brushSize);
        brushSize.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    PopupMenu popupMenu = new PopupMenu(MainActivity.this, brushSize);

                    // Inflating popup menu from brush_size_menu.xml file
                    popupMenu.getMenuInflater().inflate(R.menu.brush_size_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            doodleview.setSize(Objects.requireNonNull(menuItem.getTitle()).length());
                            return true;
                        }
                    });
                    // Showing the popup menu
                    popupMenu.show();
                    return true;
                }
                return false;
            }
        });

        ImageButton brushOpacity = findViewById(R.id.brushOpacity);
        brushOpacity.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    LayoutInflater inflater = (LayoutInflater)
                            getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.opacity_popup, null);
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    boolean focusable = true; // lets taps outside the popup also dismiss it
                    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                    popupWindow.showAsDropDown(brushOpacity);
                    SeekBar sk = popupView.findViewById(R.id.seekBar);
                    sk.setProgress(doodleview.getOpacity()*100/255);
                    sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            doodleview.setOpacity(seekBar.getProgress() * 255 / 100);
                        }
                    });
                    return true;
                }
                return false;
            }
        });

        ImageButton clearImage = findViewById(R.id.clearImage);
        clearImage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                doodleview.clearImage();
                return true;
            }
        });
    }
}