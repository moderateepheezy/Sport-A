package com.news.app.com.sport.app;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.news.app.R;

public class Game extends ActionBarActivity {

    static final String url = "http://www.fansbox18.com";
    private WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        browser = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        MyBrowser myBrowser = new MyBrowser();
        browser.setWebViewClient(myBrowser);
        browser.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.browser.canGoBack()) {
            this.browser.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyBrowser extends WebViewClient {
        public ProgressDialog dialog;
        private int       webViewPreviousState;
        private final int PAGE_STARTED    = 0x1;
        private final int PAGE_REDIRECTED = 0x2;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
            webViewPreviousState = PAGE_REDIRECTED;
            view.loadUrl(urlNewString);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webViewPreviousState = PAGE_STARTED;
            if (dialog == null || !dialog.isShowing())
                dialog = ProgressDialog.show(Game.this, "", "loading", true, true,
                        new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {
                                // do something
                            }
                        });
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            if (webViewPreviousState == PAGE_STARTED) {
                dialog.dismiss();
                dialog = null;
            }

        }
    }
}
