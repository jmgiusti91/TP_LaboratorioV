package example.juangiusti.com.tplaboratoriov;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

public class Vista {

    private Activity activity;
    private RecyclerView rv;
    private Controlador controlador;

    public Vista(Activity activity) {
        this.activity = activity;
        this.rv = (RecyclerView) this.activity.findViewById(R.id.rv);
    }

    public RecyclerView getRv() {
        return rv;
    }

    public void setRv(RecyclerView rv) {
        this.rv = rv;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
}
