package example.juangiusti.com.tplaboratoriov;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by alumno on 07/06/2018.
 */

public class ListenerListDialog implements AdapterView.OnItemClickListener{

    private ListView listView;
    private String prefiero = "deportes";

    public ListenerListDialog(ListView listView) {
        this.listView = listView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object o = this.listView.getItemAtPosition(position);
        String preferencia = o.toString();
        Log.d("Opcion", preferencia);
        if(preferencia.equalsIgnoreCase("Deportes")) {
            this.prefiero = "Deportes".toLowerCase();
        }
        if(preferencia.equalsIgnoreCase("Economia")) {
            this.prefiero = "Economia".toLowerCase();
        }
        if(preferencia.equalsIgnoreCase("Politica")) {
            this.prefiero = "Politica".toLowerCase();
        }
        if(preferencia.equalsIgnoreCase("Internacional")) {
            this.prefiero = "Internacional".toLowerCase();
        }
    }

    public String getPrefiero() {
        return prefiero;
    }
}
