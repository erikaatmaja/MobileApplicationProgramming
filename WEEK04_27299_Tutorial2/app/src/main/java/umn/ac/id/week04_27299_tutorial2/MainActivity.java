package umn.ac.id.week04_27299_tutorial2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etIsian;
    private Button btnHalaman1, btnHalaman2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etIsian = findViewById(R.id.isian);
        btnHalaman1 = findViewById(R.id.main_button_change1);
        btnHalaman2 = findViewById(R.id.main_button_change2);

        btnHalaman1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainDua = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intentMainDua);
            }
        });

        btnHalaman2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentMainTiga = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intentMainTiga);
            }
        });
    }
}