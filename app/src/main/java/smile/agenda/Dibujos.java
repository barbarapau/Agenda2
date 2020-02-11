package smile.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class Dibujos extends AppCompatActivity   {
        private Adapatador_imagenes adp;
        private ArrayList<Imagen> lista;
        private GridView grilla;
        private int imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imagenes);

        grilla =(GridView)findViewById(R.id.grilla) ;


        /// RECUPERO LA LISTA DE IMAGENES QUE TIENEN COMO VALOR EL INT DEL DRAWABLE, Y CONVIERTO ESOS INT EN DRAWABLES PARA PASARLO AL ADAPTADOR
        ArrayList<Imagen> l = new Imagen().listaInteger();
        ArrayList<Imagen> lista = new ArrayList<>();
        for(int i = 0; i<l.size();i++){
            Imagen im = new Imagen(i+1, getResources().getDrawable(l.get(i).getInt_imagLista()), getResources().getDrawable(l.get(i).getInt_imagBoton()));
            lista.add(im);
        }

        adp = new Adapatador_imagenes(this,lista);
        grilla.setAdapter(adp);
        grilla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imagen= position+1;
                Intent i =new Intent();
                i.putExtra("imagen", imagen);
                setResult(RESULT_OK, i);
                finish();

            }
        });



    }
}
