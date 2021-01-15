package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.otros.Audio;
import matope.simarro.pmdm_t3p9_torres_marcos.otros.Locales;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;

public class PrincipalActivity extends AppCompatActivity {

    private TextView nombre;
    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        String locale = sp.getString("pais", null);
        if (locale != null) {
            Locales.cambiarIdioma(this, locale);
        }


        boolean hayMusica = sp.getBoolean("reproducirMusica", false);
        if (hayMusica) {
            Intent i = new Intent(this, Audio.class);
            startService(i);
        }
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        nombre = findViewById(R.id.txtNombre);

        String alias = sp.getString("alias", null);
        if (alias == "") {
            nombre.setText(getString(R.string.welcomes) + cliente.getNombre());
        }else{
            nombre.setText(getString(R.string.welcomes) + alias);
        }



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
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        String alias = sp.getString("alias", null);
        if (alias == "") {
            nombre.setText(getString(R.string.welcomes) + cliente.getNombre());
        }else{
            nombre.setText(getString(R.string.welcomes) + " " + alias);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case R.id.configuracion:
                intent = new Intent(PrincipalActivity.this, PreferenciasActivity.class);
                intent.putExtra("cliente", cliente);
                startActivity(intent);
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