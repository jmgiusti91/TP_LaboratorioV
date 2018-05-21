package example.juangiusti.com.tplaboratoriov;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Handler.Callback, MyOnItemClick{

    private MyAdapter myAdapter;
    private Vista v;
    private Controlador c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.v = new Vista(this);
        this.c = new Controlador(v);
        v.getRv().setLayoutManager(new LinearLayoutManager(this));
        Handler handler = new Handler(this);
        HiloDatos hd1 = new HiloDatos("https://www.telam.com.ar/rss2/deportes.xml", handler, EDatos.TEXTO);
        Thread t1 = new Thread(hd1);
        t1.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.arg1 == 1) {
           this.myAdapter = new MyAdapter(ParserXML.parsear(msg.obj.toString()), this);
           this.v.getRv().setAdapter(this.myAdapter);
        } else if(msg.arg1 == 2) {

        }
        return false;
    }

    @Override
    public void onItemClick(String link) {
        Log.d("TAG_LINK", link);
    }
}
