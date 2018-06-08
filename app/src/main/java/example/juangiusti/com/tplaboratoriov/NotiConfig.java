package example.juangiusti.com.tplaboratoriov;

public class NotiConfig {

    private String prefiero;
    private String url;

    public NotiConfig() {
        this.prefiero = "deportes";
        this.url = "http://www.telam.com.ar/rss2/deportes.xml";
    }

    public NotiConfig(String prefiero, String url) {
        this.prefiero = prefiero;
        this.url = url;
    }

    public String getPrefiero() {
        return prefiero;
    }

    public void setPrefiero(String quePrefiero) {
        if(quePrefiero.equalsIgnoreCase("Deportes")) {
            this.url = "http://www.telam.com.ar/rss2/deportes.xml";
            this.prefiero = "deportes";
        }
        if(quePrefiero.equalsIgnoreCase("Economia")) {
            this.url = "https://www.telam.com.ar/rss2/economia.xml";
            this.prefiero = "economia";
        }
        if(quePrefiero.equalsIgnoreCase("Politica")) {
            this.url = "https://www.telam.com.ar/rss2/politica.xml";
            this.prefiero = "politica";
        }
        if(quePrefiero.equalsIgnoreCase("Internacional")) {
            this.url = "https://www.telam.com.ar/rss2/internacional.xml";
            this.prefiero = "internacional";
        }
    }

    public String getUrl() {
        return url;
    }
}
