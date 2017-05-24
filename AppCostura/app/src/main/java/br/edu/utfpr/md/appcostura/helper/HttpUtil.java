package br.edu.utfpr.md.appcostura.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    private String urlAddress;
    private String method = "GET";
    private String data;
    private Map<String, String> requestProperties = new HashMap<>();

    private HttpURLConnection con = null;
    private URL url = null;
    private String response = null;

    public HttpUtil() {
        requestProperties.put("User-Agent", "Mozilla/5.0");
        //requestProperties.put("Accept", "application/json");
        //requestProperties.put("Content-Type", "application/json");
    }

    public HttpUtil setUrl(String url) {
        this.urlAddress = url;
        return this;
    }

    public HttpUtil setMethod(String method) {
        this.method = method;
        return this;
    }

    public HttpUtil addRequestProperty(String chave, String valor) {
        this.requestProperties.put(chave, valor);
        return this;
    }

    public HttpUtil setData(String data) {
        this.data = data;
        return this;
    }

    /**
     * Faz a requisição
     * @return
     */
	public HttpUtil request() {
		try {
			url = new URL(urlAddress);

			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(this.method);

            for(String key : requestProperties.keySet()) {
                con.setRequestProperty(key, requestProperties.get(key));
            }

            if (this.data != null && !this.data.isEmpty()) {
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

                wr.write(this.data);  //<--- sending data.

                wr.flush();
                wr.close();
            }

			System.out.println(con.getHeaderFields());

            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                response = readStream(con.getInputStream());
            } else {
                response = readStream(con.getErrorStream());
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return this;
	}

    public int getResponseCode() throws IOException {
        return this.con.getResponseCode();
    }

    public String getResponseBody() {
        return this.response;
    }


	public static String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	}
}