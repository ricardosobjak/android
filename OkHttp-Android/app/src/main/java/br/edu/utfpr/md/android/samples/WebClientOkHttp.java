package br.edu.utfpr.md.android.samples;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebClientOkHttp {
    private OkHttpClient client = new OkHttpClient();

    public String post(String url, String json) throws IOException {
        // Builder de requisição (onde os dados são preenchidos)
        Request.Builder builder = new Request.Builder();
        builder.url(url);

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(mediaType, json);

        builder.post(body);

        // Cria a Requisição
        Request request = builder.build();

        // Executa a requisição e obtém a resposta
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}