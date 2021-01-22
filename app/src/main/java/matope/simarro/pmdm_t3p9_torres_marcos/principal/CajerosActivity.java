package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.adaptador.CajerosCursorAdapter;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBD;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.dao.CajeroDAO;

public class CajerosActivity extends AppCompatActivity {
    private CajeroDAO cajerosDAO;

    private int modo;
    private long id;

    private EditText direccion;
    private EditText latitud;
    private EditText longitud;
    private EditText zoom;
    private EditText cajeros;
    
    private ListView lista;
    private CajerosCursorAdapter cajerosAdapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajeros);

        MiBD dbHelper = MiBD.getInstance(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();

    }
}