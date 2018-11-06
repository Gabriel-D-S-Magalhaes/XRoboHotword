package br.com.xrobo.xrobo_hotword;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.service.voice.VoiceInteractionService;
import android.util.Log;

/**
 * Created by mac on 24/10/18.
 *
 * @author Gabriel D. S. Magalhães
 */
public class MyVoiceInteractionService extends VoiceInteractionService {

    private static final String TAG = MyVoiceInteractionService.class.getSimpleName();

    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind.");
        return mBinder;
    }

    class LocalBinder extends Binder {
        MyVoiceInteractionService getService() {
            return MyVoiceInteractionService.this;
        }
    }
}
