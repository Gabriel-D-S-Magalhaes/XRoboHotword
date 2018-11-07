package br.com.xrobo.xrobo_hotword;

import android.app.Activity;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isVoiceInteraction()) {
            Log.w(TAG, "Not running as a voice interaction!");
            finish();
        }
    }
}
