package tamagotchi.game;

import tamagotchi.io.SLManager;
import tamagotchi.model.entities.EntityManager;
import tamagotchi.model.entities.Food;
import tamagotchi.model.entities.Poop;
import tamagotchi.model.pet.Pet;
import tamagotchi.states.DeathState;
import tamagotchi.states.EState;
import tamagotchi.states.State;

import java.awt.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class World implements Externalizable {
  public static final int BORN_AGE = 5;
  public static final int FLOOR_Y = 370;
  public static final int NEW_GAME_WAIT_SEC = 20;
  public static final int PERIOD = 100;
  private static final long serialVersionUID = 1L;
  private Pet pet; //ser
  private long saveTime; //ser
  private transient EntityManager entityManager;
  private transient int updateTicks = 0;
  private transient int saveTicks = 0;

  public World() {
    this.entityManager = new EntityManager();
  }

  public void tick() {
    if (State.getState() == Game.getCurrentGame().getState(EState.GAME)) {
      pet.tick();
    }

    if (updateTicks < PERIOD) {
      updateTicks++;
    } else {
      updateTicks = 0;
      updateStats();
    }

    if (saveTicks < Game.getFPS()) {
      saveTicks++;
    } else {
      saveTicks = 0;
      save();
    }
  }

  public void render(Graphics g) {
    entityManager.render(g);
    pet.render(g);
  }

  void updateStats() {
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
      DeathState deathState = (DeathState) Game.getCurrentGame().getState(EState.DEATH);
      State.setState(deathState);
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

  private void save() {
    SLManager.saveWorld(this);
    saveTime = System.currentTimeMillis();
    System.out.println(toString());
  }

  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeObject(pet);
    out.writeLong(saveTime);
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    pet = (Pet) in.readObject();
    saveTime = in.readLong();
  }

  public void cleanUp() {
    entityManager.removePoops();
  }

  public void removeFood() {
    entityManager.removeFood();
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

  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }

  public long getSaveTime() {
    return saveTime;
  }

  @Override
  public String toString() {
    return "World{" +
        "period=" + PERIOD +
        ", pet=" + pet +
        ", saveTime=" + saveTime +
        '}';
  }
}