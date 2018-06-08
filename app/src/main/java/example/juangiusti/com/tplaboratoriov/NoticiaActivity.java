package example.juangiusti.com.tplaboratoriov;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class NoticiaActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        String url = this.getIntent().getStringExtra("url");
        this.webView = (WebView) findViewById(R.id.webViewNoticia);
        WebSettings ws = this.webView.getSettings();
        ws.setJavaScriptEnabled(true);
        //this.webView.loadUrl(url);
        this.webView.loadUrl(url);
        //this.webView.loadData("<h1>Aguante el Cuervo</h1>", "", "");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
