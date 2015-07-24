package br.com.wesleygalindo.activitysample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Tela2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        TextView txtResult = (TextView) findViewById(R.id.txt_result);

        Intent intent = getIntent();

        // recupera a String com a chave nome, passada como parametro na intent
        String result = intent.getStringExtra("nome");

        if(result != null) {
            txtResult.setText(result);
        } else {
            // recupera um objeto Aluno passado como no extra da intent
            Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
            txtResult.setText(aluno.getMatricula() + " - " + aluno.getNome());
        }

    }

}
