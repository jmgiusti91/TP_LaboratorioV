package example.juangiusti.com.tplaboratoriov;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

public class ListenerDialogConfig implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == AlertDialog.BUTTON_POSITIVE) {
            Log.d("ACEPTAR", "OK");
        }

        if(which == AlertDialog.BUTTON_NEGATIVE) {
            Log.d("CANCELAR", "Cancel");
        }
    }
}
