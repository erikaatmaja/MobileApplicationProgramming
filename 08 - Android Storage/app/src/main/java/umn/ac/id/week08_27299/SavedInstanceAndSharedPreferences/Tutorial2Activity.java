package umn.ac.id.week08_27299.SavedInstanceAndSharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import umn.ac.id.week08_27299.R;

public class Tutorial2Activity extends AppCompatActivity {
    private int mCount = 0;
    private int mWarna;
    private TextView tvCounter;
    private Context context;
    private final String COUNTER_KEY = "counter";
    private final String WARNA_KEY = "warna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial2);
        context = this;
        tvCounter = (TextView) findViewById(R.id.tvCounter);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt(COUNTER_KEY);
            if (mCount != 0) {
                tvCounter.setText(String.valueOf(mCount));
            }
            mWarna = savedInstanceState.getInt(WARNA_KEY);
            tvCounter.setBackgroundColor(mWarna);
        }

    }
    public void gantiBackground(View view){
        int warna = ((ColorDrawable)view.getBackground()).getColor();
        mWarna = warna;
        tvCounter.setBackgroundColor(warna);
    }
    public void tambahCounter(View view){
        mCount++;
        tvCounter.setText(String.valueOf(mCount));
    }
    public void reset(View view){
        mCount = 0;
        tvCounter.setText(String.valueOf(mCount));
        mWarna = ContextCompat.getColor(context,R.color.default_background);
        tvCounter.setBackgroundColor(mWarna);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY, mCount);
        outState.putInt(WARNA_KEY, mWarna);
    }
}