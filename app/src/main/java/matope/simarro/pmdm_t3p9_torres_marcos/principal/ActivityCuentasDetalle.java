package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.fragment.Activity_Fragment_Movimientos;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class ActivityCuentasDetalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_movimientos);

        Activity_Fragment_Movimientos movimientos = (Activity_Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        movimientos.mostrarMovimiento((ArrayList<Movimiento>) getIntent().getSerializableExtra("ListaMovs"));
    }
}