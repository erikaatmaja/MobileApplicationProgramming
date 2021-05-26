package umn.ac.id.week11_27299;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvPostLists;
    PostAdapter adapter;
    Helper helper;

    ArrayList<PostModel> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPostLists = findViewById(R.id.rvPostLists);
        rvPostLists.setLayoutManager(new LinearLayoutManager(this));

        helper = ApiClient.getClient().create(Helper.class);

        getData();
    }

    public void getData() {
        Call<ArrayList<PostModel>> postsCallback = helper.getPosts();
        postsCallback.enqueue(new Callback<ArrayList<PostModel>>() {

            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                posts = response.body();
                Log.d("onResponse", "success");

                adapter = new PostAdapter(posts);
                rvPostLists.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                Log.d("onFailure", "err");
                Toast.makeText(MainActivity.this, "Internet not available", Toast.LENGTH_LONG).show();
            }
        });
    }
}