package smile.agenda;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import smile.agenda.R;

/**
 * Created by Yo on 18/08/2017.
 */

public class adapatador_eventos extends BaseAdapter {


        private Context context;
        private ArrayList<Evento> lista;

    private View fila, filaM;
    private int posiSele;
    private ImageButton editar,borrar, editarM,borrarM,guardarM;
    private boolean pulsado;
    private TextView texto,textoHora, textoM;

    private SQLiteDatabase escribir;
    private baseDatos bd;
    private int positionM;
   // private Drawable linea;
    private Intent intent;
    private ImageView imgAdap;
    private FloatingActionButton btnFlo;

private ArrayList<Imagen> listadoImagenes;


        public adapatador_eventos(Context contexts, ArrayList<Evento> lista, Intent intent,
                                  SQLiteDatabase escribir, baseDatos bd,ArrayList<Imagen> listadoImagenes, FloatingActionButton btnFlo ) {
            this.context=contexts;
            this.lista=lista;
           this.intent= intent;
            //this.linea=linea;
            this.listadoImagenes= listadoImagenes;
            this.escribir=escribir;
            this.bd=bd;
            this.btnFlo =btnFlo;

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


            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //estructura
            fila = inflater.inflate(R.layout.adp, null);
            borrar = (ImageButton) fila.findViewById(R.id.btnBorrar);
            editar =(ImageButton)fila.findViewById(R.id.btnEditar);
            texto = (TextView) fila.findViewById(R.id.tx);
            imgAdap =(ImageView)fila.findViewById(R.id.imagenAdaptador);
            textoHora =(TextView)fila.findViewById(R.id.txtHora) ;



            String min="";
            texto.setText(lista.get(position).getTexto());


            if(lista.get(position).getFecha().get(Calendar.MINUTE)<10){
                min="0"+lista.get(position).getFecha().get(Calendar.MINUTE);
            }

            else{
                min=String.valueOf(lista.get(position).getFecha().get(Calendar.MINUTE));
            }

            textoHora.setText(lista.get(position).getFecha().get(Calendar.HOUR_OF_DAY)+":"+min);
            int idImagen =lista.get(position).getImagen()-1;
            if(idImagen<0){
                imgAdap.setVisibility(View.INVISIBLE);
            }
            else{
                imgAdap.setVisibility(View.VISIBLE);
                imgAdap.setImageDrawable(listadoImagenes.get(idImagen).getImagBoton());
            }
           //

             borrar.setVisibility(View.INVISIBLE);
            editar.setVisibility(View.INVISIBLE);

            if (pulsado==true) {
             if (posiSele == position) {

            fila.setBackgroundColor(Color.parseColor("#CFD8DC"));

                    borrar.setVisibility(View.VISIBLE);
                    editar.setVisibility(View.VISIBLE);
                 btnFlo.hide();

                    borrarM=borrar;
                    editarM=editar;
                    textoM= texto;
                    positionM=position;
                    filaM=fila;





                editar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        borrarM.setVisibility(View.INVISIBLE);
                        editarM.setVisibility(View.INVISIBLE);
                        int id=lista.get(positionM).getId();
                       Calendar f =lista.get(positionM).getFecha();
                        intent.putExtra("imagen",lista.get(positionM).getImagen());
                        intent.putExtra("texto",lista.get(position).getTexto());
                        intent.putExtra("id",id);
                        intent.putExtra("feD",f.get(Calendar.DAY_OF_MONTH));
                        intent.putExtra("feM",f.get(Calendar.MONTH));
                        intent.putExtra("feA",f.get(Calendar.YEAR));
                        intent.putExtra("feH",f.get(Calendar.HOUR_OF_DAY));
                        intent.putExtra("feMe",f.get(Calendar.MINUTE));
                        intent.putExtra("fondo",lista.get(positionM).getFondo());
                        context.startActivity(intent);
                        pulsado=false;


                    }
                });
                borrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bd.borrarEvento(escribir, lista.get(positionM).getId());
                        lista.get(position).setTexto("");

                        //filaM.setBackground(linea);
                        borrarM.setVisibility(View.INVISIBLE);
                        editarM.setVisibility(View.INVISIBLE);
                    }
                });

                    pulsado=false;
            }

            }

            return fila;
        }
        }



