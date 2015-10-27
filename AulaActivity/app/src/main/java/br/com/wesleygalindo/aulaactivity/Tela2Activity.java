package br.com.wesleygalindo.aulaactivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Tela2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // define o arquivo de layout da tela
        setContentView(R.layout.activity_tela2);

        // recupera o bundle da intent
        Bundle bundle = getIntent().getExtras();
        // recupera uma informação do bundle com a chave parametro
        String nome = bundle.getString("parametro");

        TextView textoNome = (TextView) findViewById(R.id.texto);
        textoNome.setText(nome);
        Log.i("Tela2", "parametro: " + nome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
