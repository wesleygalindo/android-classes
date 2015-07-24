package br.com.wesleygalindo.activitysample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTela2 = (Button) findViewById(R.id.btn_tela2);
        // define o listener do evento de clique do botao
        btnTela2.setOnClickListener(this);

        Button btnTela2Objeto = (Button) findViewById(R.id.btn_tela2_objeto);
        btnTela2Objeto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // cria o objeto intent para abrir a Tela 2
        Intent intent = new Intent(MainActivity.this, Tela2.class);

        switch (v.getId()) {
            case R.id.btn_tela2:
                // adiciona uma String no extra da intent
                intent.putExtra("nome", "Wesley Galindo");
                // inicia a Tela2
                startActivity(intent);
                break;
            case R.id.btn_tela2_objeto:
                // cria o objeto aluno
                Aluno aluno = new Aluno();
                aluno.setNome("Aluno");
                aluno.setMatricula("0000001");

                // criar um Bundle para adicionar o objeto aluno
                Bundle bundle = new Bundle();
                bundle.putSerializable("aluno", aluno);

                // adiciona o Bundle no extra da intent
                intent.putExtras(bundle);
                // inicia a Tela2
                startActivity(intent);
                break;
        }
    }
}
