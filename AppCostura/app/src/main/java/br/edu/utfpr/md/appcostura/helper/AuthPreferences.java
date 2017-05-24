
package br.edu.utfpr.md.appcostura.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;

public class AuthPreferences {
	private SharedPreferences prefs;

	public AuthPreferences(Context context) {
		prefs = context.getSharedPreferences("AppCostura", Context.MODE_PRIVATE);
	}
	
	public void gravar(String nome, String user, String token) {
		Editor editor = prefs.edit();
        editor.putString("NOME", nome);
		editor.putString("USER", user);
		editor.putString("TOKEN", token);

		editor.commit();
	}
	
	public Map<String, ?> getPreferences() {
		return prefs.getAll();
	}

    public String getNome() {
        return prefs.getString("NOME", null);
    }

	public String getUser() {
		return prefs.getString("USER", null);
	}
	
	public String getToken() {
		return prefs.getString("TOKEN", null);
	}
}