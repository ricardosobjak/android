package br.edu.utfpr.android.webkit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class Browser2Activity extends AppCompatActivity {
	WebView browser;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_browser2);
		browser=(WebView)findViewById(R.id.webkit);
		
		browser.loadData("<html><body>Hello, world!</body></html>",
											"text/html", "UTF-8");
	}
}