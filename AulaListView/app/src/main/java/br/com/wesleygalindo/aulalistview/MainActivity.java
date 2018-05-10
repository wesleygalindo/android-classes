package br.com.wesleygalindo.aulalistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lista);

        // Criando uma lista estática de produtos para preencher o listview
        final ArrayList<Produto> listaProdutos = new ArrayList<>();
        Produto p1 = new Produto("Coca cola", "Descrição...", "https://images-na.ssl-images-amazon.com/images/I/81mEIp4PMBL._SY445_.jpg");
        Produto p2 = new Produto("Guaraná Antartica", "Descrição...", "https://images-na.ssl-images-amazon.com/images/I/51fKJ0US2UL.jpg");
        Produto p3 = new Produto("Pepsi", "Descrição...", "https://www.pepsi.com/en-us/uploads/images/can-pepsi.png");

        listaProdutos.add(p1);
        listaProdutos.add(p2);
        listaProdutos.add(p3);

        // Criando o Adapter passando a lista de produtos
        ProdutoAdapter adapter = new ProdutoAdapter(MainActivity.this, listaProdutos);

        // Adicionando o adapter ao listview
        listView.setAdapter(adapter);

        // Capturando o evento de clique no item da lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Produto produto = listaProdutos.get(position);

                Intent intent = new Intent(MainActivity.this, DetalhesProdutoActivity.class);
                // Passando o produto clicado como extra na intent
                intent.putExtra("produtoSelecionado", produto);

                // Abrindo a tela DetalhesProdutoActivity
                startActivity(intent);
            }
        });
    }
}
