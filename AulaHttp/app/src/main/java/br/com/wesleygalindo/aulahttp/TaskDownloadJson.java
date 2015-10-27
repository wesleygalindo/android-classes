package br.com.wesleygalindo.aulahttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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

	// Aqui ainda está na Thread principal(UI)
	@Override
	protected void onPreExecute() {
		// apresenta um dialog antes de executar o doInBackground para fazer a requisição HTTP
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Aguarde!");
		progressDialog.setMessage("Buscando Informações...");
		progressDialog.show();
		super.onPreExecute();
	}

	// Começa a nova Thread e realiza a tarefa de baixar o JSON;
	@Override
	protected String doInBackground(String... params) {
		String resposta = "";

		try {
			// o parametro que passamos ao executar a task
			String link = params[0];

			URL url = new URL(link);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			// se true indica que enviaremos dados no corpo da requisição
			connection.setDoOutput(false);
			// se true indica que leremos os dados da resposta
			connection.setDoInput(true);
			// default é GET
			connection.setRequestMethod("GET");
			// verifica o código da reposta
			connection.connect();

			// se a resposta da requisição foi OK(código HTTP 200)
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// lê a resposta como String
				resposta = readString(connection.getInputStream());
			}
		} catch (IOException e) {
			publishProgress("Problema ao tentar se conectar...");
			e.printStackTrace();
		}
		return resposta;
	}

	// Termina e Thread e pega seu retorno
	@Override
	protected void onPostExecute(String result) {
		progressDialog.setMessage("Finalizado!");
		// fecha o dialog
		progressDialog.dismiss();
		super.onPostExecute(result);
	}

	// faz o parse da resposta no InputStream para uma String
	private String readString(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

}
