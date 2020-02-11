package smile.agenda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.view.View.VISIBLE;

public class calendario extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Imagen> listaImgMenu;
    private Intent evt;
    private ImageView im11, im12, im13, im14, im15, im16, im17;
    private ImageView im21, im22, im23, im24, im25, im26, im27;
    private ImageView im31, im32, im33, im34, im35, im36, im37;
    private ImageView im41, im42, im43, im44, im45, im46, im47;
    private ImageView im51, im52, im53, im54, im55, im56, im57;
    private ImageView im61, im62, im63, im64, im65, im66, im67;
    private TextView bo11, bo12, bo13, bo14, bo15, bo16, bo17, lu, ma, mi, ju, vi, sa, dom;
    private TextView bo21, bo22, bo23, bo24, bo25, bo26, bo27;
    private TextView bo31, bo32, bo33, bo34, bo35, bo36, bo37;
    private TextView bo41, bo42, bo43, bo44, bo45, bo46, bo47;
    private TextView bo51, bo52, bo53, bo54, bo55, bo56, bo57;
    private TextView bo61, bo62, bo63, bo64, bo65, bo66, bo67;
    private TextView boAño;
    private baseDatos conexion = new baseDatos(this, "notas", null, 1);
    private ArrayList<String> listaMes;
    private ArrayList<TextView> listaDias;
    String ene, feb, mar, abr, may, jun, jul, ago, sep, oct, nov, dic;
    private Calendar fecha, hoy;
    private ArrayAdapter adaptadorMes, adaptadorAño;
    private ArrayList<Evento> listaEventos;
    private SQLiteDatabase leer, escribir;
    private ArrayList<Evento> eventosMes;
    private int diaSemana;
    private ArrayList<Calendar> listaMultiple;
    private ArrayList<ImageView> listaImgDias;
    private adapatador_eventos adpEvt;

    private ListView lis;
    private SharedPreferences preferences;
    private ArrayList<Drawable> listaFondo;
    private LinearLayout barraMes;
    private FloatingActionButton btnFlotante;
    private TextView diaSele=null;
    private int mesSele, añoSele;
    private TextView txtBarraMes;
    private Button btnBarraDiaMes, btnAño;
    private float firstTouchX, actualX;
    private ArrayList <Integer>colores;
    private AdView AdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView.loadAd(adRequest);



          //MobileAds.initialize(this, "ca-app-pub-3132149477087366~9478741022");

        preferences = getSharedPreferences("primero", Context.MODE_PRIVATE);
        int p = preferences.getInt("inicio", 0);
