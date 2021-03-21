package umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.MainActivity;
import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.R;
import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.music.MusicActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnlogin;
    ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnlogin = findViewById(R.id.btnlogin);
        btnback  = findViewById(R.id.btnback);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("uasmobile") &&
                        password.getText().toString().equals("uasmobilegenap")) {
                    startActivity(new Intent(LoginActivity.this, MusicActivity.class));
//                    openDialog();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
//    public void openDialog(){
//
//    }
}