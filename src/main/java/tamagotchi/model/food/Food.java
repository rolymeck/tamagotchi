package tamagotchi.model.food;

public enum Food {

  CAT("whiskas"),
  DOG("pedigree"),
  BIRD("seed");

  private final String name;

  Food(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
