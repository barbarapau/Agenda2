package smile.agenda;


import java.util.Calendar;

/**
 * Created by Yo on 15/08/2017.
 */

public class Evento {
    private int id;
    private Calendar fecha;
    private String texto;
    private int imagen;
    private int fondo;

    public Evento(int id, Calendar fecha, String texto, int imagen, int fondo) {
        this.id = id;
        this.fecha = fecha;
        this.texto = texto;
        this.imagen = imagen;
        this.fondo = fondo;
    }

    public int getId() {
        return id;
    }

    public int getFondo() {

        return fondo;
    }

    public void setFondo(int fondo) {
        this.fondo = fondo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
