package smile.agenda;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import smile.agenda.R;

/**
 * Created by Yo on 18/08/2017.
 */

public class Adapatador_imagenes extends BaseAdapter {


        private Context context;
        private ArrayList<Imagen> lista;
        private ImageView sele;
        private View fila;
    private int posiSele;
    private ImageView img;
    private boolean pulsado;
    private LinearLayout campo;

        public Adapatador_imagenes(Context contexts, ArrayList<Imagen> lista) {
            this.context=contexts;
            this.lista = lista;
        }



    /**
         *
         * @return el numero de elementos del array
         */
        @Override
        public int getCount() {
            return lista.size();
        }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }
    /**
         *
         * @param position
         * @return el objeto tarea que ocupara la posicion introducida como parametro
         */


        /**
         *
         * @param position
         * @return Devuelve la posicion introducida como parametro
         */
        @Override
        public long getItemId(int position) {

            return position;
        }

    public void sele(int posicion){
        pulsado=true;
        posiSele=posicion;


}


         /* @param position
         * @param convertView
         * @param parent
         * @return la vista con los datos introducidos
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //estructura
            fila=inflater.inflate(R.layout.adaptador_imagenes,null);
            img = (ImageView)fila.findViewById(R.id.image_adaptador);
            img.setImageDrawable(lista.get(position).getImagBoton());

            campo =(LinearLayout)fila.findViewById(R.id.cuadrado);

            if(pulsado==true){
                if(posiSele==position){

                    campo.setBackgroundColor(Color.parseColor("#72797C"));
                }
            }
            return fila;
        }


    }


