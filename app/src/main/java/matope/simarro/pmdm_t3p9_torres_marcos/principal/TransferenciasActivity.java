package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import java.util.Calendar;
import java.util.Date;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.bd.MiBancoOperacional;
import matope.simarro.pmdm_t3p9_torres_marcos.otros.Locales;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cliente;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Cuenta;
import matope.simarro.pmdm_t3p9_torres_marcos.pojo.Movimiento;

public class TransferenciasActivity extends AppCompatActivity {

    private GridView origen, destino;
    private Spinner divisa;
    private EditText importe, descripcion;
    private TextView seleccionado1 = null, seleccionado2 = null;
    private CheckBox justificante;
    private boolean marcado = false;
    private int tipo;
    Calendar c = Calendar.getInstance();
    Date fecha = new Date();
    Cuenta cuentaOrigen;
    Cuenta cuentaDestino;
    MiBancoOperacional api;
    Cliente cliente;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String locale = sp.getString("pais", null);
        if(locale!=null){
            Locales.cambiarIdioma(this,locale);
        }

        setContentView(R.layout.activity_transferencias);
        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        ArrayList<String> listaCuentas = new ArrayList<>();

        for (int i = 0; i < cliente.getListaCuentas().size(); i++) {
            listaCuentas.add(cliente.getListaCuentas().get(i).getNumeroCuenta());
        }

        Character[] divisas = new Character[]{'€', '$', '£', '¥', '₱'};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaCuentas);
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

        ArrayAdapter<String> spinnerAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCuentas);
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
        api = MiBancoOperacional.getInstance(getApplicationContext());


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


                descripcion = findViewById(R.id.desc);

                cuentaOrigen = getCuenta(seleccionado1.getText().toString());
                cuentaDestino = getCuenta(seleccionado2.getText().toString());

                Movimiento m = new Movimiento(1, 0, new Date(), descripcion.getText().toString(), Float.parseFloat(importe.getText().toString()), cuentaOrigen, cuentaDestino);
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

    public Cuenta getCuenta(String c) {
        ArrayList<Cuenta> listaCuentas = cliente.getListaCuentas();

        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumeroCuenta().equals(c)) {
                return cuenta;
            }
        }

        return null;
    }


}