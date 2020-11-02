package matope.simarro.pmdm_t3p9_torres_marcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity {

    private TextView nombre;
    private Button cerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);

        String nombreRecibido=getIntent().getStringExtra("nombre");
        nombre=findViewById(R.id.txtNombre);
        nombre.setText(nombreRecibido);

    }

    public void btnCerrar(View v){
        Intent intent= new Intent(PrincipalActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void btnClave(View v){
        Intent intent= new Intent(PrincipalActivity.this,ClaveActivity.class);
        startActivity(intent);
    }

    public void btnTransferencias(View v) {
        Intent intent = new Intent(PrincipalActivity.this, TransferenciasActivity.class);
        startActivity(intent);
    }


}