//si p =0 es la primera vez que se abre, si p=1 no.


        fecha = Calendar.getInstance();
        Bundle v = getIntent().getExtras();
        if(v!=null){
        mesSele = v.getInt("mesSele");
        añoSele =v.getInt("añoSele");

            fecha.set(añoSele,mesSele,fecha.get(Calendar.DAY_OF_MONTH));
        }

    /////////////////////////////INICILIZO TODOS LOS ARRAY****************************************
        listaMes = new ArrayList<>();
        listaMultiple = new ArrayList<>();
        listaImgMenu = new ArrayList<>();
        listaEventos = new ArrayList<>();
        listaImgDias = new ArrayList<>();
        eventosMes = new ArrayList<>();
        listaDias = new ArrayList<>();
        colores = new ArrayList<>();


        cargarArrayImagenes();

        hoy = Calendar.getInstance();
        leer = conexion.getReadableDatabase();
        escribir = conexion.getWritableDatabase();


        //AdView mAdView = (AdView) findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);
        bo11 = (TextView) findViewById(R.id.b11);
        bo12 = (TextView) findViewById(R.id.b12);
        bo13 = (TextView) findViewById(R.id.b13);
        bo14 = (TextView) findViewById(R.id.b14);
        bo15 = (TextView) findViewById(R.id.b15);
        bo16 = (TextView) findViewById(R.id.b16);
        bo17 = (TextView) findViewById(R.id.b17);
        bo21 = (TextView) findViewById(R.id.b21);
        bo22 = (TextView) findViewById(R.id.b22);
        bo23 = (TextView) findViewById(R.id.b23);
        bo24 = (TextView) findViewById(R.id.b24);
        bo25 = (TextView) findViewById(R.id.b25);
        bo26 = (TextView) findViewById(R.id.b26);
        bo27 = (TextView) findViewById(R.id.b27);
        bo31 = (TextView) findViewById(R.id.b31);
        bo32 = (TextView) findViewById(R.id.b32);
        bo33 = (TextView) findViewById(R.id.b33);
        bo34 = (TextView) findViewById(R.id.b34);
        bo35 = (TextView) findViewById(R.id.b35);
        bo36 = (TextView) findViewById(R.id.b36);
        bo37 = (TextView) findViewById(R.id.b37);
        bo41 = (TextView) findViewById(R.id.b41);
        bo42 = (TextView) findViewById(R.id.b42);
        bo43 = (TextView) findViewById(R.id.b43);
        bo44 = (TextView) findViewById(R.id.b44);
        bo45 = (TextView) findViewById(R.id.b45);
        bo46 = (TextView) findViewById(R.id.b46);
        bo47 = (TextView) findViewById(R.id.b47);
        bo51 = (TextView) findViewById(R.id.b51);
        bo52 = (TextView) findViewById(R.id.b52);
        bo53 = (TextView) findViewById(R.id.b53);
        bo54 = (TextView) findViewById(R.id.b54);
        bo55 = (TextView) findViewById(R.id.b55);
        bo56 = (TextView) findViewById(R.id.b56);
        bo57 = (TextView) findViewById(R.id.b57);
        bo61 = (TextView) findViewById(R.id.b61);
        bo62 = (TextView) findViewById(R.id.b62);
        bo63 = (TextView) findViewById(R.id.b63);
        bo64 = (TextView) findViewById(R.id.b64);
        bo65 = (TextView) findViewById(R.id.b65);
        bo66 = (TextView) findViewById(R.id.b66);
        bo67 = (TextView) findViewById(R.id.b67);
        im11 = (ImageView) findViewById(R.id.i11);
        im12 = (ImageView) findViewById(R.id.i12);
        im13 = (ImageView) findViewById(R.id.i13);
        im14 = (ImageView) findViewById(R.id.i14);
        im15 = (ImageView) findViewById(R.id.i15);
        im16 = (ImageView) findViewById(R.id.i16);
        im17 = (ImageView) findViewById(R.id.i17);
        im21 = (ImageView) findViewById(R.id.i21);
        im22 = (ImageView) findViewById(R.id.i22);
        im23 = (ImageView) findViewById(R.id.i23);
        im24 = (ImageView) findViewById(R.id.i24);
        im25 = (ImageView) findViewById(R.id.i25);
        im26 = (ImageView) findViewById(R.id.i26);
        im27 = (ImageView) findViewById(R.id.i27);
        im31 = (ImageView) findViewById(R.id.i31);
        im32 = (ImageView) findViewById(R.id.i32);
        im33 = (ImageView) findViewById(R.id.i33);
        im34 = (ImageView) findViewById(R.id.i34);
        im35 = (ImageView) findViewById(R.id.i35);
        im36 = (ImageView) findViewById(R.id.i36);
        im37 = (ImageView) findViewById(R.id.i37);
        im41 = (ImageView) findViewById(R.id.i41);
        im42 = (ImageView) findViewById(R.id.i42);
        im43 = (ImageView) findViewById(R.id.i43);
        im44 = (ImageView) findViewById(R.id.i44);
        im45 = (ImageView) findViewById(R.id.i45);
        im46 = (ImageView) findViewById(R.id.i46);
        im47 = (ImageView) findViewById(R.id.i47);
        im51 = (ImageView) findViewById(R.id.i51);
        im52 = (ImageView) findViewById(R.id.i52);
        im53 = (ImageView) findViewById(R.id.i53);
        im54 = (ImageView) findViewById(R.id.i54);
        im55 = (ImageView) findViewById(R.id.i55);
        im56 = (ImageView) findViewById(R.id.i56);
        im57 = (ImageView) findViewById(R.id.i57);
        im61 = (ImageView) findViewById(R.id.i61);
        im62 = (ImageView) findViewById(R.id.i62);
        im63 = (ImageView) findViewById(R.id.i63);
        im64 = (ImageView) findViewById(R.id.i64);
        im65 = (ImageView) findViewById(R.id.i65);
        im66 = (ImageView) findViewById(R.id.i66);
        im67 = (ImageView) findViewById(R.id.i67);
        lu = (TextView) findViewById(R.id.lunes);
        ma = (TextView) findViewById(R.id.martes);
        mi = (TextView) findViewById(R.id.miercoles);
        ju = (TextView) findViewById(R.id.jueves);
        vi = (TextView) findViewById(R.id.viernes);
        sa = (TextView) findViewById(R.id.sabado);
        dom = (TextView) findViewById(R.id.domingo);
        lis = (ListView) findViewById(R.id.li);
        txtBarraMes =(TextView)findViewById(R.id.txtBarraMes);
        btnBarraDiaMes = (Button) findViewById(R.id.btnBarraDiaMes);
        btnAño =(Button)findViewById(R.id.btnAño);
        barraMes = (LinearLayout)findViewById(R.id.barraMes);

        btnFlotante = (FloatingActionButton) findViewById(R.id.btn_flotante);
        actualizarEventos(fecha);
        ArrayList<String> j = new ArrayList<>();
        j.add("hol");
        j.add("hola");
        j.add("holg");
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, j);
        actualizarEventos(fecha);
        //actionBar



        evt = new Intent(this, ListaImagenesMenu.class);
        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TAG", "pulso");
                actualizarEventos(fecha);
                adpEvt.sele(position);
                lis.setAdapter(adpEvt);
            }
        });


        boAño = (TextView) findViewById(R.id.ano);



        bo11.setOnClickListener(this);
        bo12.setOnClickListener(this);
        bo13.setOnClickListener(this);
        bo14.setOnClickListener(this);
        bo15.setOnClickListener(this);
        bo16.setOnClickListener(this);
        bo17.setOnClickListener(this);
        bo21.setOnClickListener(this);
        bo22.setOnClickListener(this);
        bo23.setOnClickListener(this);
        bo24.setOnClickListener(this);
        bo25.setOnClickListener(this);
        bo26.setOnClickListener(this);
        bo27.setOnClickListener(this);
        bo31.setOnClickListener(this);
        bo32.setOnClickListener(this);
        bo33.setOnClickListener(this);
        bo34.setOnClickListener(this);
        bo35.setOnClickListener(this);
        bo36.setOnClickListener(this);
        bo37.setOnClickListener(this);
        bo41.setOnClickListener(this);
        bo42.setOnClickListener(this);
        bo43.setOnClickListener(this);
        bo44.setOnClickListener(this);
        bo45.setOnClickListener(this);
        bo46.setOnClickListener(this);
        bo47.setOnClickListener(this);
        bo51.setOnClickListener(this);
        bo52.setOnClickListener(this);
        bo53.setOnClickListener(this);
        bo54.setOnClickListener(this);
        bo55.setOnClickListener(this);
        bo56.setOnClickListener(this);
        bo57.setOnClickListener(this);
        bo61.setOnClickListener(this);
        bo62.setOnClickListener(this);
        bo63.setOnClickListener(this);
        bo64.setOnClickListener(this);
        bo65.setOnClickListener(this);
        bo66.setOnClickListener(this);
        bo67.setOnClickListener(this);
        btnAño.setOnClickListener(this);



        btnBarraDiaMes.setOnClickListener(this);
        btnFlotante.setOnClickListener(this);





        ene = getResources().getString(R.string.ene);
        feb = getResources().getString(R.string.feb);
        mar = getResources().getString(R.string.mar);
        abr = getResources().getString(R.string.abr);
        may = getResources().getString(R.string.may);
        jun = getResources().getString(R.string.jun);
        jul = getResources().getString(R.string.jul);
        ago = getResources().getString(R.string.ago);
        sep = getResources().getString(R.string.sep);
        oct = getResources().getString(R.string.oct);
        nov = getResources().getString(R.string.nov);
        dic = getResources().getString(R.string.dic);
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


        listaDias.add(bo11);
        listaDias.add(bo12);
        listaDias.add(bo13);
        listaDias.add(bo14);
        listaDias.add(bo15);
        listaDias.add(bo16);
        listaDias.add(bo17);
        listaDias.add(bo21);
        listaDias.add(bo22);
        listaDias.add(bo23);
        listaDias.add(bo24);
        listaDias.add(bo25);
        listaDias.add(bo26);
        listaDias.add(bo27);
        listaDias.add(bo31);
        listaDias.add(bo32);
        listaDias.add(bo33);
        listaDias.add(bo34);
        listaDias.add(bo35);
        listaDias.add(bo36);
        listaDias.add(bo37);
        listaDias.add(bo41);
        listaDias.add(bo42);
        listaDias.add(bo43);
        listaDias.add(bo44);
        listaDias.add(bo45);
        listaDias.add(bo46);
        listaDias.add(bo47);
        listaDias.add(bo51);
        listaDias.add(bo52);
        listaDias.add(bo53);
        listaDias.add(bo54);
        listaDias.add(bo55);
        listaDias.add(bo56);
        listaDias.add(bo57);
        listaDias.add(bo61);
        listaDias.add(bo62);
        listaDias.add(bo63);
        listaDias.add(bo64);
        listaDias.add(bo65);
        listaDias.add(bo66);
        listaDias.add(bo67);

        listaImgDias.add(im11);
        listaImgDias.add(im12);
        listaImgDias.add(im13);
        listaImgDias.add(im14);
        listaImgDias.add(im15);
        listaImgDias.add(im16);
        listaImgDias.add(im17);
        listaImgDias.add(im21);
        listaImgDias.add(im22);
        listaImgDias.add(im23);
        listaImgDias.add(im24);
        listaImgDias.add(im25);
        listaImgDias.add(im26);
        listaImgDias.add(im27);
        listaImgDias.add(im31);
        listaImgDias.add(im32);
        listaImgDias.add(im33);
        listaImgDias.add(im34);
        listaImgDias.add(im35);
        listaImgDias.add(im36);
        listaImgDias.add(im37);
        listaImgDias.add(im41);
        listaImgDias.add(im42);
        listaImgDias.add(im43);
        listaImgDias.add(im44);
        listaImgDias.add(im45);
        listaImgDias.add(im46);
        listaImgDias.add(im47);
        listaImgDias.add(im51);
        listaImgDias.add(im52);
        listaImgDias.add(im53);
        listaImgDias.add(im54);
        listaImgDias.add(im55);
        listaImgDias.add(im56);
        listaImgDias.add(im57);
        listaImgDias.add(im61);
        listaImgDias.add(im62);
        listaImgDias.add(im63);
        listaImgDias.add(im64);
        listaImgDias.add(im65);
        listaImgDias.add(im66);
        listaImgDias.add(im67);







        //se muestran los 5 años anteriores y posteriores al actual


        rellenarCalendario();

        actualizarEventos(fecha);
        //permisos(this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        rellenarCalendario();

        actualizarEventos(fecha);
    }

    public void rellenarCalendario() {


        //obtengo el mes y el año y los inserto en los campos


        //mes 0 enero, 1 febrero
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int diaPresente = hoy.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH);
        txtBarraMes.setText(listaMes.get(mes));
        int año = fecha.get(Calendar.YEAR);
        boAño.setText(String.valueOf(año));
        btnBarraDiaMes.setText(String.valueOf(diaPresente));
        Log.e("mes",""+mes + "array "+colores.size());
        barraMes.setBackgroundColor(colores.get(mes));


        Calendar primero = Calendar.getInstance();
        eventosMes = conexion.consultarEventosMes(leer, fecha);
        cargarFondo();

        //primer dia del mes
        primero.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), 1);
        //obtengo el dia de la semana del primer dia del mes
        diaSemana = primero.get(Calendar.DAY_OF_WEEK) - 2;
        if (diaSemana == -1) {
            diaSemana = 6;
        }

        int contador = 1;
        //obtengo el ultimo dia del mes
        int ultimoDia = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);

        //limpio calendario
        for (int i = 0; i < listaDias.size(); i++) {
            listaDias.get(i).setText("");
            listaDias.get(i).setTextColor(getResources().getColor(R.color.marron));
            listaDias.get(i).setBackground(null);
        }
        for (int i = 0; i < listaImgDias.size(); i++) {
            listaImgDias.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.nube));
            listaImgDias.get(i).setVisibility(View.INVISIBLE);

            listaDias.get(i).setBackground(null);
        }
