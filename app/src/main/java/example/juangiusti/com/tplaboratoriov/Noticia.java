package example.juangiusti.com.tplaboratoriov;

//import java.time.Date;
//import java.util.Date;
import java.util.Arrays;
import java.util.Date;

public class Noticia {

    private String titulo;
    private String descripcion;
    private String imagen;
    private String link;
    private byte[] imagenByte;
    private Date fecha;

    public Noticia() {
    }

    public Noticia(String titulo, String descripcion, String imagen, String link, byte[] imagenByte, Date fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.link = link;
        this.imagenByte = imagenByte;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public byte[] getImagenByte() {
        return imagenByte;
    }

    public void setImagenByte(byte[] imagenByte) {
        this.imagenByte = imagenByte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", link='" + link + '\'' +
                ", imagenByte=" + Arrays.toString(imagenByte) +
                ", fecha=" + fecha +
                '}';
    }
}
