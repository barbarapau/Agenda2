package smile.agenda;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Yo on 15/08/2017.
 */

public class baseDatos extends SQLiteOpenHelper {


    public baseDatos(Context contexto, String nombre,
                     SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    /**
     * Crea las dos bases tablas de la base de datos
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE tabla(id INTEGER PRIMARY KEY AUTOINCREMENT, ano INTEGER , mes INTEGER, dia INTEGER , " +
                "texto VARCHAR(200), imagen INTEGER, hora INTEGER, minutos INTEGER, fondo INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {

        }

    }


    public void anadirEvento(SQLiteDatabase db, Calendar fecha, String texto, int imagen, int fondo) {
        String t = escaparCaracter(texto);
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora =fecha.get(Calendar.HOUR_OF_DAY);
        int minutos= fecha.get(Calendar.MINUTE);
        String consulta = "INSERT INTO tabla VALUES(null," + año + "," + mes + "," + dia + ",'" + t + "'," + imagen + ","+hora+","+minutos+","+fondo+")";
        db.execSQL(consulta);

    }

    public void borrarEvento(SQLiteDatabase db, int idEvento) {
        String consulta = "Delete from tabla WHERE id=" + idEvento;
        db.execSQL(consulta);
    }

    public void modificarEvento(SQLiteDatabase db, int idEvento, String texto, int imagen, Calendar fecha, int fondo) {
        String t = escaparCaracter(texto);
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora =fecha.get(Calendar.HOUR_OF_DAY);
        int minutos= fecha.get(Calendar.MINUTE);
        String consulta2 = "UPDATE tabla SET texto ='"+t+"', imagen="+imagen+",ano="+año+",mes="+mes+"," +
                "dia="+dia+",hora="+hora+", minutos="+minutos+",fondo="+fondo+ " WHERE id=" + idEvento;

        db.execSQL(consulta2);
    }

    public ArrayList<Evento> consultarEventoFecha(SQLiteDatabase db, Calendar fecha) {
        ArrayList<Evento> lista = new ArrayList<>();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        String consulta = "Select * from tabla WHERE ano=" + año + " AND mes=" + mes + " AND dia=" + dia;
        Cursor cursor = db.rawQuery(consulta, null);
        while (cursor.moveToNext()) {
            Calendar nue = Calendar.getInstance();
            nue.set(año, mes, cursor.getInt(3), cursor.getInt(6),cursor.getInt(7));
            lista.add(new Evento(cursor.getInt(0), nue, cursor.getString(4), cursor.getInt(5), cursor.getInt(8)));
        }

        return lista;
    }

    public ArrayList<Evento> consultarEventosMes(SQLiteDatabase db, Calendar fecha) {
        ArrayList<Evento> lista = new ArrayList<>();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);

        String consulta = "Select * from tabla WHERE ano=" + año + " AND mes=" + mes + " ORDER BY dia";
        Cursor cursor = db.rawQuery(consulta, null);
        while (cursor.moveToNext()) {
            Calendar nueva = Calendar.getInstance();
            nueva.set(año, mes, cursor.getInt(3), cursor.getInt(6),cursor.getInt(7));
            lista.add(new Evento(cursor.getInt(0), nueva, cursor.getString(4), cursor.getInt(5), cursor.getInt(8)));

        }

        return lista;
    }

    public boolean hayEvento(SQLiteDatabase db, Calendar fecha) {
        boolean hay = false;
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora =fecha.get(Calendar.HOUR_OF_DAY);
        int minutos =fecha.get(Calendar.MINUTE);

        String consulta = "select count(*) from tabla  WHERE ano=" + año + " AND mes=" + mes + " AND dia=" + dia +" AND hora=" + hora +" AND minutos=" + minutos ;;
        Cursor cursor = db.rawQuery(consulta, null);
        while (cursor.moveToNext()) {
            int numero = cursor.getInt(0);

            if (numero > 0) {
                hay = true;
            }
        }

        return hay;

    }

    public String hayEventoHora(SQLiteDatabase db, Calendar fecha) {
        String texto =null;
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora =fecha.get(Calendar.HOUR_OF_DAY);
        int minutos =fecha.get(Calendar.MINUTE);
        String consulta = "select texto from tabla  WHERE ano=" + año + " AND mes=" + mes + " AND dia=" + dia +" AND hora=" + hora +" AND minutos=" + minutos ;
        Cursor cursor = db.rawQuery(consulta, null);
        while (cursor.moveToNext()) {
            texto = cursor.getString(0);

        }

        return texto;

    }
    public void anadirEventoVarios(SQLiteDatabase db, String texto, int imagen, ArrayList<Calendar>lista,int fondo) {
        String t = escaparCaracter(texto);
        for(int i =0;i<lista.size();i++){
            int a=lista.get(i).get(Calendar.YEAR);
            int m=lista.get(i).get(Calendar.MONTH);
            int d=lista.get(i).get(Calendar.DAY_OF_MONTH);
            int hora =lista.get(i).get(Calendar.HOUR_OF_DAY);
            int minutos= lista.get(i).get(Calendar.MINUTE);

            String consulta = "INSERT INTO tabla VALUES(null," + a + "," + m + "," + d + ",'" + t + "'," + imagen + ","+hora+","+minutos+","+fondo+")";
            db.execSQL(consulta);
        }

    }
    public static String escaparCaracter(String palabra){
        String texto = palabra;
        String creando="";

        //compruebo si contiene comillas
        if(palabra.contains("'")) {
            char[] dividida = texto.toCharArray();
            //compruebo en que posiciones estan las comillas y guardo las posiciones en un array
            ArrayList<Integer> posiciones = new ArrayList<>();
            for (int i = 0; i < texto.length(); i++) {
                if (String.valueOf(dividida[i]).equals("'")) {
                    posiciones.add(i);
                }


            }
            //recorro de nuevo el array para insertar la segunda comilla en las posicione
            for (int i = 0; i < palabra.length(); i++) {

                if (posiciones.contains(i)) {
                    creando = creando + "''";
                } else {
                    creando = creando + dividida[i];
                }
                texto = creando;
            }
        }

        return texto;


    }
  /*  //metodo para crear copia de la base de datos
    public void backupdDatabase(){
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            String packageName  = "com.yourapp.package";
            String sourceDBName = "mydb.db";
            String targetDBName = "mydb";
            if (sd.canWrite()) {
                Date now = new Date();
                String currentDBPath = "data/" + packageName + "/databases/" + sourceDBName;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                String backupDBPath = targetDBName + dateFormat.format(now) + ".db";

                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                Log.i("backup","backupDB=" + backupDB.getAbsolutePath());
                Log.i("backup","sourceDB=" + currentDB.getAbsolutePath());

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        } catch (Exception e) {
            Log.i("Backup", e.toString());
        }
    }*/
}

