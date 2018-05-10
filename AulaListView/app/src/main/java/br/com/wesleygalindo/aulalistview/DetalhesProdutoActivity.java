package br.com.wesleygalindo.aulalistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        // Produto que veio via intent(extra) da MainActivity
        Produto produto = (Produto) getIntent().getSerializableExtra("produtoSelecionado");


        TextView textNome = findViewById(R.id.detalhe_nome_produto);
        // Adiciona o nome do produto ao textview
        textNome.setText(produto.getNome());
    }
}
