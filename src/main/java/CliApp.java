public class CliApp {
    private String url;

    // Représente le fait de recevoir l'option --url ou la variable d'environnement
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}