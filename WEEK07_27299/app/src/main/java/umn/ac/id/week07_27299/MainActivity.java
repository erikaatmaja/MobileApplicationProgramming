package umn.ac.id.week07_27299;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import umn.ac.id.week07_27299.camandvid.CamVidActivity;
import umn.ac.id.week07_27299.galery.GalleryActivity;

public class MainActivity extends AppCompatActivity {
    Button btncamvid, btngallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btncamvid = findViewById(R.id.btncamvid);
        btngallery = findViewById(R.id.btngallery);

        btncamvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CamVidActivity.class));
            }
        });

        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GalleryActivity.class));
            }
        });
    }
}