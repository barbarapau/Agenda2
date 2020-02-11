package smile.agenda;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


public class CalenAnual extends Fragment {
    private int year;
    private int mes;
    private ArrayList<TextView> lista;


    private ArrayList<Evento> eveMes;
    private TextView txt_nomMes;
    private ArrayList listaMes;
    private Calendar hoy;
    private LinearLayout vistaCalendarioMes;
    baseDatos conexion;
    SQLiteDatabase leer;

    ArrayList<Evento> eventosMes;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;

    private OnFragmentInteractionListener mListener;

    public CalenAnual(int year, int mes ) {
        this.mes= mes;
        this.year= year;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment CalenAnual.
     */
    // TODO: Rename and change types and number of parameters
    public static CalenAnual newInstance(int year, int mes) {
        CalenAnual fragment = new CalenAnual(year, mes);
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, mes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_calen_anual, null);

        lista= new ArrayList<>();
        listaMes = new ArrayList<String>();

        hoy = Calendar.getInstance();



        txt_nomMes =(TextView) vista.findViewById(R.id.txt_nomMes);


        vistaCalendarioMes = (LinearLayout)vista.findViewById(R.id.vistaCalendarioMes);
        lista.add(vista.findViewById(R.id.txt1_1));
        lista.add(vista.findViewById(R.id.txt1_2));
        lista.add(vista.findViewById(R.id.txt1_3));
        lista.add(vista.findViewById(R.id.txt1_4));
        lista.add(vista.findViewById(R.id.txt1_5));
        lista.add(vista.findViewById(R.id.txt1_6));
        lista.add(vista.findViewById(R.id.txt1_7));
        lista.add(vista.findViewById(R.id.txt2_1));
        lista.add(vista.findViewById(R.id.txt2_2));
        lista.add(vista.findViewById(R.id.txt2_3));
        lista.add(vista.findViewById(R.id.txt2_4));
        lista.add(vista.findViewById(R.id.txt2_5));
        lista.add(vista.findViewById(R.id.txt2_6));
        lista.add(vista.findViewById(R.id.txt2_7));
        lista.add(vista.findViewById(R.id.txt3_1));
        lista.add(vista.findViewById(R.id.txt3_2));
        lista.add(vista.findViewById(R.id.txt3_3));
        lista.add(vista.findViewById(R.id.txt3_4));
        lista.add(vista.findViewById(R.id.txt3_5));
        lista.add(vista.findViewById(R.id.txt3_6));
        lista.add(vista.findViewById(R.id.txt3_7));
        lista.add(vista.findViewById(R.id.txt4_1));
        lista.add(vista.findViewById(R.id.txt4_2));
        lista.add(vista.findViewById(R.id.txt4_3));
        lista.add(vista.findViewById(R.id.txt4_4));
        lista.add(vista.findViewById(R.id.txt4_5));
        lista.add(vista.findViewById(R.id.txt4_6));
        lista.add(vista.findViewById(R.id.txt4_7));
        lista.add(vista.findViewById(R.id.txt5_1));
        lista.add(vista.findViewById(R.id.txt5_2));
        lista.add(vista.findViewById(R.id.txt5_3));
        lista.add(vista.findViewById(R.id.txt5_4));
        lista.add(vista.findViewById(R.id.txt5_5));
        lista.add(vista.findViewById(R.id.txt5_6));
        lista.add(vista.findViewById(R.id.txt5_7));
        lista.add(vista.findViewById(R.id.txt6_1));
        lista.add(vista.findViewById(R.id.txt6_2));
        lista.add(vista.findViewById(R.id.txt6_3));
        lista.add(vista.findViewById(R.id.txt6_4));
        lista.add(vista.findViewById(R.id.txt6_5));
        lista.add(vista.findViewById(R.id.txt6_6));
        lista.add(vista.findViewById(R.id.txt6_7));


        listaMes.add(getResources().getString(R.string.ene));
        listaMes.add(getResources().getString(R.string.feb));
        listaMes.add(getResources().getString(R.string.mar));
        listaMes.add(getResources().getString(R.string.abr));
        listaMes.add(getResources().getString(R.string.may));
        listaMes.add(getResources().getString(R.string.jun));
        listaMes.add(getResources().getString(R.string.jul));
        listaMes.add(getResources().getString(R.string.ago));
        listaMes.add(getResources().getString(R.string.sep));
        listaMes.add(getResources().getString(R.string.oct));
        listaMes.add(getResources().getString(R.string.nov));
        listaMes.add(getResources().getString(R.string.dic));

        //cuando se pincha el mes, se muestra la pantalla calendario con el mes señalizado
       vistaCalendarioMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mesSele = new Intent(getActivity(), calendario.class);

                mesSele.putExtra("mesSele",mes );
                mesSele.putExtra("añoSele",year);
                startActivity(mesSele);
                getActivity().finish();

            }
        });


        //obtengo el dia de la semana del primer dia del mes



        //rellenar_calendario(year,mes);
        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        conexion= new baseDatos(getActivity(), "notas", null, 1);
          leer= conexion.getReadableDatabase();


        rellenar_calendario(year,mes);

        super.onActivityCreated(savedInstanceState);
    }



    public void rellenar_calendario(int a,int m){
        year=a;
        mes=m;
        Calendar pri = Calendar.getInstance();
        pri.set(year, mes, 1);


        pri.set(year, mes, 1);
        //ulti.set (year,mes,1);

        eveMes= conexion.consultarEventosMes(leer, pri);


        int diaSemana = pri.get(Calendar.DAY_OF_WEEK) - 2;
        if (diaSemana == -1) {
            diaSemana = 6;
        }


        int contador = 1;

        int me = pri.get(Calendar.MONTH);
        txt_nomMes.setText(listaMes.get(me).toString());
        txt_nomMes.setBackgroundColor(getResources().getColor(new Imagen().cargarColorCalendario().get(mes)));

        //obtengo el ultimo dia del mes
        int ultimoDia = pri.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        //eventos del mes

        //añado la numeracion del mes
        for (int i = diaSemana; contador <= ultimoDia; i++) {
            lista.get(i).setText(String.valueOf(contador));
                    //si es el dia actual se marca de rojo

            //recorro el arra de eventos
            //recorro el arra de eventos
            for (int w = 0; w < eveMes.size(); w++) {
                //recupero el dia de cada uno de los eventos
                java.util.Calendar g = eveMes.get(w).getFecha();
                int d = g.get(java.util.Calendar.DAY_OF_MONTH);
                //si el dia del evento es igual al dia del bucle
                if (d == contador) {
                    //si el dia tiene un evento se cambia el color a turquesa
                    lista.get(i).setTextColor(Color.parseColor("#047c6c"));

                }

            }
            if(mes==hoy.get(Calendar.MONTH )& contador== hoy.get(Calendar.DAY_OF_MONTH)){
                lista.get(i).setTextColor(getResources().getColor(R.color.rojo));
            }
            contador = contador + 1;
    }

}
public  void actualizarVista(int a,int m){
    //limpio el calendario
    for (int i = 0; i < lista.size(); i++) {
        lista.get(i).setText("");
        lista.get(i).setTextColor(Color.parseColor("#1D3B3A"));
    }
        rellenar_calendario(a,m);
        super.onResume();
}
}


