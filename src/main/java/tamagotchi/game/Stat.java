package tamagotchi.game;

public enum Stat {

  HUNGER(0, 100),
  WASTE(0, 100),
  HAPPINESS(0, 100),
  AGE(0, Float.MAX_VALUE);

  private final float min;
  private final float max;

  Stat(float min, float max) {
    this.min = min;
    this.max = max;
  }

  public float getMin() {
    return min;
  }

  public float getMax() {
    return max;
  }
}
