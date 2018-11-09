package br.com.xrobo.xrobo_hotword;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private boolean bindService = false;

    private MyVoiceInteractionService voiceInteractionService;

    private Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new Intent(MainActivity.this, MyVoiceInteractionService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Geralmente, alterna-se entre a vinculação e a desvinculação durante os momentos
        // crescentes e decrescentes do ciclo de vida do cliente. Por exemplo:
        // Se precisar interagir com o serviço enquanto a atividade estiver visível, será necessário
        // vincular durante onStart() e desvincular durante onStop().

        // Bind to MyVoiceInteractionService
        bindService(service, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

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
         * O sistema chama isso para entregar o IBinder retornado pelo método onBind() do serviço.
         *
         * @param name    The concrete component name of the service that has
         *                been connected.
         * @param service The IBinder of the Service's communication channel,
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected.");


            // We've bound to MyVoiceInteractionService, cast the IBinder and get LocalService instance
            //MyVoiceInteractionService.LocalBinder binder = (MyVoiceInteractionService.LocalBinder) service;
            //voiceInteractionService = binder.getService();

            bindService = true;
        }

        /**
         * Called when a connection to the Service has been lost.  This typically
         * happens when the process hosting the service has crashed or been killed.
         * This does <em>not</em> remove the ServiceConnection itself -- this
         * binding to the service will remain active, and you will receive a call
         * to {@link #onServiceConnected} when the Service is next running.
         *
         * O sistema Android chama isso quando a conexão ao serviço é perdida inesperadamente, como
         * quando o serviço apresenta problemas ou é fechado de forma repentina. Isso não é chamado
         * quando o cliente desfaz o vínculo.
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
