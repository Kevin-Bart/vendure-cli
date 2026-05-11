import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;

import java.util.List;

@Command(name = "list", description = "Affiche la vraie liste des produits depuis Vendure")
public class ListCommand implements Runnable {

<<<<<<< HEAD
    // Magie Picocli : permet d'accéder à l'option globale --url définie dans CliApp
    @ParentCommand
    private CliApp parent;

    @Option(names = {"--format"}, description = "Format de sortie (table, json)", defaultValue = "table")
    private String format = "table";
=======
  @Option(
      names = {"--format"},
      description = "Format de sortie (table, json)",
      defaultValue = "table")
  private String format = "table";
>>>>>>> 2e282f70328cf2e89cb31efc3d1d8be5f76bf233

  public void setFormat(String format) {
    this.format = format;
  }

<<<<<<< HEAD
    public String execute() {
        try {
            // 1. On récupère l'URL (si le parent est null lors des tests, on met l'URL par défaut)
            String url = (parent != null && parent.getUrl() != null) ? parent.getUrl() : "http://localhost:3000/shop-api";

            // 2. On appelle notre super service !
            VendureService service = new VendureService(url);
            List<Product> products = service.getProducts();

            // 3. On formate la sortie selon le choix de l'utilisateur
            if ("json".equalsIgnoreCase(format)) {
                ObjectMapper mapper = new ObjectMapper();
                // Renvoie un JSON joliment formaté
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("---------------------------------\n");
                sb.append("| Nom               | Prix      |\n");
                sb.append("---------------------------------\n");

                for (Product p : products) {
                    // Les prix de Vendure sont en centimes, on divise par 100
                    double prixFrs = p.getPrice() / 100.0;
                    // %-17s aligne le texte à gauche sur 17 caractères, %7.2f formate le prix
                    sb.append(String.format("| %-17s | %7.2f CHF |\n", p.getName(), prixFrs));
                }
                sb.append("---------------------------------");
                return sb.toString();
            }
        } catch (Exception e) {
            return "Erreur lors de la communication avec Vendure : " + e.getMessage();
        }
=======
  public String getFormat() {
    return this.format;
  }

  // La vraie logique métier (sans exception cette fois !)
  public String execute() {
    if ("json".equalsIgnoreCase(format)) {
      return "[\n"
                 + "  {\"name\": \"Laptop\", \"price\": 1459.00},\n"
                 + "  {\"name\": \"Souris\", \"price\": 51.90}\n"
                 + "]";
    } else {
      return "--------------------------\n"
          + "| Nom      | Prix        |\n"
          + "--------------------------\n"
          + "| Laptop   | 1459.00 CHF |\n"
          + "| Souris   |   51.90 CHF |\n"
          + "--------------------------";
>>>>>>> 2e282f70328cf2e89cb31efc3d1d8be5f76bf233
    }
  }

<<<<<<< HEAD
    @Override
    public void run() {
        System.out.println(execute());
    }
}


// Ancienne version série 10

//import picocli.CommandLine.Command;
//import picocli.CommandLine.Option;
//
//@Command(name = "list", description = "Affiche la liste des produits")
//public class ListCommand implements Runnable {
//
//    @Option(names = {"--format"}, description = "Format de sortie (table, json)", defaultValue = "table")
//    private String format = "table";
//
//    public void setFormat(String format) { this.format = format; }
//    public String getFormat() { return this.format; }
//
//    // La vraie logique métier (sans exception cette fois !)
//    public String execute() {
//        if ("json".equalsIgnoreCase(format)) {
//            return "[\n  {\"name\": \"Laptop\", \"price\": 1459.00},\n  {\"name\": \"Souris\", \"price\": 51.90}\n]";
//        } else {
//            return "--------------------------\n" +
//                    "| Nom      | Prix        |\n" +
//                    "--------------------------\n" +
//                    "| Laptop   | 1459.00 CHF |\n" +
//                    "| Souris   |   51.90 CHF |\n" +
//                    "--------------------------";
//        }
//    }
//
//    @Override
//    public void run() {
//        // Picocli appelle cette méthode. On affiche le résultat de execute().
//        System.out.println(execute());
//    }
//}
=======
  @Override
  public void run() {
    // Picocli appelle cette méthode. On affiche le résultat de execute().
    System.out.println(execute());
  }
}
>>>>>>> 2e282f70328cf2e89cb31efc3d1d8be5f76bf233
