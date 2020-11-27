package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class TransferenciasActivity extends AppCompatActivity {

    private GridView origen, destino;
    private Spinner divisa;
    private EditText importe, txtDestino;
    private TextView seleccionado1 = null, seleccionado2 = null;
    private CheckBox justificante;
    private boolean marcado = false;
    private int tipo;

    private Cliente cliente;

    MiBancoOperacional api = MiBancoOperacional.getInstance(this.getApplicationContext());
    ArrayList<Cuenta> listaCuentas = api.getCuentas(cliente);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias);

        origen = (GridView) findViewById(R.id.origen);

        String[] datos = new String[]{"ES 99599959446565534546656", "ES 548765987821456598124532", "ES 457898653412751975832025", "ES 798659734215758594653534"};
        Character[] divisas = new Character[]{'€', '$', '£', '¥', '₱'};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        origen = (GridView) findViewById(R.id.origen);
        origen.setAdapter(adaptador);

        origen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (seleccionado1 != null) {
                    seleccionado1.setBackgroundColor(getColor(R.color.fondo));
                }
                view.setBackgroundColor(getColor(R.color.textos));
                seleccionado1 = (TextView) view;
            }
        });

        destino = (GridView) findViewById(R.id.destino);
        destino.setAdapter(adaptador);
        destino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (seleccionado2 != null) {
                    seleccionado2.setBackgroundColor(getColor(R.color.fondo));
                }
                view.setBackgroundColor(getColor(R.color.textos));
                seleccionado2 = (TextView) view;
            }
        });

        ArrayAdapter<String> spinnerAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        spinnerAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destino.setAdapter(spinnerAdaptador);


        importe = findViewById(R.id.importe);
        divisa = findViewById(R.id.divisa);
        ArrayAdapter<Character> spinnerDivisa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, divisas);
        divisa.setAdapter(spinnerDivisa);

        justificante = findViewById(R.id.justificante);
        justificante.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (justificante.isChecked()) {
                    marcado = true;
                } else if (!justificante.isChecked()) {
                    marcado = false;
                }
            }
        });


    }

    public void ok(View v) {
        String marca = "";
        if (seleccionado1.getText().toString().equals(seleccionado2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "La cuenta de origen y destino es la misma", Toast.LENGTH_LONG).show();
        } else {
            if (seleccionado1 == null || seleccionado2 == null || importe.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Hay opciones sin completar", Toast.LENGTH_SHORT).show();
            } else {
                if (marcado) {
                    marca = "SÍ";
                } else {
                    marca = "NO";
                }

                String datos = "Cuenta origen: " + seleccionado1.getText().toString() + "\n" + "Cuenta destino:" + seleccionado2.getText().toString() + "\n" + "Importe: " + importe.getText().toString() + divisa.getSelectedItem().toString() + "\n" + "Generar justificante: " + marca;
                Toast.makeText(getApplicationContext(), datos, Toast.LENGTH_LONG).show();

                if(Double.parseDouble(importe.toString())>0){
                    tipo=1;
                }else if(Double.parseDouble(importe.toString())<0){
                    tipo=2;
                }else{
                    tipo=0;
                }


                 MiBancoOperacional api = MiBancoOperacional.getInstance(getApplicationContext());

                 Movimiento m=new Movimiento(null,null,tipo,m.getFechaOperacion().getTime(),);
                 api.transferencia(m);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void cancelar(View v) {
        seleccionado1.setBackgroundColor(getColor(R.color.fondo));
        seleccionado2.setBackgroundColor(getColor(R.color.fondo));

        importe.setText("");
    }


}