package example.juangiusti.com.tplaboratoriov;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.UnsupportedEncodingException;

public class HiloDatos implements Runnable {

    private String url;
    private Handler handler;
    private EDatos queBusco;
    private Noticia noticia;

    public HiloDatos(String url, Handler handler, EDatos queBusco) {
        this.url = url;
        this.handler = handler;
        this.queBusco = queBusco;
    }

    public HiloDatos(String url, EDatos queBusco, Noticia noticia) {
        this.url = url;
        this.queBusco = queBusco;
        this.noticia = noticia;
    }

    @Override
    public void run() {
        ConexionHttp chttp = new ConexionHttp();
        byte[] retorno = chttp.devolverDatos(this.url);
        Message msg = new Message();
        if(this.queBusco == EDatos.TEXTO) {
            msg.arg1 = 1;
            try {
                msg.obj = new String(retorno, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            this.handler.sendMessage(msg);
        } else if(this.queBusco == EDatos.IMAGEN) {
            if(this.noticia != null) {
                this.noticia.setImagenByte(retorno);
                synchronized (this.noticia) {
                    this.noticia.notify();
                }
            } else {
                msg.arg1 = 2;
                Bitmap bitmap = BitmapFactory.decodeByteArray(retorno, 0, retorno.length);
                msg.obj = bitmap;
                this.handler.sendMessage(msg);
            }
        }
    }
}
