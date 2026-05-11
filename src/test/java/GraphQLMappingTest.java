import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphQLMappingTest {

  // L'outil de Jackson pour convertir le JSON
  private final ObjectMapper mapper = new ObjectMapper();

  // Java -> JSON (Vérifier la création de la requête)
  @Test
  void testGraphQLRequestToJson() throws Exception {
    String expectedQuery = "query { products { items { name price } } }";
    GraphQLRequest request = new GraphQLRequest(expectedQuery);

    // Transformation en JSON
    String json = mapper.writeValueAsString(request);

    // On s'attend à obtenir : {"query":"query { products { items { name price } } }"}
    assertTrue(json.contains("\"query\""), "Le JSON doit contenir la clé 'query'");
    assertTrue(json.contains(expectedQuery), "Le JSON doit contenir notre requête exacte");
  }

  // JSON -> Java (Vérifier la lecture de la réponse de Vendure)
  @Test
  void testJsonToProductList() throws Exception {
    // Fausse réponse JSON typique d'une API GraphQL
    // On utilise les blocs """ pour écrire du JSON sur plusieurs lignes facilement (disponible
    // depuis Java 15)
    String fakeVendureResponse =
        """
        {
          "data": {
            "products": {
              "items": [
                { "name": "Laptop", "price": 129900 },
                { "name": "Souris", "price": 2550 }
              ]
            }
          }
        }
        """;

    // On simule la logique de notre VendureService pour extraire le tableau "items"
    JsonNode rootNode = mapper.readTree(fakeVendureResponse);
    JsonNode itemsNode = rootNode.path("data").path("products").path("items");

    List<Product> products = new ArrayList<>();
    if (itemsNode.isArray()) {
      for (JsonNode node : itemsNode) {
        // On transforme le petit bout de JSON en objet Java Product
        products.add(mapper.treeToValue(node, Product.class));
      }
    }

    // Vérifications
    assertEquals(2, products.size(), "Il devrait y avoir 2 produits dans la liste");
    assertEquals("Laptop", products.get(0).getName(), "Le premier produit doit être le Laptop");
    assertEquals(129900, products.get(0).getPrice(), "Le prix doit correspondre (en centimes)");
  }
}
