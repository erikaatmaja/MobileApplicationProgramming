package umn.ac.id.week08_27299;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import umn.ac.id.week08_27299.SavedInstanceAndSharedPreferences.Tutorial2Activity;
import umn.ac.id.week08_27299.TextEditorAndStorage.Tutorial1Activity;

public class MainActivity extends AppCompatActivity {
    Button btntutorial1, btntutorial1b, btntutorial2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btntutorial1  = findViewById(R.id.btntutorial1);
        btntutorial1b = findViewById(R.id.btntutorial1b);
        btntutorial2  = findViewById(R.id.btntutorial2);

        btntutorial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Tutorial1Activity.class));
            }
        });
        btntutorial1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Tutorial2Activity.class));
            }
        });
        btntutorial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Tutorial2Activity.class));
            }
        });
    }
}