package matope.simarro.pmdm_t3p9_torres_marcos;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;

public class CuentasView extends ListView {
    private TextView numCuenta;
    private TextView banco;
    private TextView saldo;

    public CuentasView(Context context) {
        super(context);
        inflate(context, R.layout.cuentasview, this);

        numCuenta = (TextView) findViewById(R.id.numCuentas);
        banco = (TextView) findViewById(R.id.numBanco);
        saldo = (TextView) findViewById(R.id.numSaldo);
    }

    public void setCuentas(Cuenta cuenta) {
        numCuenta.setText("" + cuenta.getNumeroCuenta());
        banco.setText("" + cuenta.getBanco());
        saldo.setText("" + cuenta.getSaldoActual());
    }
}
