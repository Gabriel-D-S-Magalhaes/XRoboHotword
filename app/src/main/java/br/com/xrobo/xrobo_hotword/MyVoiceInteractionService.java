package br.com.xrobo.xrobo_hotword;

import android.content.Intent;
import android.os.Bundle;
import android.service.voice.AlwaysOnHotwordDetector;
import android.service.voice.AlwaysOnHotwordDetector.Callback;
import android.service.voice.AlwaysOnHotwordDetector.EventPayload;
import android.service.voice.VoiceInteractionService;
import android.service.voice.VoiceInteractionSession;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Locale;

/**
 * Created by mac on 24/10/18.
 *
 * @author Gabriel D. S. Magalhães
 */
public class MyVoiceInteractionService extends VoiceInteractionService {

    private static final String TAG = MyVoiceInteractionService.class.getSimpleName();

    private final Callback mHotwordCallback = new Callback() {
        @Override
        public void onAvailabilityChanged(int status) {
            Log.i(TAG, "onAvailabilityChanged(" + status + ")");
            hotwordAvailabilityChangeHelper(status);
        }

        @Override
        public void onDetected(@NonNull EventPayload eventPayload) {
            Log.i(TAG, "onDetected");
        }

        @Override
        public void onError() {
            Log.i(TAG, "onError");
        }

        @Override
        public void onRecognitionPaused() {
            Log.i(TAG, "onRecognitionPaused");
        }

        @Override
        public void onRecognitionResumed() {
            Log.i(TAG, "onRecognitionResumed");
        }
    };

    private AlwaysOnHotwordDetector alwaysOnHotwordDetector;

    @Override
    public void onReady() {
        super.onReady();

        Log.i(TAG, "Creating " + this);

        this.alwaysOnHotwordDetector = createAlwaysOnHotwordDetector("Hello Android",
                Locale.US, mHotwordCallback);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand.");
        Bundle args = new Bundle();
        args.putParcelable("intent", new Intent(this, TestInteractionActivity.class));
        //showSession(args, VoiceInteractionSession.SHOW_WITH_SCREENSHOT);// TODO: Verificar
        stopSelf(startId);
        return START_NOT_STICKY;
    }

    private void hotwordAvailabilityChangeHelper(int availability) {
        Log.i(TAG, "Hotword availability = " + availability);
        switch (availability) {
            case AlwaysOnHotwordDetector.STATE_HARDWARE_UNAVAILABLE:
                Log.i(TAG, "STATE_HARDWARE_UNAVAILABLE");
                break;
            case AlwaysOnHotwordDetector.STATE_KEYPHRASE_UNSUPPORTED:
                Log.i(TAG, "STATE_KEYPHRASE_UNSUPPORTED");
                break;
            case AlwaysOnHotwordDetector.STATE_KEYPHRASE_UNENROLLED:
                Log.i(TAG, "STATE_KEYPHRASE_UNENROLLED");
                Intent enroll = alwaysOnHotwordDetector.createEnrollIntent();
                Log.i(TAG, "Need to enroll with " + enroll);
                break;
            case AlwaysOnHotwordDetector.STATE_KEYPHRASE_ENROLLED:
                Log.i(TAG, "STATE_KEYPHRASE_ENROLLED - starting recognition");
                if (alwaysOnHotwordDetector.startRecognition(
                        AlwaysOnHotwordDetector.RECOGNITION_FLAG_CAPTURE_TRIGGER_AUDIO)) {
                    Log.i(TAG, "startRecognition succeeded");
                } else {
                    Log.i(TAG, "startRecognition failed");
                }
                break;
        }
    }
}