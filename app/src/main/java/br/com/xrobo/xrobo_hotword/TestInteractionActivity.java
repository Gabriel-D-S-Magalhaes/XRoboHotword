package br.com.xrobo.xrobo_hotword;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by mac on 07/11/18.
 *
 * @author Gabriel D. S. Magalhães
 */
public class TestInteractionActivity extends Activity {

    private static final String TAG = TestInteractionActivity.class.getSimpleName();

    private VoiceInteractor voiceInteractor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isVoiceInteraction()) {
            Log.w(TAG, "Not running as a voice interaction!");
            finish();
        }

        setContentView(R.layout.test_interaction);

        VoiceInteractor.ConfirmationRequest req;
        req = new VoiceInteractor.ConfirmationRequest(
                new VoiceInteractor.Prompt("This is a confimation"), null) {
            @Override
            public void onCancel() {
                Log.i(TAG, "Canceled!");
                getActivity().finish();
            }

            @Override
            public void onConfirmationResult(boolean confirmed, Bundle result) {
                Log.i(TAG, "Confirmation result: confirmed=" + confirmed + " result=" + result);
                getActivity().finish();
            }
        };

        voiceInteractor.submitRequest(req);
    }
}
