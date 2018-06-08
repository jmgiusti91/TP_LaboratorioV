package example.juangiusti.com.tplaboratoriov;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Noticia> noticias;
    private List<Noticia> noticiasCopy;
    private MyOnItemClick listener;
    private Handler handler;

    public MyAdapter(List<Noticia> noticias, MyOnItemClick listener, Handler handler) {
        this.noticias = noticias;
        this.listener = listener;
        this.handler = handler;
        this.noticiasCopy = new ArrayList<>();
        this.noticiasCopy.addAll(noticias);
        /*for(int i = 0; i < noticias.size(); i ++) {
            Log.d("TAG_NOTICIAS_ADAPTER", this.noticias.get(i).toString());
        }*/
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v, this.listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Noticia n = this.noticias.get(position);

        if(n.getImagenByte() == null) {
            HiloDatos hd = new HiloDatos(n.getImagen(), EDatos.IMAGEN, this.handler, n, position);
            Thread t1 = new Thread(hd);
            t1.start();
            /*synchronized (n) {
                try {
                    n.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
        } else {
            holder.imgNoticia.setImageBitmap(BitmapFactory.decodeByteArray(n.getImagenByte(), 0, n.getImagenByte().length));
        }
        holder.setLink(n.getLink());
        holder.tituloNoticia.setText(n.getTitulo());
        holder.descripcionNoticia.setText(n.getDescripcion());
        holder.fechaNoticia.setText(n.getFecha().toString());
    }

    @Override
    public int getItemCount() {
        return this.noticias.size();
    }

    public void filter(String text) {
        this.noticias.clear();
        if(text.isEmpty()){
            this.noticias.addAll(this.noticiasCopy);
        } else{
            text = text.toLowerCase();
            for(Noticia item: this.noticiasCopy){
                if(item.getTitulo().toLowerCase().contains(text)){
                    this.noticias.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
