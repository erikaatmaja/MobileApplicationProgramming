package umn.ac.id.week11_27299;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Helper {
    @GET("posts")
    Call<ArrayList<PostModel>> getPosts();
}
