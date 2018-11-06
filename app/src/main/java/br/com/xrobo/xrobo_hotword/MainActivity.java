package br.com.xrobo.xrobo_hotword;

import android.app.VoiceInteractor;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.service.voice.AlwaysOnHotwordDetector;
import android.service.voice.VoiceInteractionService;
import android.service.voice.VoiceInteractionSession;
import android.service.voice.VoiceInteractionSessionService;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private boolean bindService = false;

    private MyVoiceInteractionService voiceInteractionService;
    private AlwaysOnHotwordDetector hotwordDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Bind to MyVoiceInteractionService
        //final Intent service = new Intent(MainActivity.this, VoiceInteractionService.class);
        //bindService(service, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (hotwordDetector != null) {
            hotwordDetector.stopRecognition();
        }

        // Unbind from the service
        if (bindService) {
            unbindService(conn);// to release the connection.
            bindService = false;
        }
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection conn = new ServiceConnection() {

        /**
         * Called when a connection to the Service has been established, with
         * the {@link IBinder} of the communication channel to the
         * Service.
         *
         * <p class="note"><b>Note:</b> If the system has started to bind your
         * client app to a service, it's possible that your app will never receive
         * this callback. Your app won't receive a callback if there's an issue with
         * the service, such as the service crashing while being created.
         *
         * @param name    The concrete component name of the service that has
         *                been connected.
         * @param service The IBinder of the Service's communication channel,
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected.");

            // We've bound to MyVoiceInteractionService, cast the IBinder and get LocalService instance
            MyVoiceInteractionService.LocalBinder binder = (MyVoiceInteractionService.LocalBinder) service;
            voiceInteractionService = binder.getService();

            bindService = true;

//            hotwordDetector = voiceInteractionService.createAlwaysOnHotwordDetector(
//                    "Hello Android", Locale.US, new MyHotwordDetector());

//            hotwordDetector = voiceInteractionService.createAlwaysOnHotwordDetector(
//                    "Olá Rebeca",
//                    new Locale("pt", "BR"),
//                    new MyHotwordDetector());
//            hotwordDetector.startRecognition(AlwaysOnHotwordDetector.RECOGNITION_FLAG_CAPTURE_TRIGGER_AUDIO);
        }

        /**
         * Called when a connection to the Service has been lost.  This typically
         * happens when the process hosting the service has crashed or been killed.
         * This does <em>not</em> remove the ServiceConnection itself -- this
         * binding to the service will remain active, and you will receive a call
         * to {@link #onServiceConnected} when the Service is next running.
         *
         * @param name The concrete component name of the service whose
         *             connection has been lost.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected.");
            bindService = false;
        }
    };
}
