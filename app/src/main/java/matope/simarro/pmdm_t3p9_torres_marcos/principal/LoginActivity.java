package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;


public class LoginActivity extends AppCompatActivity {
    public static final String REGEX_DNI = "^[0-9]{8,8}[A-Za-z]$";

    private Button btnLogin;
    private EditText txtUsuario, txtContra;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        txtUsuario = findViewById(R.id.txtUser);
        txtContra = findViewById(R.id.textPass);


    }

    public void sePulsa(View v) {
        if (!txtUsuario.getText().toString().isEmpty() && !txtContra.getText().toString().isEmpty()) {
            Cliente cliente = new Cliente();
            cliente.setNif(txtUsuario.getText().toString());
            cliente.setClaveSeguridad(txtContra.getText().toString());
            MiBancoOperacional api = MiBancoOperacional.getInstance(getApplicationContext());
            Cliente resultado = api.login(cliente);
            if (resultado != null) {
                Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                intent.putExtra("cliente", resultado);
                startActivity(intent);
            } else
                Toast.makeText(getApplicationContext(), "Datos de inicio de sesion incorrectos", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();

    }
}