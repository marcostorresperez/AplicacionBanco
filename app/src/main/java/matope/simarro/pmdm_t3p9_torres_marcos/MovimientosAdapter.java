package matope.simarro.pmdm_t3p9_torres_marcos;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class MovimientosAdapter<T> extends ArrayAdapter<T> {

    private ArrayList<Movimiento> movimientos;

    public MovimientosAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = convertView;

        if (null == convertView) {
            listItemView = inflater.inflate(R.layout.cuentasview, parent, false);
        }

        TextView numFecha = listItemView.findViewById(R.id.numFecha);
        TextView numDescripcion = listItemView.findViewById(R.id.numDescripcion);
        TextView numImporte = listItemView.findViewById(R.id.numImporte);

        Movimiento movimiento = (Movimiento) getItem(position);

        numFecha.setText((CharSequence) movimiento.getFechaOperacion());
        numDescripcion.setText(String.valueOf(movimiento.getDescripcion()));
        if (movimiento.getImporte() <= 0) {
            numImporte.setTextColor(Color.RED);
        } else {
            numImporte.setTextColor(Color.GREEN);
        }
        numImporte.setText(String.valueOf(movimiento.getImporte()));

        return listItemView;
    }
}