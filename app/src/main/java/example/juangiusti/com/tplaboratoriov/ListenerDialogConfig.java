package example.juangiusti.com.tplaboratoriov;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;

public class ListenerDialogConfig implements DialogInterface.OnClickListener {

    ListenerListDialog lld;
    Activity activity;

    public ListenerDialogConfig(ListenerListDialog lld, Activity activity) {
        this.lld = lld;
        this.activity = activity;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == AlertDialog.BUTTON_POSITIVE) {
            //Log.d("ACEPTAR", "OK");
            SharedPreferences prefs = this.activity.getSharedPreferences("noticias_config", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("noticias", this.lld.getNotiConfig().getPrefiero());
            Log.d("getPrefiero()", this.lld.getNotiConfig().getPrefiero());
            editor.commit();
            ((MainActivity)this.activity).buscarNoticias(this.lld.getNotiConfig());
        }

        if(which == AlertDialog.BUTTON_NEGATIVE) {
            Log.d("CANCELAR", "Cancel");
        }
    }
}
