package br.com.xrobo.xrobo_hotword;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.service.voice.AlwaysOnHotwordDetector;
import android.service.voice.VoiceInteractionService;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;

import java.util.Locale;

/**
 * Created by mac on 24/10/18.
 *
 * @author Gabriel D. S. Magalhães
 */
public class MyVoiceInteractionService extends VoiceInteractionService {

    private static final String TAG = MyVoiceInteractionService.class.getSimpleName();

    private final IBinder mBinder = new LocalBinder();
    private AlwaysOnHotwordDetector alwaysOnHotwordDetector;

    /**
     * A client is binding to the service with {@link android.content.Context#bindService(Intent, ServiceConnection, int)}
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind.");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle args = new Bundle();
        args.putParcelable("intent", new Intent(this, TestInteractionActivity.class));
        showSession(args, VoiceInteractionSession.SHOW_WITH_ASSIST);// TODO: Verificar
        stopSelf(startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onReady() {
        super.onReady();
        this.alwaysOnHotwordDetector = super.createAlwaysOnHotwordDetector("Hello Android",
                Locale.US, new MyHotwordDetector());
    }

    class LocalBinder extends Binder {
        MyVoiceInteractionService getService() {
            return MyVoiceInteractionService.this;
        }
    }
}