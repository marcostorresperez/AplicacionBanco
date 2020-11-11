package matope.simarro.pmdm_t3p9_torres_marcos.adaptador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class MovimientosAdapter extends ArrayAdapter<Movimiento> {
    Activity context;
    ArrayList<Movimiento> listaMovimientos;

    public MovimientosAdapter(Fragment context, ArrayList<Movimiento> listaMovimientos) {
        super(context.getActivity(),R.layout.movimientosview,listaMovimientos);
   this.context=context.getActivity();
   this.listaMovimientos=listaMovimientos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.movimientosview,null);

        TextView numFecha = item.findViewById(R.id.numFecha);
        TextView numDescripcion = item.findViewById(R.id.numDescripcion);
        TextView numImporte = item.findViewById(R.id.numImporte);

        Movimiento movimiento = (Movimiento) getItem(position);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        numFecha.setText(sdf.format(movimiento.getFechaOperacion()));
        numDescripcion.setText(String.valueOf(movimiento.getDescripcion()));
        if (movimiento.getImporte() <= 0) {
            numImporte.setTextColor(Color.RED);
        } else {
            numImporte.setTextColor(Color.GREEN);
        }
        numImporte.setText(String.valueOf(movimiento.getImporte()));

        return item;
    }
}