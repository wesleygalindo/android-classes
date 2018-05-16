package br.com.wesleygalindo.aulalistawebservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // Ip(localhost) de acesso ao servidor
    private static final String BASE_URL = "http://127.0.0.1:8882/";
    private List<Teatro> teatros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TeatroAdapter adapter = new TeatroAdapter(MainActivity.this);

        ListView listView = findViewById(R.id.lista);
        // Captura o evento de clique em um item da lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // pega o objeto teatro na posicão clicada
                Teatro teatro = teatros.get(position);

                Intent intent = new Intent(MainActivity.this, TeatroDetalhesActivity.class);
                // Adiciona o teatro clicado à intent
                intent.putExtra("teatro", teatro);

                // Abre a activity TeatroDetalhesActivity
                startActivity(intent);
            }
        });

        // Adiciona o adapter ao listview
        listView.setAdapter(adapter);

        // Configuração para acessar o Webservice
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Cria uma instancia do TeatroWebService
        ITeatroWebService webService = retrofit.create(ITeatroWebService.class);

        // Cria a chamada ao end point /teatros
        Call<List<Teatro>> call = webService.getTeatros();
        call.enqueue(new Callback<List<Teatro>>() {
            @Override
            public void onResponse(Call<List<Teatro>> call, Response<List<Teatro>> response) {
                // Pega a lista de teatros na resposta da requisição
                teatros = response.body();

                // adiciona a lista de teatros ao adapter
                adapter.setTeatrosList(teatros);
                // notifica o adapter que os dados foram alterados(atualiza o listview)
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Teatro>> call, Throwable t) {
                // Imprime um log caso a requisição falhe
                Log.e("MainActivity", t.toString());
            }
        });
    }
}
