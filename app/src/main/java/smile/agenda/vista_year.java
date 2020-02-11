package smile.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class vista_year extends AppCompatActivity implements CalenAnual.OnFragmentInteractionListener, View.OnClickListener {


    private baseDatos conexion = new baseDatos(this, "notas", null, 1);

    private Calendar fecha,hoy ;
    private  int contar;
    private CalenAnual fragment1, fragment2,fragment3,fragment4,fragment5,fragment6;
    private float firstTouchX, actualX;
    private TextView txtBarrraAño;
    private Button btnBarraDiaAño, btnMes;
    private Intent mes;
    private FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mes = new Intent(this,calendario.class);
        setContentView(R.layout.activity_vista_year);
        txtBarrraAño =(TextView)findViewById(R.id.txtBarraAño);
        btnBarraDiaAño =(Button) findViewById(R.id.btnBarraDiaAño);
        btnMes =(Button) findViewById(R.id.btnMes);
        btnBarraDiaAño .setOnClickListener(this);
        btnMes.setOnClickListener(this);


        fecha = Calendar.getInstance();
        hoy =Calendar.getInstance();


        if(fecha.get(Calendar.MONTH)<=5){
            contar=0;

            fecha.set(fecha.get(Calendar.YEAR),0,1);
        }
        else{
            contar=6;
            fecha.set(fecha.get(Calendar.YEAR),6,1);
        }




        FragmentManager fragmentManager = getSupportFragmentManager();
       fragmentTransaction = fragmentManager.beginTransaction();






        fragment1 = new CalenAnual(fecha.get(Calendar.YEAR),contar);

        contar =contar+1;

         fragment2 = new CalenAnual(fecha.get(Calendar.YEAR),contar);

        contar =contar+1;
        fecha.set(fecha.get(Calendar.YEAR),contar,1);
        fragment3 = new CalenAnual(fecha.get(Calendar.YEAR),contar);

        contar =contar+1;
        fecha.set(fecha.get(Calendar.YEAR),contar,1);
         fragment4 = new CalenAnual(fecha.get(Calendar.YEAR),contar);

        contar =contar+1;
        fecha.set(fecha.get(Calendar.YEAR),contar,1);
         fragment5 = new CalenAnual(fecha.get(Calendar.YEAR),contar);

        contar =contar+1;
        fecha.set(fecha.get(Calendar.YEAR),contar,1);
         fragment6 = new CalenAnual(fecha.get(Calendar.YEAR),contar);

        btnBarraDiaAño.setText(String.valueOf(hoy.get(Calendar.DAY_OF_MONTH)));
        txtBarrraAño.setText(String.valueOf(fecha.get(Calendar.YEAR)));
        fragmentTransaction.add(R.id.f1_1, fragment1);
        fragmentTransaction.add(R.id.f1_2, fragment2);
        fragmentTransaction.add(R.id.f2_1, fragment3);
        fragmentTransaction.add(R.id.f2_2, fragment4);
        fragmentTransaction.add(R.id.f3_1, fragment5);
        fragmentTransaction.add(R.id.f3_2, fragment6);
        fragmentTransaction.commit();

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
                fecha.add((Calendar.MONTH),6);
                actualizar(fecha);
                Log.e("movimiento","fecha +:"+fecha.get(Calendar.MONTH));
            }
            else{
                Log.e("movimiento","pulsa");
            }
        }
        else if(firstTouchX < actualX){
            //resta meses
            if(actualX - firstTouchX>=350){
                fecha.add((Calendar.MONTH),-6);
                actualizar(fecha);
                Log.e("movimiento","fecha -:"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR));
            }
            else{
                Log.e("movimiento","pulsa");
            }
        }
        firstTouchX=0;
        actualX =0;
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    public void actualizar(Calendar f){
        if(f.get(Calendar.MONTH)<=5){
            contar=0;

            f.set(f.get(Calendar.YEAR),0,1);
        }
        else{
            contar=6;
            f.set(f.get(Calendar.YEAR),6,1);

        }

        fragment1.actualizarVista(f.get(Calendar.YEAR),f.get(Calendar.MONTH));
        fragment2.actualizarVista(f.get(Calendar.YEAR),f.get(Calendar.MONTH)+1);
        fragment3.actualizarVista(f.get(Calendar.YEAR),f.get(Calendar.MONTH)+2);
        fragment4.actualizarVista(f.get(Calendar.YEAR),f.get(Calendar.MONTH)+3);
        fragment5.actualizarVista(f.get(Calendar.YEAR),f.get(Calendar.MONTH)+4);
        fragment6.actualizarVista(f.get(Calendar.YEAR),f.get(Calendar.MONTH)+5);


        txtBarrraAño.setText(String.valueOf(f.get(Calendar.YEAR)));
        fragmentTransaction.hide(fragment1);
        fragmentTransaction.hide(fragment2);
        fragmentTransaction.hide(fragment3);
        fragmentTransaction.hide(fragment4);
        fragmentTransaction.hide(fragment5);
        fragmentTransaction.hide(fragment6);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBarraDiaAño:

             actualizar(hoy);
                break;


            case R.id.btnMes:

                startActivity(mes.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                finish();
                break;
    }}




}


