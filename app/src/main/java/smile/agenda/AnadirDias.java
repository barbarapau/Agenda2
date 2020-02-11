package smile.agenda;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import smile.agenda.R;

public class AnadirDias extends AppCompatActivity implements View.OnClickListener {


    private TextView lu,ma,mi,ju,vi,sa,dom,anoM;
    private ToggleButton bo11,bo12,bo13,bo14,bo15,bo16,bo17;

    private ToggleButton bo21,bo22,bo23,bo24,bo25,bo26,bo27;
    private ToggleButton bo31,bo32,bo33,bo34,bo35,bo36,bo37;
    private ToggleButton bo41,bo42,bo43,bo44,bo45,bo46,bo47;
    private ToggleButton bo51,bo52,bo53,bo54,bo55,bo56,bo57;
    private ToggleButton bo61,bo62,bo63,bo64,bo65,bo66,bo67;
    private TextView boMes;
    private baseDatos conexion=new baseDatos(this,"notas",null,1);
    private ArrayList<String> listaMes;
    private ArrayList<ToggleButton>listaDias;
    String ene,feb,mar,abr,may,jun,jul,ago,sep,oct,nov,dic;
    private Calendar fecha,hoy;
    private ArrayList<Evento> listaEventos;
    private ImageButton anterior,siguiente;
    private LinearLayout layoutAnadir;
    private SQLiteDatabase leer,escribir;
    private ArrayList<Evento> eventosMes;
    private int diaSemana;
    private ArrayList<Calendar> listaMultiple;
    private Typeface face;
    private ListView lis;
    private Button guardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_dias);
            Bundle v = getIntent().getExtras();
        int dAnte = v.getInt("dia");
        int mAnte = v.getInt("mes");
        int ante = v.getInt("ano");


            listaMes=new ArrayList<>();
            listaMultiple =new ArrayList<>();



            listaEventos =new ArrayList<>();
         listaMultiple = new ArrayList<>();
            eventosMes =new ArrayList<>();
            listaDias =new ArrayList<>();

            fecha = Calendar.getInstance();
        fecha.set(ante,mAnte,dAnte);
        Calendar fecha_Sele = Calendar.getInstance();
        fecha_Sele.set(ante,mAnte,dAnte);
        listaMultiple.add(fecha_Sele);
            hoy =Calendar.getInstance();
            leer =conexion.getReadableDatabase();
            escribir=conexion.getWritableDatabase();
        layoutAnadir = (LinearLayout) findViewById(R.id.layoutAnadir);
            bo11 = (ToggleButton) findViewById(R.id.b11M);bo12 = (ToggleButton) findViewById(R.id.b12M);bo13 = (ToggleButton) findViewById(R.id.b13M);bo14 =(ToggleButton) findViewById(R.id.b14M);bo15 =(ToggleButton) findViewById(R.id.b15M);bo16 = (ToggleButton) findViewById(R.id.b16M);bo17 = (ToggleButton) findViewById(R.id.b17M);
            bo21 = (ToggleButton) findViewById(R.id.b21M);bo22 = (ToggleButton) findViewById(R.id.b22M);bo23 = (ToggleButton) findViewById(R.id.b23M);bo24 =(ToggleButton) findViewById(R.id.b24M);bo25 =(ToggleButton) findViewById(R.id.b25M);bo26 = (ToggleButton) findViewById(R.id.b26M);bo27 = (ToggleButton) findViewById(R.id.b27M);
            bo31 = (ToggleButton) findViewById(R.id.b31M);bo32 = (ToggleButton) findViewById(R.id.b32M);bo33 = (ToggleButton) findViewById(R.id.b33M);bo34 =(ToggleButton) findViewById(R.id.b34M);bo35 =(ToggleButton) findViewById(R.id.b35M);bo36 = (ToggleButton) findViewById(R.id.b36M);bo37 = (ToggleButton) findViewById(R.id.b37M);
            bo41 = (ToggleButton) findViewById(R.id.b41M);bo42 = (ToggleButton) findViewById(R.id.b42M);bo43 = (ToggleButton) findViewById(R.id.b43M);bo44 =(ToggleButton) findViewById(R.id.b44M);bo45 =(ToggleButton) findViewById(R.id.b45M);bo46 = (ToggleButton) findViewById(R.id.b46M);bo47 = (ToggleButton) findViewById(R.id.b47M);
            bo51 = (ToggleButton) findViewById(R.id.b51M);bo52 = (ToggleButton) findViewById(R.id.b52M);bo53 = (ToggleButton) findViewById(R.id.b53M);bo54 =(ToggleButton) findViewById(R.id.b54M);bo55 =(ToggleButton) findViewById(R.id.b55M);bo56 = (ToggleButton) findViewById(R.id.b56M);bo57 = (ToggleButton) findViewById(R.id.b57M);
            bo61 = (ToggleButton) findViewById(R.id.b61M);bo62 = (ToggleButton) findViewById(R.id.b62M);bo63 = (ToggleButton) findViewById(R.id.b63M);bo64 =(ToggleButton) findViewById(R.id.b64M);bo65 =(ToggleButton) findViewById(R.id.b65M);bo66 = (ToggleButton) findViewById(R.id.b66M);bo67 = (ToggleButton) findViewById(R.id.b67M);
            lu =(TextView)findViewById(R.id.lunesM);
            ma =(TextView)findViewById(R.id.martesM);
            mi =(TextView)findViewById(R.id.miercolesM);
            ju =(TextView)findViewById(R.id.juevesM);
            vi =(TextView)findViewById(R.id.viernesM);
            sa =(TextView)findViewById(R.id.sabadoM);
            dom=(TextView)findViewById(R.id.domingoM);
            guardar =(Button)findViewById(R.id.btnVarios);
            anoM= (TextView)findViewById(R.id.anoM);
            boMes=(TextView)findViewById(R.id.mesM);
            anterior =(ImageButton)findViewById(R.id.anteM);
            siguiente=(ImageButton)findViewById(R.id.siguiM);

            bo11.setOnClickListener(this);bo12.setOnClickListener(this);bo13.setOnClickListener(this);bo14.setOnClickListener(this);bo15.setOnClickListener(this);bo16.setOnClickListener(this);bo17.setOnClickListener(this);
            bo21.setOnClickListener(this);bo22.setOnClickListener(this);bo23.setOnClickListener(this);bo24.setOnClickListener(this);bo25.setOnClickListener(this);bo26.setOnClickListener(this);bo27.setOnClickListener(this);
            bo31.setOnClickListener(this);bo32.setOnClickListener(this);bo33.setOnClickListener(this);bo34.setOnClickListener(this);bo35.setOnClickListener(this);bo36.setOnClickListener(this);bo37.setOnClickListener(this);
            bo41.setOnClickListener(this);bo42.setOnClickListener(this);bo43.setOnClickListener(this);bo44.setOnClickListener(this);bo45.setOnClickListener(this);bo46.setOnClickListener(this);bo47.setOnClickListener(this);
            bo51.setOnClickListener(this);bo52.setOnClickListener(this);bo53.setOnClickListener(this);bo54.setOnClickListener(this);bo55.setOnClickListener(this);bo56.setOnClickListener(this);bo57.setOnClickListener(this);
            bo61.setOnClickListener(this);bo62.setOnClickListener(this);bo63.setOnClickListener(this);bo64.setOnClickListener(this);bo65.setOnClickListener(this);bo66.setOnClickListener(this);bo67.setOnClickListener(this);
            guardar.setOnClickListener(this);


            anterior.setOnClickListener(this);
            siguiente.setOnClickListener(this);

            //fuente


            ene=getResources().getString(R.string.ene);
            feb=getResources().getString(R.string.feb);
            mar=getResources().getString(R.string.mar);
            abr=getResources().getString(R.string.abr);
            may=getResources().getString(R.string.may);
            jun=getResources().getString(R.string.jun);
            jul=getResources().getString(R.string.jul);
            ago=getResources().getString(R.string.ago);
            sep=getResources().getString(R.string.sep);
            oct=getResources().getString(R.string.oct);
            nov=getResources().getString(R.string.nov);
            dic=getResources().getString(R.string.dic);
            listaMes.add(ene);
            listaMes.add(feb);
            listaMes.add(mar);
            listaMes.add(abr);
            listaMes.add(may);
            listaMes.add(jun);
            listaMes.add(jul);
            listaMes.add(ago);
            listaMes.add(sep);
            listaMes.add(oct);
            listaMes.add(nov);
            listaMes.add(dic);


            listaDias.add(bo11); listaDias.add(bo12); listaDias.add(bo13); listaDias.add(bo14); listaDias.add(bo15); listaDias.add(bo16); listaDias.add(bo17);
            listaDias.add(bo21); listaDias.add(bo22); listaDias.add(bo23); listaDias.add(bo24); listaDias.add(bo25); listaDias.add(bo26); listaDias.add(bo27);
            listaDias.add(bo31); listaDias.add(bo32); listaDias.add(bo33); listaDias.add(bo34); listaDias.add(bo35); listaDias.add(bo36); listaDias.add(bo37);
            listaDias.add(bo41); listaDias.add(bo42); listaDias.add(bo43); listaDias.add(bo44); listaDias.add(bo45); listaDias.add(bo46); listaDias.add(bo47);
            listaDias.add(bo51); listaDias.add(bo52); listaDias.add(bo53); listaDias.add(bo54); listaDias.add(bo55); listaDias.add(bo56); listaDias.add(bo57);
            listaDias.add(bo61); listaDias.add(bo62); listaDias.add(bo63); listaDias.add(bo64); listaDias.add(bo65); listaDias.add(bo66); listaDias.add(bo67);


            //se muestran los 5 años anteriores y posteriores al actual





            rellenarCalendario();


        }




    public void rellenarCalendario(){

        //obtengo el mes y el año y los inserto en los campos


        //mes 0 enero, 1 febrero
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int diaPresente = hoy.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH);
        int año = fecha.get(Calendar.YEAR);
        // cada mes tiene un color asignado

        layoutAnadir.setBackgroundColor(getResources().getColor(new Imagen().cargarColorCalendario().get(mes)));



        boMes.setText(listaMes.get(mes));
        anoM.setText("            "+año+"            ");
       // boAño.setText(String.valueOf(año));
        //obtengo el dia de la semana del primer dia del mes

        Calendar primero =Calendar.getInstance();
        eventosMes = conexion.consultarEventosMes(leer, fecha);


        primero.set(fecha.get(Calendar.YEAR),fecha.get(Calendar.MONTH),1);
        diaSemana =primero.get(Calendar.DAY_OF_WEEK)-2;
        if(diaSemana==-1){
            diaSemana=6;
        }


        int contador=1;
        //obtengo el ultimo dia del mes
        int ultimoDia = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);

        //limpio calendario
        for(int i = 0;i<listaDias.size();i++) {
            listaDias.get(i).setTextOn("");
            listaDias.get(i).setTextOff("");
            listaDias.get(i).setText("");
            listaDias.get(i).setBackground(null);
            listaDias.get(i).setChecked(false);
        }




        for(int i = diaSemana;contador<=ultimoDia;i++){
            listaDias.get(i).setTextOn(String.valueOf(contador));
            listaDias.get(i).setTextOff(String.valueOf(contador));
            listaDias.get(i).setText(String.valueOf(contador));
            listaDias.get(i).setBackground(getResources().getDrawable(R.drawable.dia));
            if(listaMultiple.size()!=0){
            for(int y  =0; y<listaMultiple.size();y++) {

                if(listaMultiple.get(y).get(Calendar.MONTH)==mes){
                    if((int)(listaMultiple.get(y).get(Calendar.DAY_OF_MONTH))==contador){
                        listaDias.get(i).setBackground(getResources().getDrawable(R.drawable.dia_con_evento_seleccionado));
                        listaDias.get(i).setChecked(true);
                    }
                }
            }
            }


            contador=contador+1;
        }



    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.anteM:
                fecha.add(Calendar.MONTH,-1);
                rellenarCalendario();
                break;
            case R.id.siguiM:
                fecha.add(Calendar.MONTH,1);
                rellenarCalendario();
                break;
            case R.id.btnVarios:
                ListaImagenesMenu.anadir(listaMultiple);
                finish();
                break;

            default:

                //si se pulsa sobre un dia se sombrea, se muestra los eventos de ese dia y aparece un boton flotante para añadir nuevos eventos
                ToggleButton d = (ToggleButton) v;
                if(d.getText().length()>0){

                int diaSele = Integer.valueOf(d.getText().toString());

                //int mesSele = 0;
                int añoSele = fecha.get(Calendar.YEAR);;
                int mesSele = fecha.get(Calendar.MONTH);

                Calendar fechaVarios = Calendar.getInstance();
                fechaVarios.set(añoSele, mesSele, diaSele,0,0,0);
                if(d.isChecked()==false){
                    //si el dia ya esta seleccionado se deselecciona
                    v.setBackground(getResources().getDrawable(R.drawable.dia));
                    //busco la fecha en el array y la borro;
                    for(int i = 0;i<listaMultiple.size();i++){
                        if(listaMultiple.get(i).get(Calendar.MONTH)==fechaVarios.get(Calendar.MONTH)){

                            if(listaMultiple.get(i).get(Calendar.DAY_OF_MONTH)==fechaVarios.get(Calendar.DAY_OF_MONTH)) {

                                listaMultiple.remove(i);
                            }

                        }
                    }

                }
                else {
                    v.setBackground(getResources().getDrawable(R.drawable.dia_con_evento_seleccionado));
                    listaMultiple.add(fechaVarios);

                }
                Collections.sort(listaMultiple);

                  }
    }}



}




