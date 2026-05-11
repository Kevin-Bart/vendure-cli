import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
  private String name;
  private List<Variant> variants;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Variant> getVariants() {
    return variants;
  }

  public void setVariants(List<Variant> variants) {
    this.variants = variants;
  }

  // Méthode pratique pour récupérer le prix de la première variante
  public int getPrice() {
    if (variants != null && !variants.isEmpty()) {
      return variants.get(0).getPrice();
    }
    return 0;
  }

  // Sous-classe pour lire le tableau des variantes de Vendure
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Variant {
    private int price;

    public int getPrice() {
      return price;
    }

    public void setPrice(int price) {
      this.price = price;
    }
  }
}
