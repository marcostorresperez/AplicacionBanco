package matope.simarro.pmdm_t3p9_torres_marcos.adaptador;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;

public class CuentasAdapter extends ArrayAdapter<Cuenta> {

    Activity context;
    ArrayList<Cuenta> listaCuentas;

    public CuentasAdapter(Fragment context, ArrayList<Cuenta> listaCuentas) {
        super(context.getActivity(), R.layout.layout_elemento_cuenta_lista, listaCuentas);
        this.context = context.getActivity();
        this.listaCuentas = listaCuentas;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_elemento_cuenta_lista, null);

        TextView numCuentas = item.findViewById(R.id.numFecha);
        TextView numSaldo = item.findViewById(R.id.numDescripcion);
        TextView numBanco = item.findViewById(R.id.numImporte);

        Cuenta cuenta = (Cuenta) getItem(position);

        numCuentas.setText(cuenta.getNumeroCuenta());
        numSaldo.setText(String.valueOf(cuenta.getSaldoActual()));
        if (cuenta.getSaldoActual() <= 0) {
            numSaldo.setTextColor(Color.RED);
        } else {
            numSaldo.setTextColor(Color.GREEN);
        }
        numBanco.setText(cuenta.getBanco());

        return (item);
    }
}