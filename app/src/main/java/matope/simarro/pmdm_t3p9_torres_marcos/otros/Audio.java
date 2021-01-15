package matope.simarro.pmdm_t3p9_torres_marcos.otros;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import matope.simarro.pmdm_t3p9_torres_marcos.R;

public class Audio extends Service {
    MediaPlayer loop;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getClass().getSimpleName(), "Creating service");
        loop = MediaPlayer.create(this, R.raw.musica);
        loop.setLooping(true);
        loop.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        loop.stop();
        loop.release();
    }
}
