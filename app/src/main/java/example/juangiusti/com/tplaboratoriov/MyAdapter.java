package example.juangiusti.com.tplaboratoriov;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Noticia> noticias;
    private MyOnItemClick listener;

    public MyAdapter(List<Noticia> noticias, MyOnItemClick listener) {
        this.noticias = noticias;
        this.listener = listener;
        for(int i = 0; i < noticias.size(); i ++) {
            Log.d("TAG_NOTICIAS_ADAPTER", this.noticias.get(i).toString());
        }
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
            HiloDatos hd = new HiloDatos(n.getImagen(), EDatos.IMAGEN, n);
            Thread t1 = new Thread(hd);
            t1.start();
            synchronized (n) {
                try {
                    n.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        holder.setLink(n.getLink());
        holder.imgNoticia.setImageBitmap(BitmapFactory.decodeByteArray(n.getImagenByte(), 0, n.getImagenByte().length));
        holder.tituloNoticia.setText(n.getTitulo());
        holder.descripcionNoticia.setText(n.getDescripcion());
        holder.fechaNoticia.setText(n.getFecha().toString());
    }

    @Override
    public int getItemCount() {
        return this.noticias.size();
    }
}
