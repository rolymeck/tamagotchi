package tamagotchi.model.world;

public enum Stat {

  HUNGER(0, 100),
  WASTE(0, 100),
  HAPPINESS(0, 100);

  private final double min;
  private final double max;

  Stat(double min, double max) {
    this.min = min;
    this.max = max;
  }

  public double getMin() {
    return min;
  }

  public double getMax() {
    return max;
  }
}
