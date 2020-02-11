package smile.agenda;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import smile.agenda.R;

/**
 * Created by Yo on 18/08/2017.
 */

public class Adapatador_fondo extends BaseAdapter {


        private Context context;
        private ArrayList<Drawable> lista;

        private View fila;
        private TextView fondo;

        public Adapatador_fondo(Context contexts, ArrayList<Drawable> lista) {
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




         /* @param position
         * @param convertView
         * @param parent
         * @return la vista con los datos introducidos
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //estructura
            fila=inflater.inflate(R.layout.adaptador_fondo,null);
            fondo = (TextView)fila.findViewById(R.id.campo);
            fondo.setBackground(lista.get(position));


            return fila;
        }


    }


