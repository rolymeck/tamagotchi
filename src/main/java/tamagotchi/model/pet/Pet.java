package tamagotchi.model.pet;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Pet implements Serializable {
  private final LocalDateTime birthday;
  private final String name;

  private double happiness;
  private double hunger;
  private double waste;
  private boolean alive;

  public Pet(final String name) {
    this.name = name;
    this.happiness = 100.0d;
    this.hunger = 0.0d;
    this.waste = 0.0d;
    this.alive = true;
    this.birthday = LocalDateTime.now();
  }

  public LocalDateTime getBirthday() {
    return birthday;
  }

  public String getName() {
    return name;
  }

  public double getHappiness() {
    return happiness;
  }

  public void setHappiness(double happiness) {
    this.happiness = happiness;
  }

  public double getHunger() {
    return hunger;
  }

  public void setHunger(double hunger) {
    this.hunger = hunger;
  }

  public double getWaste() {
    return waste;
  }

  public void setWaste(double waste) {
    this.waste = waste;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }
}
