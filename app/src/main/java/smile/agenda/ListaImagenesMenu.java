package smile.agenda;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class ListaImagenesMenu extends AppCompatActivity implements View.OnClickListener {
    private baseDatos conexion=new baseDatos(this,"notas",null,1);
    private SQLiteDatabase escribir;
    private Spinner listView;
    //private Imagen sele;
    private ArrayList<Imagen> listaImgMenu;
    private TextView tD;
    private EditText tTexto;
    private Button bGuardar;
    private Calendar diaSe;
    private int idS;
    private Button cancelar;
    private Button masD, masColor, btnImagenes, btnHora;
    private Intent intentMasDias;
    private static ArrayList<Calendar>listaDias;
    private int imag;
    private int hora,minutos;
    private int d,m,a;
    private ArrayList<Drawable>listadoFondo;
    private int fondo,img;
    private Bundle bundle;
    private Intent intent_color, intent_imagen, intent_hora;
    private Image imagen;
    private int color;
    private Button btnMenuImagen;
    private ArrayList<Integer>colores;
    private LinearLayout fech;
    private TextView txt_hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagenes_menu);

        btnMenuImagen =(Button)findViewById(R.id.btnMenuImagen);
        bGuardar =(Button)findViewById(R.id.btnGuardar);
        tD =(TextView)findViewById(R.id.txtDia);
        tTexto =(EditText)findViewById(R.id.txtTexto);
        cancelar = (Button)findViewById(R.id.btnCancelar);
        masD =(Button)findViewById(R.id.btnMasDias);
        masColor =(Button)findViewById(R.id.btnColor);
        btnHora =(Button)findViewById(R.id.btnHora);
        fech =(LinearLayout)findViewById(R.id.fech);
        txt_hora =(TextView)findViewById(R.id.txt_hora);
        //si no se elige ningun fondo por defecto se pone el que tiene id =1
        color =1;

        colores = new Imagen().cargarColorCalendario();
        listaDias = new ArrayList<>();
        listaImgMenu =new ArrayList<>();

        cargarArrayImagenes();


        bundle = getIntent().getExtras();
         d =bundle.getInt("feD");
         m =bundle.getInt("feM");
         a =bundle.getInt("feA");
        hora =bundle.getInt("feH");
        minutos=bundle.getInt("feMe");
        idS =bundle.getInt("id");

        //SI SE ESTA EDITANDO EL EVENTO 
            tTexto.setText(bundle.getString("texto"));
            color= bundle.getInt("fondo");
            imag =bundle.getInt("imagen");


        fech.setBackgroundColor(getResources().getColor(colores.get(m)));
        if(imag>0) {
            btnMenuImagen.setBackground(listaImgMenu.get(imag-1).getImagBoton());
        }


        //se crea una instacia de calendar y se le da el valor de la fecha del evento
        diaSe = Calendar.getInstance();

        diaSe.set(a,m,d,hora,minutos);
        txt_hora.setText(hora(diaSe));


        intentMasDias = new Intent(this,AnadirDias.class);
        intent_imagen = new Intent(this, Dibujos.class);
        intent_color = new Intent(this, Colores.class);


        masColor.setOnClickListener(this);
        bGuardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        masD.setOnClickListener(this);
        btnMenuImagen.setOnClickListener(this);
        btnHora .setOnClickListener(this);


        escribir=conexion.getWritableDatabase();



        String txM;
        if(m+1<10){
           txM ="0"+(m+1);
        }
        else{
            txM=String.valueOf(m+1);
        }
        tD.setText((d+"/"+txM+"/"+a));

        //sele =new Imagen(0,null,null);


    }


    public void guardar(){
    //compruebo si hay texto o imagen
    //sino hay ni texto ni imagen se muestra error


        if(tTexto.length()==0 ) {
            Toast.makeText(this,getResources().getString(R.string.error_evento),Toast.LENGTH_LONG).show();
        }
        else{
            String tx="";
            tx = tTexto.getText().toString();
            if(listaDias.size()==0){


                  if (idS != -1) {//editar evento
                      conexion.modificarEvento(escribir, idS, tx, imag, diaSe, color);
                      alarma(diaSe);

                  } else {//nuevo evento
                      conexion.anadirEvento(escribir, diaSe, tx, imag, color);
                        alarma(diaSe);


                  }
              }
                  if(listaDias.size()!=0){//varios dias con el mismo evento
                      //el evento se guarda en mas de un dia
                      //se inserta la hora
                      for(int i = 0;i<listaDias.size();i++){
                          listaDias.get(i).set(Calendar.HOUR_OF_DAY,hora);
                          listaDias.get(i).set(Calendar.MINUTE,minutos);
                          listaDias.get(i).set(Calendar.SECOND,00);
                          listaDias.get(i).set(Calendar.MILLISECOND,00);
                          alarma(listaDias.get(i));




                      }
                      conexion.anadirEventoVarios(escribir,tx,imag,listaDias,color);
                      if(idS!=-1){
                          conexion.borrarEvento(escribir,idS);
                      }
                  }


        tTexto.setVisibility(View.INVISIBLE);



            finish();
        }

        }
        public static void anadir(ArrayList<Calendar> l){
            listaDias =l;
        }
 


        public void alarma(Calendar fecha){

            //se calcula la diferencia en miliseguntos desde la fecha de creaccion hasta la fecha del evento
            long diferencia = fecha.getTimeInMillis()-Calendar.getInstance().getTimeInMillis();


            //se crea la alarma para mostrar notificacion

            AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            Intent        intent  = new Intent(this, Notificacion.class);
            PendingIntent pIntent = PendingIntent.getBroadcast(this, 1, intent,  PendingIntent.FLAG_CANCEL_CURRENT);
            manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + diferencia, pIntent);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK){ //1 IMAGENES Y 2 COLOR
            if(requestCode==1){
                imag = data.getExtras().getInt("imagen");
                btnMenuImagen.setBackground(listaImgMenu.get(imag-1).getImagBoton());
            }
            else if(requestCode==2){
                color =data.getExtras().getInt("color");
            }
            else if(requestCode==3){
                diaSe.set(Calendar.HOUR_OF_DAY,data.getExtras().getInt("hora"));
                diaSe.set(Calendar.MINUTE,minutos, data.getExtras().getInt("minutos"));
                diaSe.set(Calendar.SECOND,00);
                diaSe.set(Calendar.MILLISECOND,00);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGuardar:
                //se muestra un lottie

                guardar();
                break;
            case R.id.btnCancelar:
                finish();
                break;
            case R.id.btnMasDias:
                intentMasDias.putExtra("dia",diaSe.get(Calendar.DAY_OF_MONTH));
                intentMasDias.putExtra("mes",diaSe.get(Calendar.MONTH));
                intentMasDias.putExtra("ano",diaSe.get(Calendar.YEAR));
                startActivity(intentMasDias);
                break;
            case R.id.btnMenuImagen:
                Log.e("pulsado","pulsado");
                startActivityForResult(intent_imagen,1);
                break;
            case R.id.btnColor:
                startActivityForResult(intent_color,2);
                break;
            case R.id.btnHora:
                obtenerHora();
                break;
        }
    }

    public void cargarArrayImagenes() {

        /// RECUPERO LA LISTA DE IMAGENES QUE TIENEN COMO VALOR EL INT DEL DRAWABLE, Y CONVIERTO ESOS INT EN DRAWABLES PARA PASARLO AL ADAPTADOR
        ArrayList<Imagen> l = new Imagen().listaInteger();
        for(int i = 0; i<l.size();i++){
            Imagen im = new Imagen(i+1, getResources().getDrawable(l.get(i).getInt_imagLista()), getResources().getDrawable(l.get(i).getInt_imagBoton()));
            listaImgMenu.add(im);
        }


    }
    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf("0" + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf("0" + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario

                //Muestro la hora con el formato deseado
                txt_hora.setText(horaFormateada + ":" + minutoFormateado);
                diaSe.set(diaSe.get(Calendar.YEAR),diaSe.get(Calendar.MONTH),diaSe.get(Calendar.DAY_OF_MONTH),hourOfDay,minute);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas

        }, hora, minutos, true);

        recogerHora.show();
    }
    public String hora(Calendar f){
        String h;
        String hor=null,min = null;
        Log.e("fecha",""+f.get(Calendar.HOUR_OF_DAY));

        if(f.get(Calendar.HOUR_OF_DAY)<10){
            hor = "0"+f.get(Calendar.HOUR_OF_DAY);
        }
        else{
            hor =String.valueOf(f.get(Calendar.HOUR_OF_DAY));
        }
        if( f.get(Calendar.MINUTE)<10){
            min = "0"+f.get(Calendar.MINUTE);
        }
        else{
            min =String.valueOf(f.get(Calendar.MINUTE));
        }

        h =hor+":"+min;
        Log.e("fecha","formato"+hor);
        return h;
    }
}
