package matope.simarro.pmdm_t3p9_torres_marcos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity {

    private TextView nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        String nombreRecibido=getIntent().getStringExtra("nombre");
        nombre=findViewById(R.id.txtNombre);
        nombre.setText(nombreRecibido);
    }
}