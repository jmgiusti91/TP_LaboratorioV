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

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity implements Handler.Callback, MyOnItemClick, SearchView.OnQueryTextListener{

    private MyAdapter myAdapter;
    private Vista v;
    private Controlador c;
    private Handler handler;
    private SharedPreferences prefs;
    private String url;
    private NotiConfig notiConfig = new NotiConfig("deportes", ParserXML.TELAM + "/rss2/deportes.xml");
    private List<Noticia> noticias;

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
            this.obtenerNotiConfig(prefs);
        }
        this.v = new Vista(this);
        this.c = new Controlador(v);
        v.getRv().setLayoutManager(new LinearLayoutManager(this));
        this.handler = new Handler(this);
        this.buscarNoticias(this.notiConfig);
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

    public void obtenerNotiConfig(SharedPreferences prefs) {
        if(prefs.getString("noticias", "").equalsIgnoreCase("deportes")) {
            this.notiConfig = new NotiConfig("deportes", ParserXML.TELAM + "/rss2/deportes.xml");
        }
        if(prefs.getString("noticias", "").equalsIgnoreCase("economia")) {
            this.notiConfig = new NotiConfig("economia", ParserXML.TELAM + "/rss2/economia.xml");
        }
        if(prefs.getString("noticias", "").equalsIgnoreCase("politica")) {
            this.notiConfig = new NotiConfig("politica", ParserXML.TELAM + "/rss2/politica.xml");
        }
        if(prefs.getString("noticias", "").equalsIgnoreCase("internacional")) {
            this.notiConfig = new NotiConfig("internacional", ParserXML.TELAM + "/rss2/internacional.xml");
        }
    }

    public void buscarNoticias(NotiConfig notiConfig) {
        this.url = notiConfig.getUrl();
        HiloDatos hd1 = new HiloDatos(this.url, this.handler, EDatos.TEXTO);
        Thread t1 = new Thread(hd1);
        t1.start();
    }

    @Override
    public void onItemClick(String link) {
        Log.d("TAG_LINK", link);
        Intent i = new Intent(this, NoticiaActivity.class);
        i.putExtra("url", link);
        startActivity(i);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("TextSubmit", query);
        this.myAdapter.filter(query);
        /*this.noticias = this.myAdapter.getNoticias();
        List<Noticia> noticiasFiltro = new ArrayList<>();
        for (Noticia n : noticias) {
            if(n.getTitulo().equalsIgnoreCase(query)) {
                noticiasFiltro.add(n);
            }
        }
        this.myAdapter.setNoticias(noticiasFiltro);
        this.myAdapter.notifyDataSetChanged();*/
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        this.myAdapter.filter(newText);
        //Se puede usar un pattern en Java o un indexOf para buscar coincidencias. Lo que se busca en el search, debe coincidir con el titulo de la noticia.
        Log.d("TextChange", newText);
        return false;
    }

    @Override
    protected void onStop() {
        this.myAdapter.getT1().interrupt();
        super.onStop();
    }
}
