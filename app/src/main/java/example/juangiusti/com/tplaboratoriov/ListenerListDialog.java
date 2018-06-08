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
    private NotiConfig notiConfig;

    public ListenerListDialog(ListView listView) {
        this.listView = listView;
        this.notiConfig = new NotiConfig();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object o = this.listView.getItemAtPosition(position);
        Log.d("listView.itemPosition", o.toString());
        this.notiConfig.setPrefiero(o.toString());
    }

    public NotiConfig getNotiConfig() {
        return notiConfig;
    }
}
