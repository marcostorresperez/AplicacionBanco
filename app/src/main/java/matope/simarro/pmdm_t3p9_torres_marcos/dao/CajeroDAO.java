package matope.simarro.pmdm_t3p9_torres_marcos.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import matope.simarro.pmdm_t3p9_torres_marcos.bd.Constantes;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBD;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;

public class CajeroDAO{
    private Context contexto;
  private MiBD dbHelper;
    private SQLiteDatabase db;

    public CajeroDAO(Context context) {
        this.contexto=context;
    }

    public CajeroDAO abrir(){
        db= dbHelper.getWritableDatabase();
        return this;
    }
    public void cerrar() {
        dbHelper.close();
    }

    public Cursor getCursor() {
        Cursor c = db.query( true, Constantes.CAJEROS_TABLE, Constantes.CAMPOS_CAJEROS, null, null, null, null, null, null);
        return c;
    }

}
