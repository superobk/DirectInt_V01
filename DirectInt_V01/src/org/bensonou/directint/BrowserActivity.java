package org.bensonou.directint;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

public class BrowserActivity extends Activity {

	WebView browser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browser);

		browser = new WebView(this);
		browser.getSettings().setJavaScriptEnabled(true);
		// browser.loadUrl("http://www.baidu.com/");
		browser.loadUrl("http://ieye.asia/pos/");

		setContentView(browser);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()) {
			browser.goBack();
			return true;
		} else {
			this.finish();
			return false;
		}
	}
}
