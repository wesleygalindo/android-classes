package br.com.wesleygalindo.aulahttp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            TaskDownloadJson downloadJson = new TaskDownloadJson(MainActivity.this);
            // executa a tarefa passando uma String como parametro para o doInBackground
            downloadJson.execute("http://dados.recife.pe.gov.br/api/action/datastore_search?resource_id=dc6b3d07-3124-453d-b11e-72364cced7aa");
            // ler o retorno da tarefa(JSON) e adiciona na String
            String json = downloadJson.get();
            // imprime no Log o JSON retornado
            Log.i("MainActivity", "json: " + json);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
