package umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.MainActivity;
import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.R;
import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.profile.ProfileActivity;

import static umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.music.MusicActivity.repeatbool;
import static umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.music.MusicActivity.shufflebool;
import static umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.music.MusicActivity.songFiles;

public class SongDetailActivity extends AppCompatActivity {
    TextView title, artist, durationStart, durationTotal;
    ImageView thumbnailLagu;
    ImageButton btnplayPause, btnnext, btnprev, btnshuffle, btnrepeat, btnback;
    SeekBar seekBar;

    int pos = -1;
    ArrayList<SongFiles> songList = new ArrayList<>();
    static Uri uri;
    static MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Thread playThread, nextThread, prevThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SongDetailActivity.this, MusicActivity.class));
            }
        });

        initVariable();
        getIntentMethod();
        title.setText(songList.get(pos).getTitle());
        artist.setText(songList.get(pos).getArtist());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SongDetailActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null)
                {
                    int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrPos);
                    durationStart.setText(formattedTime(mCurrPos));
                }
                handler.postDelayed(this, 1000);
            }
        });
        btnshuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shufflebool){
                    shufflebool = false;
                    btnshuffle.setImageResource(R.drawable.shuffle);
                }else {
                    shufflebool = true;
                    btnshuffle.setImageResource(R.drawable.shuffleon);
                }
            }
        });
        btnrepeat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(repeatbool){
                    repeatbool = false;
                    btnrepeat.setImageResource(R.drawable.repeat);
                } else {
                    repeatbool = true;
                    btnrepeat.setImageResource(R.drawable.repeaton);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        btnplayThread();
        btnnextThread();
        btnprevThread();
        super.onResume();
    }

    private void btnplayThread() {
        playThread = new Thread(){
            @Override
            public void run() {
                super.run();
                btnplayPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnplayPauseClicked();
                    }
                });
            }
        };
        playThread.start();
    }

    private void btnplayPauseClicked(){
        if (mediaPlayer.isPlaying()){
            btnplayPause.setImageResource(R.drawable.play);
            mediaPlayer.pause();
            seekBar.setMax(mediaPlayer.getDuration()/1000);

            SongDetailActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        }else{
            btnplayPause.setImageResource(R.drawable.pause);
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration()/1000);

            SongDetailActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        }
    }

    private void btnnextThread() {
        nextThread = new Thread(){
            @Override
            public void run() {
                super.run();
                btnnext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnnextClicked();
                    }
                });
            }
        };
        nextThread.start();
    }

    private void btnnextClicked() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();

            if (shufflebool && !repeatbool){
                pos = getRandom(songList.size() - 1);
            } else if(!shufflebool && !repeatbool){
                pos = ((pos + 1) %songList.size());
            }

            pos = ((pos + 1) %songList.size());
            uri = Uri.parse(songList.get(pos).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            title.setText(songList.get(pos).getTitle());
            artist.setText(songList.get(pos).getArtist());
            seekBar.setMax(mediaPlayer.getDuration()/1000);

            SongDetailActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            btnplayPause.setImageResource(R.drawable.pause);
            mediaPlayer.start();
        }else {
            mediaPlayer.stop();
            mediaPlayer.release();
            if (shufflebool && !repeatbool){
                pos = getRandom(songList.size() - 1);
            } else if(!shufflebool && !repeatbool){
                pos = ((pos + 1) %songList.size());
            }
            uri = Uri.parse(songList.get(pos).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            title.setText(songList.get(pos).getTitle());
            artist.setText(songList.get(pos).getArtist());
            seekBar.setMax(mediaPlayer.getDuration()/1000);

            SongDetailActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            btnplayPause.setImageResource(R.drawable.play);
        }
    }

    private int getRandom(int i) {
        Random random = new Random();
        return random.nextInt(i+1);
    }

    private void btnprevThread() {
        prevThread = new Thread(){
            @Override
            public void run() {
                super.run();
                btnprev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnprevClicked();
                    }
                });
            }
        };
        prevThread.start();
    }

    private void btnprevClicked() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            if (shufflebool && !repeatbool){
                pos = getRandom(songList.size() - 1);
            } else if(!shufflebool && !repeatbool){
                pos = ((pos - 1) < 0 ? (songList.size() -1) : (pos - 1));
            }
            uri = Uri.parse(songList.get(pos).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            title.setText(songList.get(pos).getTitle());
            artist.setText(songList.get(pos).getArtist());
            seekBar.setMax(mediaPlayer.getDuration()/1000);

            SongDetailActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            btnplayPause.setImageResource(R.drawable.pause);
            mediaPlayer.start();
        }else {
            mediaPlayer.stop();
            mediaPlayer.release();
            if (shufflebool && !repeatbool){
                pos = getRandom(songList.size() - 1);
            } else if(!shufflebool && !repeatbool){
                pos = ((pos - 1) < 0 ? (songList.size() -1) : (pos - 1));
            }
            uri = Uri.parse(songList.get(pos).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            title.setText(songList.get(pos).getTitle());
            artist.setText(songList.get(pos).getArtist());
            seekBar.setMax(mediaPlayer.getDuration()/1000);

            SongDetailActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            btnplayPause.setImageResource(R.drawable.play);
        }
    }

    private String formattedTime(int mCurrPos)
    {
        String totalout = "";
        String totalNew = "";
        String seconds = String.valueOf(mCurrPos % 60);
        String minutes = String.valueOf(mCurrPos / 60);
        totalout = minutes + ":" + seconds;
        totalNew = minutes + ":" + "0" + seconds;
        if(seconds.length() == 1)
        {
            return totalNew;
        }
        else {
            return totalout;
        }
    }

    private void getIntentMethod() {
        pos = getIntent().getIntExtra("position", -1);
        songList = songFiles;

        if(songList != null)
        {
            btnplayPause.setImageResource(R.drawable.pause);
            uri = Uri.parse(songList.get(pos).getPath());
        }
        if(mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        else
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        seekBar.setMax(mediaPlayer.getDuration() / 1000);
        metaData(uri);
    }

    private void initVariable()
    {
        title = findViewById(R.id.tvJudul);
        artist = findViewById(R.id.tvArtist);
        durationStart = findViewById(R.id.durationstart);
        durationTotal = findViewById(R.id.durationtotal);
        thumbnailLagu = findViewById(R.id.thumbnailDetail);
        btnnext = findViewById(R.id.btnnext);
        btnprev = findViewById(R.id.btnprev);
        btnshuffle = findViewById(R.id.btnshuffle);
        btnrepeat = findViewById(R.id.btnrepeat);
        btnplayPause = findViewById(R.id.btnplaypause);
        seekBar = findViewById(R.id.seekBar);
    }

    private void metaData(Uri uri)
    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri.toString());
        int duration_total = Integer.parseInt(songList.get(pos).getDuration()) / 1000;
        durationTotal.setText(formattedTime(duration_total));
        byte[] art = retriever.getEmbeddedPicture();
        if(art != null){
            Glide.with(this).asBitmap().load(art).into(thumbnailLagu);
        }
        else{
            Glide.with(this).asBitmap().load(R.drawable.thumbnail).into(thumbnailLagu);
        }
    }
}