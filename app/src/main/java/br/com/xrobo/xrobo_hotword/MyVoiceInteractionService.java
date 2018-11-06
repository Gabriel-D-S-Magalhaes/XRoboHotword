package br.com.xrobo.xrobo_hotword;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.service.voice.AlwaysOnHotwordDetector;
import android.service.voice.VoiceInteractionService;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Locale;

/**
 * Created by mac on 24/10/18.
 *
 * @author Gabriel D. S. Magalhães
 */
public class MyVoiceInteractionService extends VoiceInteractionService implements Runnable {

    private static final String TAG = MyVoiceInteractionService.class.getSimpleName();

    private final IBinder mBinder = new LocalBinder();

    /**
     * A client is binding to the service with {@link android.content.Context#bindService(Intent, ServiceConnection, int)}
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind.");
        return mBinder;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Log.i(TAG, "run.");
    }

    class LocalBinder extends Binder {
        MyVoiceInteractionService getService() {
            return MyVoiceInteractionService.this;
        }
    }
}
