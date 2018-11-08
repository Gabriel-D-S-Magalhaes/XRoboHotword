package br.com.xrobo.xrobo_hotword;

import android.content.Context;
import android.content.Intent;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mac on 08/11/18.
 *
 * @author Gabriel D. S. Magalhães
 */
public class MyVoiceInteractionSession extends VoiceInteractionSession implements View.OnClickListener {

    static final String TAG = MyVoiceInteractionSession.class.getSimpleName();

    Intent mStartIntent;

    View mContentView;

    TextView mText;

    Button mStartButton;

    Button mConfirmButton;

    Button mCompleteButton;

    Button mAbortButton;

    static final int STATE_IDLE = 0;

    static final int STATE_LAUNCHING = 1;

    static final int STATE_CONFIRM = 2;

    static final int STATE_COMMAND = 3;

    static final int STATE_ABORT_VOICE = 4;

    static final int STATE_COMPLETE_VOICE = 5;

    int mState = STATE_IDLE;

    Request mPendingRequest;

    MyVoiceInteractionSession(Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public View onCreateContentView() {
        mContentView = getLayoutInflater().inflate(R.layout.voice_interaction_session, null);
        mText = mContentView.findViewById(R.id.text);
        mStartButton = mContentView.findViewById(R.id.start);
        mStartButton.setOnClickListener(this);
        mConfirmButton = mContentView.findViewById(R.id.confirm);
        mConfirmButton.setOnClickListener(this);
        mCompleteButton = mContentView.findViewById(R.id.complete);
        mCompleteButton.setOnClickListener(this);
        mAbortButton = mContentView.findViewById(R.id.abort);
        mAbortButton.setOnClickListener(this);
        updateState();
        return mContentView;
    }

    private void updateState() {
        mStartButton.setEnabled(mState == STATE_IDLE);
        mConfirmButton.setEnabled(mState == STATE_CONFIRM || mState == STATE_COMMAND);
        mAbortButton.setEnabled(mState == STATE_ABORT_VOICE);
        mCompleteButton.setEnabled(mState == STATE_COMPLETE_VOICE);
    }

    public void onClick(View v) {
        if (v == mStartButton) {
            mState = STATE_LAUNCHING;
            updateState();
            startVoiceActivity(mStartIntent);

        } else if (v == mConfirmButton) {
            if (mState == STATE_CONFIRM) {
                //mPendingRequest.sendConfirmResult(true, null);

            } else {
                //mPendingRequest.sendCommandResult(true, null);

            }
            mPendingRequest = null;
            mState = STATE_IDLE;
            updateState();

        } else if (v == mAbortButton) {
            //mPendingRequest.sendAbortVoiceResult(null);
            mPendingRequest = null;
            mState = STATE_IDLE;
            updateState();

        } else if (v == mCompleteButton) {
            //mPendingRequest.sendCompleteVoiceResult(null);
            mPendingRequest = null;
            mState = STATE_IDLE;
            updateState();
        }
    }

    @Override
    public boolean[] onGetSupportedCommands(String[] commands) {
        return super.onGetSupportedCommands(commands);
    }

    /*@Override
    public void onConfirm(Caller caller, Request request, CharSequence prompt, Bundle extras) {
        Log.i(TAG, "onConfirm: prompt=" + prompt + " extras=" + extras);
        mText.setText(prompt);
        mStartButton.setText("Confirm");
        mPendingRequest = request;
        mState = STATE_CONFIRM;
        updateState();
    }*/

    /*@Override
    public void onCompleteVoice(Caller caller, Request request, CharSequence message, Bundle extras) {
        Log.i(TAG, "onCompleteVoice: message=" + message + " extras=" + extras);
        mText.setText(message);
        mPendingRequest = request;
        mState = STATE_COMPLETE_VOICE;
        updateState();
    }*/

    /*@Override
    public void onAbortVoice(Caller caller, Request request, CharSequence message, Bundle extras) {
        Log.i(TAG, "onAbortVoice: message=" + message + " extras=" + extras);
        mText.setText(message);
        mPendingRequest = request;
        mState = STATE_ABORT_VOICE;
        updateState();
    }*/

    /*@Override
    public void onCommand(Caller caller, Request request, String command, Bundle extras) {
        Log.i(TAG, "onCommand: command=" + command + " extras=" + extras);
        mText.setText("Command: " + command);
        mStartButton.setText("Finish Command");
        mPendingRequest = request;
        mState = STATE_COMMAND;
        updateState();
    }*/

    /*@Override
    public void onCancel(Request request) {
        Log.i(TAG, "onCancel");
        request.cancel();
    }*/
}
