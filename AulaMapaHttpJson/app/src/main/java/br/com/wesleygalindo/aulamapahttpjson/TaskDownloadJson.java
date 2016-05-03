package br.com.wesleygalindo.aulamapahttpjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

//Roda uma Thread a parte da principal para que nao trave a inteface do usuario
public class TaskDownloadJson extends AsyncTask<String, String, String> {
	private Context context;
	private ProgressDialog progressDialog;

	public TaskDownloadJson(Context contexto) {
		this.context = contexto;
	}

	// Aqui ainda está na Thread principal
	@Override
	protected void onPreExecute() {
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Aguarde!");
		progressDialog.setMessage("Buscando Informações...");
		progressDialog.show();
		super.onPreExecute();
	}

	// Começa a nova Thread e realiza a tarefa;
	@Override
	protected String doInBackground(String... params) {
		String result = "";

		try {
			// o parametro que passamos ao executar a task
			String link = params[0];

			URL url = new URL(link);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();


			// se true indica que enviaremos dados no corpo da requisição
			// false)
			connection.setDoOutput(false);
			// se true indica que leremos os dados da resposta
			connection.setDoInput(true);
			// default é GET
			connection.setRequestMethod("GET");
			// verifica o código da reposta
			connection.connect();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(connection.getInputStream(),
								"iso-8859-1"));
				String line = "";
				
				while ((line = buffer.readLine()) != null) {
					result += line;
				}
				buffer.close();
			}
		} catch (IOException e) {
			publishProgress("Problema ao tentar se conectar...");
			e.printStackTrace();
		}
		return result;
	}

	// Termina e Thread e pega seu retorno
	@Override
	protected void onPostExecute(String result) {
		progressDialog.setMessage("Finalizado!");
		progressDialog.dismiss();
		super.onPostExecute(result);
	}

}
