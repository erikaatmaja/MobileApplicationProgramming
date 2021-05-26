package umn.ac.id.week10_27299;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnAsync, btnAsyncLoader, btnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAsync = findViewById(R.id.btnAsync);
        btnAsyncLoader = findViewById(R.id.btnAsyncLoader);
        btnIntent = findViewById(R.id.btnIntent);

        btnAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsyncActivity.class));
            }
        });

        btnAsyncLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AsyncTaskLoaderActivity.class));
            }
        });

        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IntentServiceActivity.class));
            }
        });
    }
}