package example.juangiusti.com.tplaboratoriov;


import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConexionHttp {

    private URL url;
    private HttpURLConnection connection;
    private byte[] byteArray;

    public byte[] devolverDatos(String strUrl) {
        this.url = null;
        this.connection = null;
        this.byteArray = null;
        try {
            Log.d("URL: ", strUrl);
            this.url = new URL(strUrl);
            this.connection = (HttpURLConnection) this.url.openConnection();
            this.connection.setRequestMethod("GET");
            this.connection.connect();
            if(this.connection.getResponseCode() == 200) {
                InputStream is = this.connection.getInputStream();
                int len;
                byte[] arr = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while((len = is.read(arr, 0, 1024)) > -1) {
                    baos.write(arr, 0, len);
                }
                is.close();
                this.byteArray = baos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.byteArray;
    }
}
