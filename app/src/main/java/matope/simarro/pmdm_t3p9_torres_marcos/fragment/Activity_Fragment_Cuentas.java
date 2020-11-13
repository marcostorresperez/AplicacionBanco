package matope.simarro.pmdm_t3p9_torres_marcos.fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;


import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.adaptador.CuentasAdapter;
import matope.simarro.pmdm_t3p9_torres_marcos.adaptador.MovimientosAdapter;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class Activity_Fragment_Cuentas extends Fragment {
    private ArrayList<Cuenta> cuentas = new ArrayList<>();
    private ListView lstListado;
    private CuentasListener listener;
    private ListView origen, listaMovs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_cuentas, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        cuentas = (ArrayList<Cuenta>) getArguments().getSerializable("Cuentas");

        lstListado = (ListView) getView().findViewById(R.id.LstListado);


        lstListado.setAdapter(new CuentasAdapter(this, cuentas));

        lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (listener != null) {
                    listener.onCuentaSeleccionada(
                            ((Cuenta) lstListado.getAdapter().getItem(pos)));
                }
            }
        });

    }

    public void setCuentasListener(CuentasListener listener) {
        this.listener = listener;
    }
}
