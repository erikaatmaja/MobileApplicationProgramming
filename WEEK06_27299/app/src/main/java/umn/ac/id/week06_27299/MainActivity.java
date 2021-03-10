package umn.ac.id.week06_27299;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import umn.ac.id.week06_27299.tutorialDrawable.DrawableActivity;
import umn.ac.id.week06_27299.tutorialFisika.FisikaActivity;
import umn.ac.id.week06_27299.tutorialProperty.PropertyActivity;

public class MainActivity extends AppCompatActivity {

    Button btnProperty, btnDrawable, btnFisika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProperty = findViewById(R.id.btnProperty);
        btnDrawable = findViewById(R.id.btnDrawable);
        btnFisika = findViewById(R.id.btnFisika);

        btnProperty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PropertyActivity.class));
            }
        });

        btnDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DrawableActivity.class));
            }
        });

        btnFisika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FisikaActivity.class));
            }
        });
    }
}