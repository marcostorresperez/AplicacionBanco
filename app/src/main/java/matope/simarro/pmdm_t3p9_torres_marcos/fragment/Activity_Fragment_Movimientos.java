package matope.simarro.pmdm_t3p9_torres_marcos.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
        TextView texto = (TextView) getView().findViewById(R.id.txtNoMovs);
        TextView texto2 = (TextView) getView().findViewById(R.id.txtNoMovs2);
        lstDetalle = (ListView) getView().findViewById(R.id.LstDetalle);
        lstDetalle.setAdapter(new MovimientosAdapter(this, lista));

        if (lstDetalle.getAdapter().isEmpty()) {
            texto.setVisibility(View.VISIBLE);
            texto2.setVisibility(View.VISIBLE);
        } else {
            texto.setVisibility(View.GONE);
            texto2.setVisibility(View.GONE);
        }

        lstDetalle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                Movimiento movimiento = (Movimiento) lstDetalle.getAdapter().getItem(pos);
                String texto = movimiento.getDescripcion() + "\n" + "Origen" + movimiento.getCuentaOrigen() + "\n" + "Destino: " + movimiento.getCuentaDestino() + "\n" + "Importe: " + movimiento.getImporte();


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.dialogs);
                LayoutInflater inflater= getActivity().getLayoutInflater();
                builder.setMessage(texto);
                builder.setTitle("Info Movimiento");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
