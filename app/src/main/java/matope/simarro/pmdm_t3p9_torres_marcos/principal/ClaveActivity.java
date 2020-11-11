package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;

public class ClaveActivity extends AppCompatActivity {

    private EditText claveActual, claveNueva1, claveNueva2;
    private Cliente cliente;

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
            Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");
            final MiBancoOperacional api = MiBancoOperacional.getInstance(getApplicationContext());
            if (claveActual.getText().toString().equals(cliente.getClaveSeguridad())) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirmar cambios");
                builder.setMessage("¿Está seguro de querer cambiar su clave?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Cliente cliente = (Cliente) getIntent().getSerializableExtra("cliente");
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Clave cambiada con éxito", Toast.LENGTH_SHORT);
                        toast1.show();
                        cliente.setClaveSeguridad(claveNueva1.getText().toString());
                        api.changePassword(cliente);
                        finish();

                    }
                });
                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                Toast toast1 = Toast.makeText(getApplicationContext(), "La clave antigua no es correcta", Toast.LENGTH_SHORT);
                toast1.show();
            }

        } else {
            Toast toast1 = Toast.makeText(getApplicationContext(), "La clave de confirmación debe ser la misma", Toast.LENGTH_SHORT);
            toast1.show();
            claveNueva1.setText("");
            claveNueva2.setText("");
        }

    }
}