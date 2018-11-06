package br.com.xrobo.xrobo_hotword;

import android.media.AudioFormat;
import android.service.voice.AlwaysOnHotwordDetector;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by mac on 24/10/18.
 *
 * @author Gabriel D. S. Magalhães
 */
public class MyHotwordDetector extends AlwaysOnHotwordDetector.Callback {

    private static final String TAG = MyHotwordDetector.class.getSimpleName();

    /**
     * Called when the hotword availability changes.
     * This indicates a change in the availability of recognition for the given keyphrase.
     * It's called at least once with the initial availability.<p/>
     * <p>
     * Availability implies whether the hardware on this system is capable of listening for
     * the given keyphrase or not. <p/>
     *
     * @param status int
     * @see AlwaysOnHotwordDetector#STATE_HARDWARE_UNAVAILABLE
     * @see AlwaysOnHotwordDetector#STATE_KEYPHRASE_UNSUPPORTED
     * @see AlwaysOnHotwordDetector#STATE_KEYPHRASE_UNENROLLED
     * @see AlwaysOnHotwordDetector#STATE_KEYPHRASE_ENROLLED
     */
    @Override
    public void onAvailabilityChanged(int status) {
    }

    /**
     * Called when the keyphrase is spoken.
     * This implicitly stops listening for the keyphrase once it's detected.
     * Clients should start a recognition again once they are done handling this
     * detection.
     *
     * @param eventPayload Payload data for the detection event.
     *                     This may contain the trigger audio, if requested when calling
     *                     {@link AlwaysOnHotwordDetector#startRecognition(int)}.
     */
    @Override
    public void onDetected(@NonNull AlwaysOnHotwordDetector.EventPayload eventPayload) {
        AudioFormat captureAudioFormat = eventPayload.getCaptureAudioFormat();

        if (eventPayload.getTriggerAudio() != null) {
            String triggerAudio = new String(eventPayload.getTriggerAudio(), 0,
                    eventPayload.getTriggerAudio().length);
            Log.i(TAG, String.format("Trigger audio: %s", triggerAudio));
        }
    }

    /**
     * Called when the detection fails due to an error.
     */
    @Override
    public void onError() {
    }

    /**
     * Called when the recognition is paused temporarily for some reason.
     * This is an informational callback, and the clients shouldn't be doing anything here
     * except showing an indication on their UI if they have to.
     */
    @Override
    public void onRecognitionPaused() {
        Log.i(TAG, "The recognition is paused temporarily for some reason.");
    }

    /**
     * Called when the recognition is resumed after it was temporarily paused.
     * This is an informational callback, and the clients shouldn't be doing anything here
     * except showing an indication on their UI if they have to.
     */
    @Override
    public void onRecognitionResumed() {
        Log.i(TAG, "The recognition is resumed after it was temporarily paused.");
    }
}
