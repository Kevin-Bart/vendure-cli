import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "list", description = "Affiche la liste des produits")
public class ListCommand implements Runnable {

    @Option(names = {"--format"}, description = "Format de sortie (table, json)", defaultValue = "table")
    private String format = "table";

    public void setFormat(String format) { this.format = format; }
    public String getFormat() { return this.format; }

    // La vraie logique métier (sans exception cette fois !)
    public String execute() {
        if ("json".equalsIgnoreCase(format)) {
            return "[\n  {\"name\": \"Laptop\", \"price\": 1459.00},\n  {\"name\": \"Souris\", \"price\": 51.90}\n]";
        } else {
            return "--------------------------\n" +
                    "| Nom      | Prix        |\n" +
                    "--------------------------\n" +
                    "| Laptop   | 1459.00 CHF |\n" +
                    "| Souris   |   51.90 CHF |\n" +
                    "--------------------------";
        }
    }

    @Override
    public void run() {
        // Picocli appelle cette méthode. On affiche le résultat de execute().
        System.out.println(execute());
    }
}