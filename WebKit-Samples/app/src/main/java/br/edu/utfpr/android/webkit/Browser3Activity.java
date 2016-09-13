package br.edu.utfpr.android.webkit;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Date;

public class Browser3Activity extends Activity {
	WebView browser;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_browser3);
		browser=(WebView)findViewById(R.id.webkit);
		browser.setWebViewClient(new Callback());
		
		loadTime();
	}
	
	void loadTime() {
		String page="<html><body><a href=\"clock\">"
						+new Date().toString()
						+"</a></body></html>";
						
		browser.loadData(page, "text/html", "UTF-8");
	}

	private class Callback extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			loadTime();
			
			return(true);
		}
	}
}