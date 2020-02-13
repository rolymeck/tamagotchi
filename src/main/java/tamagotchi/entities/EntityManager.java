package tamagotchi.entities;

import tamagotchi.Config;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {

  private final List<Poop> poops;
  private Food food;

  public EntityManager() {
    this.poops = new ArrayList<>();
  }

  public void addPoop(final Poop poop) {
    if (poops.size() <= Config.POOPS_MAX_AMOUNT) {
      poops.add(poop);
    }
  }

  public void removePoops() {
    poops.clear();
  }

  public void addFood(final Food food) {
    if (this.food != null) {
      return;
    }
    this.food = food;
  }

  public void removeFood() {
    this.food = null;
  }

  public void render(final Graphics g) {
    poops.forEach(poop -> poop.render(g));
    if (food != null) {
      food.render(g);
    }
  }

  public boolean haveFood() {
    return food != null;
  }

  public Food getFood() {
    return food;
  }

  public int poopsAmount() {
    return poops.size();
  }
}
