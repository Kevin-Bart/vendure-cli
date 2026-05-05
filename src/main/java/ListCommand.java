public class ListCommand {
    private String format = "table"; // Valeur par défaut demandée par l'énoncé

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return this.format;
    }

    public String execute() {
        // Implémentation vide pour faire compiler, on retourne une erreur exprès
        throw new UnsupportedOperationException("Fonctionnalité pas encore implémentée !");
    }
}