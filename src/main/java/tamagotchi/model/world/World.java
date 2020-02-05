package tamagotchi.model.world;

import tamagotchi.model.pet.Pet;

import java.io.Serializable;

public class World implements Serializable {

  private final int speed;
  private Pet pet;

  public World(int speed) {
    this.speed = speed;
  }

  public int getSpeed() {
    return speed;
  }

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }
}
