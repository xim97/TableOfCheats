package efimovich.bsu.by.tableofcheats.Services;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import efimovich.bsu.by.tableofcheats.R;

public class MusicService extends Service {
    MediaPlayer ambientMediaPlayer;
    final String LOG_TAG = "Music Service";

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "on create music service");
        ambientMediaPlayer = MediaPlayer.create(this, R.raw.track);
        ambientMediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "on start command music service");
        ambientMediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "on destroy music service");
        ambientMediaPlayer.stop();
    }
}
