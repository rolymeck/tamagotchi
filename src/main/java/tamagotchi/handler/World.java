package tamagotchi.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tamagotchi.model.entities.EntityManager;
import tamagotchi.model.entities.Food;
import tamagotchi.model.entities.Poop;
import tamagotchi.model.pet.Pet;
import tamagotchi.states.DeathState;
import tamagotchi.states.State;

import java.awt.*;
import java.io.Serializable;

public class World implements Serializable {

  private static transient final Logger log = LogManager.getLogger(World.class);
  public static final int BORN_AGE = 5;
  public static final int FLOOR_Y = 370;
  public static final int NEW_GAME_WAIT_SEC = 20;

  private final int period;
  private Pet pet;
  private Handler handler;

  private EntityManager entityManager;

  private int ticks = 0;

  //private LocalDateTime lastFeeding;
  //private LocalDateTime lastCleaning;

  public World(Handler handler, int period) {
    this.handler = handler;
    this.period = period;
    this.entityManager = new EntityManager(handler);
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
    pet.tick();
    if (ticks < Game.FPS * period / 10) {
      ticks++;
      return;
    }

    ticks = 0;
    update();
  }

  public void render(Graphics g) {
    entityManager.render(g);
    pet.render(g);
  }

  private void update() {
    if (!pet.isAlive()) {
      return;
    }

    if (pet.getValue(Stat.AGE) == 15) {
      pet.makeMedium();
    }

    if (pet.getValue(Stat.AGE) == 30) {
      pet.makeLarge();
    }

    if ((int) (pet.getValue(Stat.WASTE) / 20) > entityManager.poopsAmount()) {
      entityManager.addPoop(new Poop(pet.getX()));
    }

    pet.incrementValue(Stat.AGE, 1);

    if (pet.getValue(Stat.AGE) < BORN_AGE) {
      return;
    }

    final boolean happinessIsMin = pet.getValue(Stat.HAPPINESS) == Stat.HAPPINESS.getMin();

    if (happinessIsMin) {
      pet.setAlive(false);
      State.setState(handler.getGame().deathState, handler);
      DeathState ds = (DeathState) (handler.getGame().deathState);
      ds.getTimer().start();
      return;
    }

    final boolean hungerIsMax = pet.getValue(Stat.HUNGER) == Stat.HUNGER.getMax();
    final boolean wasteIsMax = pet.getValue(Stat.WASTE) == Stat.HUNGER.getMax();

    if (hungerIsMax) {
      pet.decrementValue(Stat.HAPPINESS, 1);
    } else {
      pet.incrementValue(Stat.HUNGER, 1);
      pet.incrementValue(Stat.HAPPINESS, 1);
    }

    if (wasteIsMax) {
      pet.decrementValue(Stat.HAPPINESS, 1);
    } else {
      pet.incrementValue(Stat.WASTE, 0.5);
    }

  }
  // 1st level of abstraction


  // death stage
  public void wipe() {
  }

  public boolean haveFood() {
    return entityManager.haveFood();
  }

  public void setFood(Food food) {
    entityManager.addFood(food);
  }

  public Food getFood() {
    return entityManager.getFood();
  }

  public void cleanUp() {
    entityManager.removePoops();
  }

  public void feed() {
    entityManager.removeFood();
  }

  // GETTERS SETTERS

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