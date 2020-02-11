package tamagotchi.handler;

import tamagotchi.model.entities.EntityManager;
import tamagotchi.model.entities.Food;
import tamagotchi.model.entities.Poop;
import tamagotchi.model.pet.Pet;
import tamagotchi.states.DeathState;
import tamagotchi.states.EState;
import tamagotchi.states.State;

import java.awt.*;
import java.io.Serializable;

public class World implements Serializable {

  public static final int BORN_AGE = 5;
  public static final int FLOOR_Y = 370;
  public static final int NEW_GAME_WAIT_SEC = 20;

  private final int period; //ser
  private Pet pet; //ser
  private Handler handler;
  private EntityManager entityManager;
  private int ticks = 0;

  public World(Handler handler, int period) {
    this.handler = handler;
    this.period = period;
    this.entityManager = new EntityManager(handler);
  }

  public void skip() {
    //deserialization
  }

  public void tick() {
    pet.tick();
    if (ticks < period) {
      ticks++;
      return;
    }

    ticks = 0;
    updateStats();
  }

  public void render(Graphics g) {
    entityManager.render(g);
    pet.render(g);
  }

  private void updateStats() {
    if (pet.getStage() == Pet.Stage.DEAD) {
      return;
    }

    if ((int) (pet.getValue(Stat.WASTE) / 20) > entityManager.poopsAmount()) {
      entityManager.addPoop(new Poop(pet.getX()));
    }

    pet.incrementValue(Stat.AGE, 0.33f);

    if (pet.getStage() == Pet.Stage.BORN && pet.getValue(Stat.AGE) > BORN_AGE) {
      pet.makeSmall();
    }

    if (pet.getStage() == Pet.Stage.BORN) {
      return;
    }

    if (pet.getStage() == Pet.Stage.SMALL && pet.getValue(Stat.AGE) > 15) {
      pet.makeMedium();
    }

    if (pet.getStage() == Pet.Stage.MEDIUM && pet.getValue(Stat.AGE) > 30) {
      pet.makeLarge();
    }

    final boolean happinessIsMin = pet.getValue(Stat.HAPPINESS) == Stat.HAPPINESS.getMin();

    if (happinessIsMin) {
      pet.setStage(Pet.Stage.DEAD);
      DeathState deathState = (DeathState) handler.getGame().getStates().get(EState.DEATH);
      State.setState(deathState, handler);
      deathState.getTimer().start();
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
      pet.incrementValue(Stat.WASTE, 0.5f);
    }
  }

  public boolean haveFood() {
    return entityManager.haveFood();
  }

  public Food getFood() {
    return entityManager.getFood();
  }

  public void setFood(Food food) {
    entityManager.addFood(food);
  }

  public void cleanUp() {
    entityManager.removePoops();
  }

  public void feed() {
    entityManager.removeFood();
  }

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }

  public Handler getHandler() {
    return handler;
  }

  public void setHandler(Handler handler) {
    this.handler = handler;
  }
}