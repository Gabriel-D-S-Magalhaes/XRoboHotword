package br.com.xrobo.xrobo_hotword;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.service.voice.VoiceInteractionSession;
import android.service.voice.VoiceInteractionSessionService;
import android.util.Log;

/**
 * Created by mac on 06/11/18.
 *
 * @author Gabriel D. S. Magalhães
 */
public class MyVoiceInteractionSessionService extends VoiceInteractionSessionService {

    private static final String TAG = MyVoiceInteractionSessionService.class.getSimpleName();
    private final IBinder binder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind.");
        return binder;
    }

    @Override
    public VoiceInteractionSession onNewSession(Bundle args) {
        return new MyVoiceInteractionSession(this);
    }

    public class LocalBinder extends Binder {
        MyVoiceInteractionSessionService getService() {
            return MyVoiceInteractionSessionService.this;
        }
    }
}
