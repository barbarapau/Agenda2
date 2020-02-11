package smile.agenda;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class Colores extends AppCompatActivity {
    private Adapatador_fondo adp;
    private ArrayList<Imagen> lista;
    private GridView grilla;
    private int fondo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_colores);

            grilla =(GridView)findViewById(R.id.grillaColor) ;


            /// RECUPERO LA LISTA DE COLORES QUE TIENEN COMO VALOR EL INT DEL DRAWABLE, Y CONVIERTO ESOS INT EN DRAWABLES PARA PASARLO AL ADAPTADOR
            ArrayList<Integer> l = new Imagen().cargarFondoInteger();
            ArrayList<Drawable> lista = new ArrayList<>();
            for(int i = 0; i<l.size();i++){
                Drawable d =  getResources().getDrawable(l.get(i));
                lista.add(d);
            }

            adp = new Adapatador_fondo(this,lista);
            grilla.setAdapter(adp);
            grilla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    fondo= position;
                    Intent i =new Intent();
                    i.putExtra("color", fondo);
                    setResult(RESULT_OK, i);
                    finish();

                }
            });



        }


}
