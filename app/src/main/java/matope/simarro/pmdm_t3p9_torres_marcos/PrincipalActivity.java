package matope.simarro.pmdm_t3p9_torres_marcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;

public class PrincipalActivity extends AppCompatActivity {

    private TextView nombre;
    private Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);
        Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        nombre = findViewById(R.id.txtNombre);
        nombre.setText(cliente.getNif());

    }

    public void btnCerrar(View v) {
        Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void btnClave(View v) {
        Cliente cliente= (Cliente) getIntent().getSerializableExtra("cliente");
        Intent intent = new Intent(PrincipalActivity.this, ClaveActivity.class);
        intent.putExtra("cliente", cliente);
        startActivity(intent);
    }

    public void btnTransferencias(View v) {
        Intent intent = new Intent(PrincipalActivity.this, TransferenciasActivity.class);
        startActivity(intent);
    }

    public void btnCuentas(View v){
        Cliente cliente= (Cliente) getIntent().getSerializableExtra("cliente");
        Intent intent= new Intent(PrincipalActivity.this,CuentasActivity.class);
        intent.putExtra("cliente", cliente);
        startActivity(intent);
    }


}