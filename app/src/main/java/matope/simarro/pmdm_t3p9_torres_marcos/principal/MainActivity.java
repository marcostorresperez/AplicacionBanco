package matope.simarro.pmdm_t3p9_torres_marcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import matope.simarro.pmdm_t3p9_torres_marcos.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }


    public void sePulsa(View v){
        Intent intent= new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}