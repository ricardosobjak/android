package br.edu.utfpr.android.webkit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class Browser1Activity extends AppCompatActivity {
	WebView browser;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_browser1);
		browser=(WebView)findViewById(R.id.webkit);
		
		browser.loadUrl("http://192.168.6.138:8080/ServerAndroidMeditec/pessoa");
	}
}