package br.com.wesleygalindo.aulaactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pega o campo EditText com id editParametro
        final EditText campoParametro = (EditText) findViewById(R.id.editParametro);

        Button botao = (Button) findViewById(R.id.botaoTela2);
        // escuta o evento de clique no botão
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // criar um Bundle com parametros para ser adicionado na Intent
                Bundle bundle = new Bundle();
                bundle.putString("parametro", campoParametro.getText().toString());

                Intent intent = new Intent(MainActivity.this, Tela2Activity.class);
                // adiciona o bundle à intent
                intent.putExtras(bundle);

                // abre a activity com a intent (Tela2Activity)
                startActivity(intent);
            }
        });



        Log.i("MainActivity", "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
