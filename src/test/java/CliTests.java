import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CliTests {

    // Test 1 : Vérifier que l'URL peut être configurée (Spécification : --url)
    @Test
    void testCliAcceptsGlobalUrl() {
        CliApp app = new CliApp();
        String expectedUrl = "http://localhost:3000/shop-api";
        app.setUrl(expectedUrl);
        assertEquals(expectedUrl, app.getUrl(), "L'URL globale doit être correctement enregistrée.");
    }

    // Test 2 : Vérifier le format par défaut (Spécification : format table par défaut)
    @Test
    void testListCommandDefaultFormatIsTable() {
        ListCommand listCmd = new ListCommand();
        assertEquals("table", listCmd.getFormat(), "Le format par défaut doit être 'table'.");
    }

    // Test 3 : Vérifier le format JSON (Spécification : --format json)
    @Test
    void testListCommandAcceptsJsonFormat() {
        ListCommand listCmd = new ListCommand();
        listCmd.setFormat("json");
        assertEquals("json", listCmd.getFormat(), "La commande doit accepter le format 'json'.");
    }

//    // Old Test 4 : Vérifier l'exécution (Spécification : afficher les produits avec nom et prix)
//    @Test
//    void testListCommandExecutionThrowsExceptionInitially() {
//        ListCommand listCmd = new ListCommand();
//        Exception exception = assertThrows(UnsupportedOperationException.class, listCmd::execute);
//        assertEquals("Fonctionnalité pas encore implémentée !", exception.getMessage());
//    }

    // Test 4 : Vérifier l'exécution (Spécification : afficher les produits statiques)
    @Test
    void testListCommandExecutionReturnsStaticList() {
        ListCommand listCmd = new ListCommand();
        String result = listCmd.execute();

        // On vérifie que la réponse statique contient bien nos produits
        assertTrue(result.contains("Laptop"), "Le résultat doit contenir 'Laptop'");
        assertTrue(result.contains("1459"), "Le résultat doit contenir le prix");
    }
}