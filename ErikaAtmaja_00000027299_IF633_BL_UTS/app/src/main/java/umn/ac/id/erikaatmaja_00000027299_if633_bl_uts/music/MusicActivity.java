package umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;

import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.MainActivity;
import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.R;
import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.profile.ProfileActivity;

public class MusicActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {
    RecyclerView rvDaftarMusic;
    SongAdapter songfilesAdapter;
    static ArrayList<SongFiles> songFiles;
    static boolean shufflebool = false, repeatbool = false;

    Button btnpopup;
    ImageButton btndropdown;

    public static ArrayList<SongFiles> getAllAudio(Context context) {
        ArrayList<SongFiles> daftarMusicTemp = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA //for path
        };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                String title = cursor.getString(0);
                String artist = cursor.getString(1);
                String album = cursor.getString(2);
                String duration = cursor.getString(3);
                String path = cursor.getString(4);

                SongFiles songfiles = new SongFiles(path, title, artist, duration, album);
                Log.e("Path : " + path, " Album : " + album);
                daftarMusicTemp.add(songfiles);
            }
            cursor.close();
        }
        return daftarMusicTemp;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        songFiles = getAllAudio(this);

        rvDaftarMusic = (RecyclerView) findViewById(R.id.recyclerview);
        songfilesAdapter = new SongAdapter(this, songFiles);

        rvDaftarMusic.setAdapter(songfilesAdapter);
        rvDaftarMusic.setLayoutManager(new LinearLayoutManager(this));

        btnpopup = findViewById(R.id.btnlogin);

        DialogPopup dialogWelcome = new DialogPopup();
        dialogWelcome.show(getSupportFragmentManager(), "welcome message");

        btndropdown = findViewById(R.id.dropdown);
        btndropdown.setOnClickListener(new View.OnClickListener() {
            @Override //popup pada dropdown
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MusicActivity.this, v);
                popup.inflate(R.menu.popup_menu);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profiledropdown:
                                startActivity(new Intent(MusicActivity.this, ProfileActivity.class));
                                return true;
                            case R.id.logoutdropdown:
                                startActivity(new Intent(MusicActivity.this, MainActivity.class));
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}