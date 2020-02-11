package smile.agenda;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by Yo on 18/08/2017.
 */

public class Imagen {
    private int id;
    private Drawable imagLista;
    private Drawable imagBoton;
    private int int_imagLista, int_imagBoton;

    public Imagen(int id, Drawable imagLista, Drawable imagBoton) {
        this.id = id;
        this.imagLista = imagLista;// pequeña
        this.imagBoton = imagBoton; //grande
    }
    public Imagen(int id, int int_imagLista, int int_imagBoton) {
        this.id = id;
        this.int_imagLista = int_imagLista;
        this.int_imagBoton = int_imagBoton;
    }
    public Imagen(){

    }

    public int getInt_imagLista() {
        return int_imagLista;
    }

    public void setInt_imagLista(int int_imagLista) {
        this.int_imagLista = int_imagLista;
    }

    public int getInt_imagBoton() {
        return int_imagBoton;
    }

    public void setInt_imagBoton(int int_imagBoton) {
        this.int_imagBoton = int_imagBoton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getImagLista() {
        return imagLista;
    }

    public void setImagLista(Drawable imagLista) {
        this.imagLista = imagLista;
    }

    public Drawable getImagBoton() {
        return imagBoton;
    }

    public void setImagBoton(Drawable imagBoton) {
        this.imagBoton = imagBoton;
    }


    public ArrayList<Imagen> listaInteger(){
        ArrayList<Imagen> listaImg = new ArrayList<>();
        //el id=0  es posicion sin imagen
        listaImg.add(new Imagen(1,(R.mipmap.nube),(R.mipmap.nube_p)));
        listaImg.add(new Imagen(2,(R.mipmap.gafas),(R.mipmap.gafas_p)));
        listaImg.add(new Imagen(3,(R.mipmap.sol),(R.mipmap.sol_p)));
        listaImg.add(new Imagen(4,(R.mipmap.pajita),(R.mipmap.pajita_p)));
        listaImg.add(new Imagen(5,(R.mipmap.copa),(R.mipmap.copa_p)));
        listaImg.add(new Imagen(6,(R.mipmap.bici),(R.mipmap.bici_p)));
        listaImg.add(new Imagen(7,(R.mipmap.furgo),(R.mipmap.furgo_p)));
        listaImg.add(new Imagen(8,(R.mipmap.guitarra),(R.mipmap.guitarra_p)));
        listaImg.add(new Imagen(9,(R.mipmap.moto),(R.mipmap.moto_p)));
        listaImg.add(new Imagen(10,(R.mipmap.medico),(R.mipmap.medico_p)));
        listaImg.add(new Imagen(11,(R.mipmap.bailarina),(R.mipmap.bailarina_p)));


        return listaImg;
    }

    public ArrayList<Integer> cargarFondoInteger(){

        ArrayList<Integer>lista = new ArrayList<>();
        lista.add(R.drawable.dia_con_evento) ;
        lista.add(R.drawable.dia_con_evento_a);
        lista.add(R.drawable.dia_con_evento_b);
        lista.add(R.drawable.dia_con_evento_c);
        lista.add(R.drawable.dia_con_evento_d);
        lista.add(R.drawable.dia_con_evento_f);
        lista.add(R.drawable.dia_con_evento_i);
        lista.add(R.drawable.dia_con_evento_l);

//8-14 son oscuros
        lista.add(R.drawable.dia_con_evento_o);
        lista.add(R.drawable.dia_con_evento_g);
        lista.add(R.drawable.dia_con_evento_n);
        lista.add(R.drawable.dia_con_evento_k);
        lista.add(R.drawable.dia_con_evento_j);
        lista.add(R.drawable.dia_con_evento_h);
        lista.add(R.drawable.dia_con_evento_e);

        lista.add(R.drawable.dia_con_evento_p);
        lista.add(R.drawable.dia_con_evento_q);
        lista.add(R.drawable.dia_con_evento_r);
        lista.add(R.drawable.dia_con_evento_s);
        lista.add(R.drawable.dia_con_evento_t);
        lista.add(R.drawable.dia_con_evento_u);

        return  lista;
    }

    public ArrayList<Integer> cargarColorCalendario(){
        ArrayList<Integer> colores = new ArrayList<>();
        colores.add((R.color.e_n));
        colores.add((R.color.f_n));
        colores.add((R.color.m_n));
        colores.add((R.color.a_n));
        colores.add((R.color.my_n));
        colores.add((R.color.j_n));
        colores.add((R.color.jl_n));
        colores.add((R.color.ag_n));
        colores.add((R.color.s_n));
        colores.add((R.color.o_n));
        colores.add((R.color.nv_n));
        colores.add((R.color.d_n));
        return colores;
    }

}