//00000000----------------MES ANTERIOR--------------------------------------------------------
        //añado numeracion del mes anterior si el mes no empieza el lunes
        if(diaSemana>0){
            //Creo una nueva instancia de calendar y le doy fecha del mes anterior de la variable fecha
            Calendar fechaMesAnterior = Calendar.getInstance();
            fechaMesAnterior.set(fecha.get(Calendar.YEAR),fecha.get(Calendar.MONTH),1);
            fechaMesAnterior.add(Calendar.MONTH,-1);
//obtengo el ultimo dia del mes anterior
            int ultimoDiaAnterior = fechaMesAnterior.getActualMaximum(Calendar.DAY_OF_MONTH);
            Log.e("vista","ultimo_dia_mes"+ ultimoDiaAnterior);

            //correspondiente al mes anterior el primer dia de la semana
            int calculo = ultimoDiaAnterior-(diaSemana-1);

            for(int a=0;a<diaSemana;a++){
                listaDias.get(a).setText(String.valueOf(calculo));
                listaDias.get(a).setTextColor(getResources().getColor(R.color.gris_noselect_texto));
              //  listaDias.get(a).setBackground(getResources().getDrawable(R.drawable.dia_mes_pasado));
                calculo++;
            }
        }
//-------------------------------MES SIGUIENTE------------------------------------------------------
        //Se calcula en que dia de la semana termina el mes
        Calendar c_ultimo = Calendar.getInstance();
        c_ultimo.set(fecha.get(Calendar.YEAR),fecha.get(Calendar.MONTH),ultimoDia);

        int diaSemanaUltimo = c_ultimo.get(Calendar.DAY_OF_WEEK) - 2;
        if (diaSemanaUltimo == -1) {
            diaSemanaUltimo = 6;
        }
        //sino termina en domingo


            //en que textView termina el mes seleccionado
            int empezar= diaSemana+ultimoDia;
            for(int d=1;empezar<listaDias.size();empezar++){
                listaDias.get(empezar).setText(String.valueOf(d));
                listaDias.get(empezar).setTextColor(getResources().getColor(R.color.gris_noselect_texto));
             //   listaDias.get(empezar).setBackground(getResources().getDrawable(R.drawable.dia_mes_pasado));
                d++;
            }



        //añado la numeracion del mes
        for (int i = diaSemana; contador <= ultimoDia; i++) {
            listaDias.get(i).setText(String.valueOf(contador));
            listaDias.get(i).setTextColor(getResources().getColor(R.color.gris));
          //  listaDias.get(i).setBackground(getResources().getDrawable(R.drawable.dia));
            //recorro el arra de eventos
            for (int w = 0; w < eventosMes.size(); w++) {
                //recupero el dia de cada uno de los eventos
                Calendar g = eventosMes.get(w).getFecha();
                int d = g.get(Calendar.DAY_OF_MONTH);
                //si el dia del evento es igual al dia del bucle
                if (d == contador) {
                    //recupero el fondo elegido para el evento y lo marco en el calendario que tiene un evento
                    if(eventosMes.get(w).getFondo() >=8 & eventosMes.get(w).getFondo() <= 14 ){
                        listaDias.get(i).setTextColor(getResources().getColor(R.color.blanco));
                    }
                    listaDias.get(i).setBackground(listaFondo.get(eventosMes.get(w).getFondo()));
                    if (eventosMes.get(w).getImagen() != 0) {
                        int posiArray = (eventosMes.get(w).getImagen())-1;
                        if( posiArray<0){
                            listaImgDias.get(i).setImageDrawable(getResources().getDrawable(R.mipmap.nube));
                            listaImgDias.get(i).setVisibility(View.INVISIBLE);
                        }
                        else {
                            listaImgDias.get(i).setImageDrawable(listaImgMenu.get(posiArray).getImagLista());

                            listaImgDias.get(i).setVisibility(VISIBLE);
                        }

                    }

                }
            }


            if (contador == dia) {//dia seleccionado
                listaDias.get(i).setBackground(getResources().getDrawable(R.drawable.dia_seleccionado));
                listaDias.get(i).setTextColor(getResources().getColor(R.color.marron));
            }
            if ((contador == diaPresente) & (hoy.get(Calendar.MONTH) == fecha.get(Calendar.MONTH))) {//marco el dia de hoy
                listaDias.get(i).setBackground(getResources().getDrawable(R.drawable.dia_presente));
                listaDias.get(i).setTextColor(getResources().getColor(R.color.marron));
               // diaSele=listaDias.get(i);//se marca como seleccionado por defecto el dia presente

                if (dia == diaPresente) {//si ademas el dia de hoy es el seleccionado
                    listaDias.get(i).setBackground(getResources().getDrawable(R.drawable.dia_seleccionado_presente));
                    listaDias.get(i).setTextColor(getResources().getColor(R.color.marron));
                }

            }
            contador = contador + 1;
        }

    }
