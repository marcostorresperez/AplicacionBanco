package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.ArrayList;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.fragment.Activity_Fragment_Movimientos;
import matope.simarro.pmdm_t3p9_torres_marcos.otros.Locales;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class ActivityCuentasDetalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String locale = sp.getString("pais", null);
        if(locale!=null){
            Locales.cambiarIdioma(this,locale);
        }

        setContentView(R.layout.layout_activity_movimientos);

        Activity_Fragment_Movimientos movimientos = (Activity_Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        movimientos.mostrarMovimiento((ArrayList<Movimiento>) getIntent().getSerializableExtra("ListaMovs"));
    }
}