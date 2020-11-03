package matope.simarro.pmdm_t3p9_torres_marcos;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;

public class CuentasAdapter<T> extends Cuenta {

    private ArrayList<Cuenta> cuentas;

    public CuentasAdapter(CuentasActivity cuentasActivity, ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public int getCount() {
        return cuentas.size();
    }

    public Object getItem(int position) {
        return cuentas.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        CuentasView view;
        if (convertView == null)
            view = new CuentasView(parent.getContext());
        else
            view = (CuentasView) convertView;

        view.setCuentas(cuentas.get(position));

        return view;
    }
}