// ****************************menu*************************************







    @Override
    public void onClick(View v) {
        Log.e("pulsado",""+v.getId());
        switch (v.getId()) {
            case R.id.btnBarraDiaMes:
                Calendar hoy = Calendar.getInstance();
                fecha=hoy;
                rellenarCalendario();
                actualizarEventos(fecha);
                break;


            case R.id.btnAño:
                Intent anual = new Intent(this,vista_year.class);
                startActivity(anual);

                break;

            case R.id.btn_flotante:

                if (diaSele!= null) {
                    anadirEvento(diaSele);
                }
                break;



            default:

                //si se pulsa sobre un dia se sombrea, se muestra los eventos de ese dia y aparece un boton flotante para añadir nuevos eventos
                TextView d = (TextView) v;
                btnFlotante.show();
                // Se carga la variable ocn el dia seleccionado
                diaSele =d;
                btnFlotante.show();
                int diaSele;

                if (d.toString().length() > 0) {
                    diaSele = Integer.valueOf(d.getText().toString());


                } else {
                    diaSele = 1;
                }



                int mesSele = 0;
                int añoSele = Integer.valueOf(boAño.getText().toString());
                String mesS = txtBarraMes.getText().toString();
                if (mesS.equals(ene)) {
                    mesSele = 0;
                }
                if (mesS.equals(feb)) {
                    mesSele = 1;
                }
                if (mesS.equals(mar)) {
                    mesSele = 2;
                }
                if (mesS.equals(abr)) {
                    mesSele = 3;
                }
                if (mesS.equals(may)) {
                    mesSele = 4;
                }
                if (mesS.equals(jun)) {
                    mesSele = 5;
                }
                if (mesS.equals(jul)) {
                    mesSele = 6;
                }
                if (mesS.equals(ago)) {
                    mesSele = 7;
                }
                if (mesS.equals(sep)) {
                    mesSele = 8;
                }
                if (mesS.equals(oct)) {
                    mesSele = 9;
                }
                if (mesS.equals(nov)) {
                    mesSele = 10;
                }
                if (mesS.equals(dic)) {
                    mesSele = 11;
                }
                //si se cumplen las dos condiciones corresponde al mes anterios
                if(Integer.valueOf(d.getText().toString())>7 & (d.getId()==R.id.b11 ||d.getId()==R.id.b12 ||d.getId()==R.id.b13 ||d.getId()==R.id.b14 ||d.getId()==R.id.b15 ||d.getId()==R.id.b16 ||d.getId()==R.id.b17)){
                    btnFlotante.hide();
                    mesSele=mesSele-1;


                }
                //si se cumplen las condiciones corresponde al mes siguiente
                if(Integer.valueOf(d.getText().toString())<20 & ( d.getId()==R.id.b56 ||d.getId()==R.id.b57 ||d.getId()==R.id.b61 ||d.getId()==R.id.b62 ||d.getId()==R.id.b63 ||d.getId()==R.id.b64 ||d.getId()==R.id.b65 ||d.getId()==R.id.b66 ||d.getId()==R.id.b67)){
                    btnFlotante.hide();
                    mesSele=mesSele+1;

                }
                    fecha.set(añoSele, mesSele, diaSele);
                    //se comprueba en la base de datos si hay algun evento en la fecha si lo hay se muestra.
                    //se muestra el boton para añadir evento en la fecha seleccionada y se quitan todos los posibles sombreados y
                    // se sombrea en el calendario el dia selecciondado


                    actualizarEventos(fecha);
                    rellenarCalendario();
                Log.e("vista","mes: "+mesSele);

        }

    }
    public boolean onTouchEvent(MotionEvent event){

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //Aqui guardas en una variable privada de clase las coordenadas del primer toque:
                firstTouchX = event.getX();

                break;

            case MotionEvent.ACTION_UP:
                actualX =event.getX();

                break;
        }
        if(firstTouchX> actualX){
            //suma meses
            if(firstTouchX-actualX>=350){
                fecha.add(Calendar.MONTH, 1);
                rellenarCalendario();
                actualizarEventos(fecha);
                btnFlotante.hide();
            }
            else{

            }
        }
        else if(firstTouchX < actualX){
            //resta meses
            if(actualX - firstTouchX>=350){

                    fecha.add(Calendar.MONTH, -1);
                    rellenarCalendario();
                    actualizarEventos(fecha);
                    btnFlotante.hide();// ocultar boton
            }
            else{
                Log.e("movimiento","pulsa");
            }
        }
        firstTouchX=0;
        actualX =0;
        return true;
    }

    public void actualizarEventos(Calendar f) {

        listaEventos = conexion.consultarEventoFecha(leer, f);
        adpEvt = new adapatador_eventos(this, listaEventos,  evt, escribir, conexion, listaImgMenu, btnFlotante);
       lis.setAdapter(adpEvt);


    }




    public void cargarArrayImagenes() {

        /// RECUPERO LA LISTA DE IMAGENES QUE TIENEN COMO VALOR EL INT DEL DRAWABLE, Y CONVIERTO ESOS INT EN DRAWABLES PARA PASARLO AL ADAPTADOR
        ArrayList<Imagen> l = new Imagen().listaInteger();
        for(int i = 0; i<l.size();i++){
            Imagen im = new Imagen(i+1, getResources().getDrawable(l.get(i).getInt_imagLista()), getResources().getDrawable(l.get(i).getInt_imagBoton()));
            listaImgMenu.add(im);
        }
        //RECUPERO LOS COLORES DEL CALENDARIO POR MES
        ArrayList<Integer> c  = new Imagen().cargarColorCalendario();
        for(int x = 0; x<c.size();x++){
            colores.add(getResources().getColor(c.get(x)));
        }

    }




    /**
     * Activa la alarma
     */

    public ArrayList<Drawable> cargarFondo() {
        listaFondo = new ArrayList<>();
        ArrayList<Integer> l =new Imagen().cargarFondoInteger();
        for(int i =0; i<l.size();i++){
            listaFondo.add(getResources().getDrawable(l.get(i)));
        }
        return listaFondo;
    }

    //*****************COPIA SEGURIDAD****************
    protected void Envio_Email() {


        String[] mailto = {""};
        String ruta_bd = getDatabasePath("notas.db").getAbsolutePath();

        //File bd = getDatabasePath("notas.db");
        Uri uri = Uri.parse(ruta_bd);
        Log.e("envio", "uri: " + uri);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
        //asunto
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.asunto_mail));
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, getResources().getString(R.string.texto_mail));
        emailIntent.setType("application/pdf");
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(emailIntent, "Send email using:"));

    }


        protected void sendEmail(String ruta) {


            //File bd = getDatabasePath("notas.db");
            Uri uri = Uri.parse(ruta);
           // String[] TO = {"contacto@seogalicia.es"}; //aquí pon tu correo
            //String[] CC = {""};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("application/pdf");

// Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
            emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this,
                        "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
            }
        }






    private void copia_seguridad() throws IOException {
        boolean sdDisponible = false;
        boolean sdAccesoEscritura = false;

//Comprobamos el estado de la memoria externa (tarjeta SD)
        String estado = Environment.getExternalStorageState();


        if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
        }

        try {
            Log.e("Ficheros", "acceso solo lectura: " + sdAccesoEscritura);
            File rut_sd = getExternalFilesDir(null);

            //File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");

            // OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
            String ruta_bd = getDatabasePath("notas.db").getAbsolutePath();
            //String ruta_sd = getExternalFilesDir(null).getAbsolutePath();
            String ruta_sd = Environment.getExternalStorageDirectory().getAbsolutePath();

            // fout.write("ruta origen: "+ruta_bd+" ruta destino: "+ r_sd);
            //fout.close();
            Log.e("Ficheros", "ruta origen: " + ruta_bd + " ruta destino: " + ruta_sd);

        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }

    }

    public void exportDatabse(String databaseName) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//"+getPackageName()+"//databases//"+databaseName+"";
                String backupDBPath = "backupname.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);
                Log.e("ruta", "ruta : "+backupDB);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
            Log.e("ruta", "error: "+e);
        }
    }



    public void anadirEvento(TextView d) {


    //se muestra un menu que permite incluir diferentes fondos a los dias seleccionados o incluir un mismo texto
    d.setBackground(getResources().getDrawable(R.drawable.dia_seleccionado));
    int diaC = Integer.valueOf(d.getText().toString());
    int mesC = 0;
    int añoC = Integer.valueOf(boAño.getText().toString());
    String mesS = txtBarraMes.getText().toString();
    if (mesS.equals(ene)) {
        mesC = 0;
    }
    if (mesS.equals(feb)) {
        mesC = 1;
    }
    if (mesS.equals(mar)) {
        mesC = 2;
    }
    if (mesS.equals(abr)) {
        mesC = 3;
    }
    if (mesS.equals(may)) {
        mesC = 4;
    }
    if (mesS.equals(jun)) {
        mesC = 5;
    }
    if (mesS.equals(jul)) {
        mesC = 6;
    }
    if (mesS.equals(ago)) {
        mesC = 7;
    }
    if (mesS.equals(sep)) {
        mesC = 8;
    }
    if (mesS.equals(oct)) {
        mesC = 9;
    }
    if (mesS.equals(nov)) {
        mesC = 10;
    }
    if (mesS.equals(dic)) {
        mesC = 11;
    }
    Calendar fechaColor = Calendar.getInstance();
    fechaColor.set(añoC, mesC, diaC);

    evt.putExtra("feD", fechaColor.get(Calendar.DAY_OF_MONTH));
    evt.putExtra("feM", fechaColor.get(Calendar.MONTH));
    evt.putExtra("feA", fechaColor.get(Calendar.YEAR));
    evt.putExtra("feH", Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
    evt.putExtra("feMe", Calendar.getInstance().get(Calendar.MINUTE));
    evt.putExtra("imagen", -1);
    evt.putExtra("fondo",0);
    evt.putExtra("texto", "");
    evt.putExtra("id", -1);

    startActivity(evt);

}

 /*   public  void baseDatos() throws IOException {
        if(isSDCardAvailable(this)==true){

        String pathData = "//data//"+getPackageName()+"//databases//notas";
        String backupDBPath = "backupdatabase.db";
        File data = Environment.getDataDirectory();
        //File sd = Environment.getExternalStorageDirectory();
        File sd = new File(System.getenv("SECONDARY_STORAGE"));
            Log.e("error", sd.toString()+" antes de guardar");
        File currentDB = new File(data, pathData );
        File backupDB = new File(sd, backupDBPath);
        if (currentDB.exists()) {
            FileChannel src = new FileInputStream(currentDB).getChannel();
            FileChannel dst = new FileOutputStream(backupDB).getChannel();
            dst.transferFrom(src, 0, src.size());
            Log.e("error", sd+" correcto");
            src.close();
            dst.close();


            }
        }




    }*/
   /* public static boolean isSDCardAvailable(Context context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);
        if (storages.length > 1 && storages[0] != null && storages[1] != null)
            return true;
        else
            Log.e("error", " no hay tarjeta");
            return false;
    }

   public static void permisos(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
}*/
}

