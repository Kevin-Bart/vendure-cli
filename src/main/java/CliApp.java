import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

// On définit le nom du programme et on lie la sous-commande ListCommand
@Command(name = "cli", subcommands = { ListCommand.class }, mixinStandardHelpOptions = true)
public class CliApp implements Runnable {

    // L'option --url globale. defaultValue permet de lire la variable d'environnement URL.
    @Option(names = {"--url"}, description = "URL du serveur Vendure",
            defaultValue = "${env:URL:-http://localhost:3000/shop-api}")
    private String url;

    // On garde nos getters/setters pour que nos tests unitaires continuent de fonctionner
    public void setUrl(String url) { this.url = url; }
    public String getUrl() { return this.url; }

    @Override
    public void run() {
        // Ce code s'exécute si l'utilisateur tape juste "cli" sans sous-commande
        System.out.println("Veuillez spécifier une sous-commande (ex: list).");
    }

    // Le point d'entrée officiel du programme
    public static void main(String[] args) {
        int exitCode = new CommandLine(new CliApp()).execute(args);
        System.exit(exitCode);
    }
}