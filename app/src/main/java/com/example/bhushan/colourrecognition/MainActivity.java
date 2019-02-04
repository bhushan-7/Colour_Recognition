package com.example.bhushan.colourrecognition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imagecam;
    Bitmap btmp;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btncam =(Button) findViewById(R.id.cam_button);
        imagecam =(ImageView) findViewById(R.id.cam_image);
        textView=(TextView) findViewById(R.id.rbg);

        imagecam.setDrawingCacheEnabled(true);
        imagecam.buildDrawingCache(true);

        btncam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap =(Bitmap)data.getExtras().get("data");
        imagecam.setImageBitmap(bitmap);

        imagecam.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_MOVE || event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    btmp=imagecam.getDrawingCache();
                    int pixel =btmp.getPixel((int) event.getX(),(int) event.getY());

                    int r = Color.red(pixel);
                    int g =Color.green(pixel);
                    int b =Color.blue(pixel);

                    String hex = String.format("#%02x%02x%02x", r, g, b);



                    /////   textView.setBackground(Color.rgb(r,g,b));
                    textView.setText("RGB value : "+"( "+r+" , "+g+" , "+b+" )\n"+"Colour code : "+hex  );

                }

                return true;
            }
        });


    }




}
