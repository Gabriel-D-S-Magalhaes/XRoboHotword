package br.com.xrobo.xrobo_hotword;

import android.content.Intent;
import android.speech.RecognitionService;
import android.util.Log;

/**
 * Created by mac on 06/11/18.
 *
 * @author Gabriel D. S. Magalhães
 */
public class MyRecognitionService extends RecognitionService {

    private static final String TAG = MyRecognitionService.class.getSimpleName();

    /**
     * Notifies the service that it should start listening for speech.
     *
     * @param recognizerIntent contains parameters for the recognition to be performed. The intent
     *                         may also contain optional extras, see
     *                         {@link android.speech.RecognizerIntent}. If these values are
     *                         not set explicitly, default values should be used by the recognizer.
     * @param listener         that will receive the service's callbacks
     */
    @Override
    protected void onStartListening(Intent recognizerIntent, Callback listener) {
        Log.i(TAG, "Start listening for speech.");
    }

    /**
     * Notifies the service that it should cancel the speech recognition.
     *
     * @param listener {@link RecognitionService.Callback}
     */
    @Override
    protected void onCancel(Callback listener) {
        Log.i(TAG, "Cancel the speech recognition.");
    }

    /**
     * Notifies the service that it should stop listening for speech. Speech captured so far should
     * be recognized as if the user had stopped speaking at this point. This method is only called
     * if the application calls it explicitly.
     *
     * @param listener {@link RecognitionService.Callback}
     */
    @Override
    protected void onStopListening(Callback listener) {
        Log.i(TAG, "Stop listening for speech.");
    }
}
