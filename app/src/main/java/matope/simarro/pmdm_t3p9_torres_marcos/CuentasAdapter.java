package matope.simarro.pmdm_t3p9_torres_marcos;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;

public class CuentasAdapter<T> extends ArrayAdapter<T> {

    private ArrayList<Cuenta> cuentas;

    public CuentasAdapter(@NonNull Context context, List<T> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = convertView;

        if (null == convertView) {
            listItemView = inflater.inflate(R.layout.cuentasview, parent, false);
        }

        TextView numCuentas = listItemView.findViewById(R.id.numFecha);
        TextView numSaldo = listItemView.findViewById(R.id.numDescripcion);
        TextView numBanco = listItemView.findViewById(R.id.numImporte);

        Cuenta cuenta = (Cuenta) getItem(position);

        numCuentas.setText(cuenta.getNumeroCuenta());
        numSaldo.setText(String.valueOf(cuenta.getSaldoActual()));
        if(cuenta.getSaldoActual()<= 0){
            numSaldo.setTextColor(Color.RED);
        }else{
            numSaldo.setTextColor(Color.GREEN);
        }
        numBanco.setText(cuenta.getBanco());

        return listItemView;
    }
}