import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Cette annotation dit à Jackson de ne pas planter s'il y a des champs dans le JSON qu'on n'a pas
// mis dans cette classe
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
  private String name;

  // Vendure renvoie les prix sous forme d'entiers (centimes). On utilisera des variables basiques
  // pour simplifier.
  private int price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
