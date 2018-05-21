package example.juangiusti.com.tplaboratoriov;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView imgNoticia;
    public TextView tituloNoticia;
    public TextView descripcionNoticia;
    public TextView fechaNoticia;
    private MyOnItemClick listener;
    private String link;

    public MyViewHolder(View itemView, MyOnItemClick listener) {
        super(itemView);

        this.imgNoticia = (ImageView) itemView.findViewById(R.id.imgNoticia);
        this.tituloNoticia = (TextView) itemView.findViewById(R.id.tituloNoticia);
        this.descripcionNoticia = (TextView) itemView.findViewById(R.id.descripcionNoticia);
        this.fechaNoticia = (TextView) itemView.findViewById(R.id.fechaNoticia);
        itemView.setOnClickListener(this);
        this.listener = listener;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClick(link);
    }
}
