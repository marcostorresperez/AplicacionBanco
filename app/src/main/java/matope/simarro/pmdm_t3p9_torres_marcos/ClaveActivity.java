package matope.simarro.pmdm_t3p9_torres_marcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClaveActivity extends AppCompatActivity {

    private EditText claveActual, claveNueva1, claveNueva2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);

        claveActual = findViewById(R.id.claveActual);
        claveNueva1 = findViewById(R.id.claveNueva1);
        claveNueva2 = findViewById(R.id.claveNueva2);
    }

    public void sePulsa(View v) {
        if (claveActual.getText().toString().equals("") || claveNueva1.getText().toString().equals("") || claveNueva2.getText().toString().equals("")) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos", Toast.LENGTH_SHORT);
            toast1.show();
        } else if (claveNueva1.getText().toString().equals(claveActual.getText().toString())) {
            claveNueva1.setText("");
            claveNueva2.setText("");
            Toast toast1 = Toast.makeText(getApplicationContext(), "La nueva clave no puede ser igual a la actual", Toast.LENGTH_SHORT);
            toast1.show();
        } else if (claveNueva1.getText().toString().equals(claveNueva2.getText().toString())) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Clave cambiada con éxito", Toast.LENGTH_SHORT);
            toast1.show();

            Intent intent = new Intent(ClaveActivity.this, PrincipalActivity.class);
            startActivity(intent);

        } else {
            Toast toast1 = Toast.makeText(getApplicationContext(), "La clave de confirmación debe ser la misma", Toast.LENGTH_SHORT);
            toast1.show();
            claveNueva1.setText("");
            claveNueva2.setText("");
        }

    }
}