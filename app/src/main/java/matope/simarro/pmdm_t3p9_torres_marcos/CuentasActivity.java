package matope.simarro.pmdm_t3p9_torres_marcos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.dao.CuentaDAO;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class CuentasActivity extends AppCompatActivity {
    private ListView origen, listaMovs;
    private TextView seleccionado = null;
    CuentasAdapter<Cuenta> cuentas;
    MovimientosAdapter<Movimiento> movimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuentas);


        Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");
        MiBancoOperacional api = MiBancoOperacional.getInstance(getApplicationContext());

        origen = findViewById(R.id.cuentas);
        cuentas = new CuentasAdapter(this, api.getCuentas(cliente));
        origen.setAdapter((ListAdapter) cuentas);


        origen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView movs = findViewById(R.id.txtMovs);
                movs.setVisibility(View.VISIBLE);
                TextView txtNoMovs = findViewById(R.id.txtNoMovs);
                TextView txtNoMovs2 = findViewById(R.id.txtNoMovs2);

                Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");
                Cuenta cuenta = cuentas.getItem(i);
                MiBancoOperacional api = MiBancoOperacional.getInstance(getApplicationContext());
                listaMovs = findViewById(R.id.movimientos);
                movimientos = new MovimientosAdapter(getApplicationContext(), api.getMovimientos(cuenta));
                listaMovs.setAdapter((ListAdapter) movimientos);
                ArrayList<Movimiento> listaMovimientos = api.getMovimientos(cuenta);
                if (listaMovimientos.size() == 0) {
                    txtNoMovs.setVisibility(View.VISIBLE);
                    txtNoMovs2.setVisibility(View.VISIBLE);
                }else{
                    txtNoMovs.setVisibility(View.GONE);
                    txtNoMovs2.setVisibility(View.GONE);
                }

            }
        });


    }
}