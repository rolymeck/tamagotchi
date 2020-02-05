package tamagotchi.model.pet;

import tamagotchi.model.food.Food;

public class Cat extends Pet {
  public Cat(String name) {
    super(name);
    this.food = Food.CAT;
  }
}
