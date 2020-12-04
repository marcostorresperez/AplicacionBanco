package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;

public class PrincipalActivity extends AppCompatActivity {

    private TextView nombre;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        nombre = findViewById(R.id.txtNombre);
        nombre.setText("Bienvenido: " + cliente.getNombre());

    }

    public void btnCerrar(View v) {
        Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void btnClave(View v) {
        Intent intent = new Intent(PrincipalActivity.this, ClaveActivity.class);
        intent.putExtra("cliente", cliente);
        startActivity(intent);
    }

    public void btnTransferencias(View v) {

        Intent intent = new Intent(PrincipalActivity.this, TransferenciasActivity.class);
        intent.putExtra("cliente", cliente);
        startActivity(intent);
    }

    public void btnCuentas(View v) {

        Intent intent = new Intent(PrincipalActivity.this, CuentasActivity.class);
        intent.putExtra("cliente", cliente);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case R.id.cajeros:
                return true;
            case R.id.descuentos:
                return true;
            case R.id.clave:
                intent = new Intent(PrincipalActivity.this, ClaveActivity.class);
                intent.putExtra("cliente", cliente);
                startActivity(intent);
                return true;
            case R.id.cuentas:
                intent = new Intent(PrincipalActivity.this, CuentasActivity.class);
                intent.putExtra("cliente", cliente);
                startActivity(intent);
                return true;
            case R.id.transferencias:
                intent = new Intent(PrincipalActivity.this, TransferenciasActivity.class);
                intent.putExtra("cliente", cliente);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}