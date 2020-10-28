package matope.simarro.pmdm_t3p9_torres_marcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        Pattern patron = Pattern.compile(REGEX_DNI, Pattern.CASE_INSENSITIVE);
        Matcher mat = patron.matcher(txtUsuario.getText().toString());
        if (!mat.matches()) {

            Toast toast = Toast.makeText(getApplicationContext(), "Introduce un DNI válido", Toast.LENGTH_SHORT);
            toast.show();
        } else if (txtContra.length() != 4) {

            Toast toast = Toast.makeText(getApplicationContext(), "La clave debe ser de 4 dígitos", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            nombre = txtUsuario.getText().toString();

            Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
            intent.putExtra("nombre", nombre);
            startActivity(intent);
        }
    }
}