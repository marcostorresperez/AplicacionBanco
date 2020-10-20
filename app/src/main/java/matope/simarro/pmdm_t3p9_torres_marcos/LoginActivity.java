package matope.simarro.pmdm_t3p9_torres_marcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtUsuario, txtContra;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario = findViewById(R.id.txtUser);
        txtContra= findViewById(R.id.textPass);

    }

    public void sePulsa(View v) {
        nombre = txtUsuario.getText().toString();

        Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
        intent.putExtra("nombre", nombre);

        startActivity(intent);
    }
}