package umn.ac.id.week10_27299;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;

public class SimpleIntentService extends IntentService {
    public SimpleIntentService() {
        super("SimpleIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("INTENTSERVICE", "onHandleIntent: IntentService dimulai !");
        int n =(int)(Math.random()*50)+10;
        try {
            for(int i = 0; i < n; i++) {
                Thread.sleep(200);
                Log.i("INTENTSERVICE", "onHandleIntent: berjalan "+
                        ((int)((100 * i)/(float) n)) + " persen");
            }
            Log.i("INTENTSERVICE", "onHandleIntent: Selesai");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent servIntent = new Intent(this, SimpleIntentService.class);
        startService(servIntent);
    }
    public static class CustomServiceTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onProgressUpdate(Integer... integers) {
            Log.i("CUSTOMSERVICE", "onStartCommand: " + integers[0] + " berjalan " +
                    integers[1] + " persen");
        }

        @Override
        protected void onPostExecute(Integer result) {
            Log.i("CUSTOMSERVICE", "onStartCommand: " + result + " Selesai");
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int startId = integers[0];
            int n = (int) (Math.random() * 50) + 10;
            try {
                for (int i = 0; i < n; i++) {
                    Thread.sleep(200);
                    publishProgress(startId, ((int) ((100 * i) / (float) n)));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return startId;
        }
    }
}
