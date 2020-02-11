package smile.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class animacion extends AppCompatActivity {

    private final int DURACION_SPLASH = 500; // 1 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacion);

        //se abre la animacion
        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(animacion.this, calendario.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);

    }
}
