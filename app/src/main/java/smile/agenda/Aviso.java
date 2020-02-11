package smile.agenda;

/**
 * Created by Yo on 31/08/2017.
 */


;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

import smile.agenda.R;

public class Aviso extends Service {
    private static final String TAG = "ser";
    private baseDatos conexion=new baseDatos(this,"notas",null,1);
    private SQLiteDatabase leer;
    private String texto1, texto2=null;
    private NotificationManager nManager;
    int hora;
    Context context;


    //int intervaloCompruebo = 1000*60*60*1; /* 1 horas */


        public Aviso() {
        }

        @Override
        public IBinder onBind(Intent intent) {
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void onCreate() {
           leer =conexion.getReadableDatabase();
            texto1 = getResources().getString(R.string.evento1);


        }



    @Override
    public void onStart(Intent intent, int startId) {
        nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent i = new Intent(this,calendario.class);
        //no abrir una segunda si ya esta abierta
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        final PendingIntent intencion =
                PendingIntent.getActivity(this,0, i,PendingIntent.FLAG_UPDATE_CURRENT);

        new Thread(new Runnable() {

            @Override
            public void run() {

                mostrarEvento(intencion);
            }


        }).start();



    }
public void mostrarEvento(PendingIntent i){
    if(conexion.hayEvento(leer,Calendar.getInstance())==true){
        texto2 = conexion.hayEventoHora(leer,Calendar.getInstance());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getBaseContext())
                .setSmallIcon(R.mipmap.calendario_foreground)
                .setContentTitle(texto1)
                .setContentText(texto2)
                .setWhen(System.currentTimeMillis());
        builder.setContentIntent(i);
        builder.setAutoCancel(true);
        nManager.notify(12345, builder.build());
    }


}
}

