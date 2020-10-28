package matope.simarro.pmdm_t3p9_torres_marcos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class TransferenciasActivity extends AppCompatActivity {

    private GridView origen;
    private Spinner destino, divisa;
    private RadioGroup radio;
    private RadioButton radio1, radio2;
    private EditText importe, txtDestino;
    private TextView seleccionado = null;
    private CheckBox justificante;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias);

        origen = (GridView) findViewById(R.id.origen);

        String[] datos = new String[]{"ES 99599959446565534546656", "548765987821456598124532", "457898653412751975832025", "798659734215758594653534"};
        Character[] divisas = new Character[]{'€', '$', '£', '¥', '₱'};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        origen = (GridView) findViewById(R.id.origen);
        origen.setAdapter(adaptador);

        origen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (seleccionado != null) {
                    seleccionado.setBackgroundColor(getColor(R.color.fondo));
                }
                view.setBackgroundColor(getColor(R.color.textos));
                seleccionado = (TextView) view;
            }
        });

        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);

        radio.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (radio1.isChecked()) {
                    destino.setVisibility(View.VISIBLE);
                    txtDestino.setVisibility(View.GONE);
                } else if (radio2.isChecked()) {
                    txtDestino.setVisibility(View.VISIBLE);
                    destino.setVisibility(View.GONE);
                }

            }
        };


        txtDestino = findViewById(R.id.destino);
        destino = findViewById(R.id.spinnerDestino);


        ArrayAdapter<String> spinnerAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        spinnerAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destino.setAdapter(spinnerAdaptador);


        importe = findViewById(R.id.importe);
        divisa = findViewById(R.id.divisa);
        ArrayAdapter<Character> spinnerDivisa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, divisas);
        divisa.setAdapter(spinnerDivisa);

        justificante = findViewById(R.id.justificante);


    }

    public void ok(View v){

    }

    public void cancelar(View v){
        
    }


}