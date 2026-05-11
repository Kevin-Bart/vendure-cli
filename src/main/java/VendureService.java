import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class VendureService {
  private final String apiUrl;
  private final HttpClient client;
  private final ObjectMapper mapper;

  public VendureService(String apiUrl) {
    this.apiUrl = apiUrl;
    this.client = HttpClient.newHttpClient(); // Le client HTTP natif de Java
    this.mapper = new ObjectMapper(); // Jackson pour le JSON
  }

  // Méthode pour récupérer la liste des produits (Requête GraphQL)
  public List<Product> getProducts() throws Exception {
    // 1. Définition de la requête GraphQL selon l'énoncé
    String query = "query { products { items { name } } }"; // Simplifiée pour l'exemple
    GraphQLRequest requestObj = new GraphQLRequest(query);

    // 2. Transformation de l'objet en texte JSON
    String jsonPayload = mapper.writeValueAsString(requestObj);

    // 3. Préparation de la requête HTTP POST
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
            .build();

    // 4. Envoi de la requête
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    // 5. Lecture de la réponse JSON pour extraire la liste des produits
    JsonNode rootNode = mapper.readTree(response.body());
    JsonNode itemsNode = rootNode.path("data").path("products").path("items");

    List<Product> products = new ArrayList<>();
    if (itemsNode.isArray()) {
      for (JsonNode node : itemsNode) {
        Product p = mapper.treeToValue(node, Product.class);
        products.add(p);
      }
    }
    return products;
  }
}
