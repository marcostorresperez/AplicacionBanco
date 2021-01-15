package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import matope.simarro.pmdm_t3p9_torres_marcos.R;
import matope.simarro.pmdm_t3p9_torres_marcos.otros.Audio;
import matope.simarro.pmdm_t3p9_torres_marcos.otros.Locales;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);


        String locale = sp.getString("pais", null);
        if(locale!=null){
            Locales.cambiarIdioma(this,locale);
        }

        setContentView(R.layout.activity_main);

    }


    public void sePulsa(View v){
        Intent intent= new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}