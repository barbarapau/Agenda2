package smile.agenda;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Yo on 31/08/2017.
 */

public class Notificacion  extends BroadcastReceiver {

    @Override
    public void onReceive(Context contexto, Intent intent) {
        contexto.startService(new Intent(contexto, Aviso.class));
        contexto.stopService(new Intent(contexto, Aviso.class));

    }


    }

