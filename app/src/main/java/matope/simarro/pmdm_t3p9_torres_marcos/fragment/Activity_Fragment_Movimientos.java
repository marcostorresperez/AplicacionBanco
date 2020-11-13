package matope.simarro.pmdm_t3p9_torres_marcos.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.adaptador.MovimientosAdapter;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class Activity_Fragment_Movimientos extends Fragment {
    private ListView lstDetalle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_movimientos, container, false);
    }

    public void mostrarMovimiento(ArrayList<Movimiento> lista) {
        lstDetalle = (ListView) getView().findViewById(R.id.LstDetalle);
        lstDetalle.setAdapter(new MovimientosAdapter(this, lista));

        lstDetalle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                Movimiento movimiento = (Movimiento) lstDetalle.getAdapter().getItem(pos);
                String texto = movimiento.getDescripcion() + "\n" + "Origen" + movimiento.getCuentaOrigen() + "\n" + "Destino: " + movimiento.getCuentaDestino() + "\n" + "Importe:_" + movimiento.getImporte();
                Toast.makeText(getContext(), texto, Toast.LENGTH_LONG).show();
            }
        });
    }
}
