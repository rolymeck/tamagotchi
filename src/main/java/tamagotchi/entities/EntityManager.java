package tamagotchi.entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {

  private Food food;
  private List<Poop> poops;

  public EntityManager() {
    this.poops = new ArrayList<>();
  }

  public void addPoop(Poop poop) {
    if (poops.size() < 5) {
      poops.add(poop);
    }
  }

  public void removePoops() {
    poops.clear();
  }

  public void addFood(Food food) {
    if (this.food != null) {
      return;
    }
    this.food = food;
  }

  public void removeFood() {
    this.food = null;
  }


  public void render(Graphics g) {
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
