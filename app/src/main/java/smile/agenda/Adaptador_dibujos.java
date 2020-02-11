package smile.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class Adaptador_dibujos extends BaseAdapter {

    private Context context;
    private ArrayList<Imagen> lista;
    private ImageView sele;
    private View celda;
    private int posiSele;
    private Button img;
    private boolean pulsado;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        celda = inflater.inflate(R.layout.adaptador_dibujos,null);
        img = celda.findViewById(R.id.btnAdpImagen);
        img.setBackground(lista.get(position).getImagLista());

        return celda;
    }
}
