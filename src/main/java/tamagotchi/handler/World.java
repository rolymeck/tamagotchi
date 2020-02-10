package tamagotchi.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tamagotchi.model.food.Food;
import tamagotchi.model.pet.Pet;

import java.awt.*;
import java.io.Serializable;

public class World implements Serializable {

  private static transient final Logger log = LogManager.getLogger(World.class);
  public static final int FLOOR_Y = 370;

  private final int period;
  private Pet pet;
  private Handler handler;
  private Food food;

  private int ticks = 0;

  //private LocalDateTime lastFeeding;
  //private LocalDateTime lastCleaning;

  public World(Handler handler, int period) {
    this.handler = handler;
    this.period = period;
    log.debug("World created with period " + period);
  }

  //load
  public void skip() {
    //deserialization
  }

  // birth stage
  public void setPet(Pet pet) {
    if (pet == null) {
      log.error("Method 'setPet' called with pet == null");
      return;
    }
    this.pet = pet;
  }

  // life stage - work with pet stats
  // 2nd level of abstraction
  public void tick() {

    if (ticks < Game.FPS * period / 3) {
      ticks++;
      return;
    }

    ticks = 0;

    pet.tick();

  }


  public void render(Graphics g) {
    if (food != null) {
      food.render(g);
    }
    pet.render(g);
  }

  // 1st level of abstraction


  // death stage
  public void wipe() {
  }


  // GETTERS SETTERS


  public Food getFood() {
    return food;
  }

  public void setFood(Food food) {
    this.food = food;
  }

  public Pet getPet() {
    return pet;
  }

  public Handler getHandler() {
    return handler;
  }

  public void setHandler(Handler handler) {
    this.handler = handler;
  }


  public int getPeriod() {
    return period;
  }

}