package example.juangiusti.com.tplaboratoriov;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements Handler.Callback, MyOnItemClick, SearchView.OnQueryTextListener{

    private MyAdapter myAdapter;
    private Vista v;
    private Controlador c;
    private Handler handler;
    private SharedPreferences prefs;
    private String url;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.searchView).getActionView();
        sv.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.itemConfig) {
            DialogConfig dc = new DialogConfig();
            dc.show(getSupportFragmentManager(), "");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("NotiView");
        SharedPreferences prefs = getSharedPreferences("noticias_config", Context.MODE_PRIVATE);
        if(prefs != null) {
            if(prefs.getString("noticias", "deportes").equalsIgnoreCase("deportes")) {
                this.url = "http://www.telam.com.ar/rss2/deportes.xml";
            }
            if(prefs.getString("noticias", "deportes").equalsIgnoreCase("economia")) {
                this.url = "https://www.telam.com.ar/rss2/economia.xml";
            }
            if(prefs.getString("noticias", "deportes").equalsIgnoreCase("politica")) {
                this.url = "https://www.telam.com.ar/rss2/politica.xml";
            }
            if(prefs.getString("noticias", "deportes").equalsIgnoreCase("internacional")) {
                this.url = "https://www.telam.com.ar/rss2/internacional.xml";
            }
        }
        this.v = new Vista(this);
        this.c = new Controlador(v);
        v.getRv().setLayoutManager(new LinearLayoutManager(this));
        this.handler = new Handler(this);
        HiloDatos hd1 = new HiloDatos(this.url, handler, EDatos.TEXTO);
        Thread t1 = new Thread(hd1);
        t1.start();
        //Intent i = new Intent(this, NoticiaActivity.class);
        //i.putExtra("url", "noticia.getUrl()");
        //startActivity(i);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == 1) {
           this.myAdapter = new MyAdapter(ParserXML.parsear(msg.obj.toString()), this, this.handler);
           this.v.getRv().setAdapter(this.myAdapter);
        } else if(msg.arg1 == 2) {
            //this.myAdapter.notifyDataSetChanged();
            this.myAdapter.notifyItemChanged((Integer)msg.obj);
        }
        return false;
    }

    @Override
    public void onItemClick(String link) {
        Log.d("TAG_LINK", link);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("TextSubmit", query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //Se puede usar un pattern en Java o un indexOf para buscar coincidencias. Lo que se busca en el search, debe coincidir con el titulo de la noticia.
        Log.d("TextChange", newText);
        return false;
    }
}
