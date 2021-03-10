package umn.ac.id.week03_27299;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAdd;
    RecyclerView rvList;
    //ini untuk konekin dengan adapter
    DummyAdapter adapter;

    List<String> dummyLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data dalam array
        for (int i=0; i<21; i++){
            dummyLists.add("Word " + (i+1));
        }
        //tag untuk search
        Log.d("MainActivity", dummyLists.toString());

        fabAdd = findViewById(R.id.fabAdd);
        rvList = findViewById(R.id.rvList);

        adapter = new DummyAdapter(MainActivity.this, dummyLists);
        rvList.setAdapter(adapter); //udah konek recycleview sama adapter

        //kasih tau tampilannya akan sprtti apa, skrg coba layoutnya linear
        rvList.setLayoutManager(new LinearLayoutManager(this));



        rvList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dummyLists.add("Word " +(dummyLists.size()+1));
                //kasih tau ke adapter juga kalo data nambah!
                adapter.notifyDataSetChanged();
                Log.d("MainActivity", dummyLists.get(dummyLists.size() - 1));

                //v punya fabadd, nanti dia cari parentnya dia
                //bisa juga tulisnya MainActivity.this
                Toast.makeText(v.getContext(), "New data added successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}