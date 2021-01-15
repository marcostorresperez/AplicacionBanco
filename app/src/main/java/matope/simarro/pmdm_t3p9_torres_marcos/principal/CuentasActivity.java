package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.ArrayList;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.fragment.Activity_Fragment_Cuentas;
import matope.simarro.pmdm_t3p9_torres_marcos.fragment.Activity_Fragment_Movimientos;
import matope.simarro.pmdm_t3p9_torres_marcos.fragment.CuentasListener;
import matope.simarro.pmdm_t3p9_torres_marcos.otros.Locales;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class CuentasActivity extends AppCompatActivity implements CuentasListener {
    MiBancoOperacional api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String locale = sp.getString("pais", null);
        if(locale!=null){
            Locales.cambiarIdioma(this,locale);
        }

        setContentView(R.layout.layout_activity_cuentas);
        api = MiBancoOperacional.getInstance(this);
        ArrayList<Cuenta> listaCuentas = api.getCuentas((Cliente) getIntent().getSerializableExtra("cliente"));

        Activity_Fragment_Cuentas cuenta = (Activity_Fragment_Cuentas) getSupportFragmentManager().findFragmentById(R.id.FrgListado);
        cuenta.setCuentasListener((CuentasListener) this);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Cuentas", listaCuentas);
        cuenta.setArguments(bundle);
    }

    @Override
    public void onCuentaSeleccionada(Cuenta cuenta) {
        boolean hayMovimiento = (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);

        ArrayList<Movimiento> listaMovimientos = api.getMovimientos(cuenta);

        if (hayMovimiento) {
            ((Activity_Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle)).mostrarMovimiento(listaMovimientos);
        } else {
            Intent i = new Intent(this, ActivityCuentasDetalle.class);
            i.putExtra("ListaMovs", listaMovimientos);
            startActivity(i);
        }
    }

}