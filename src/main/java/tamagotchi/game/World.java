package tamagotchi.game;

import tamagotchi.Config;
import tamagotchi.entities.EntityManager;
import tamagotchi.entities.Food;
import tamagotchi.entities.Poop;
import tamagotchi.pet.Pet;
import tamagotchi.states.DeathState;
import tamagotchi.states.EState;
import tamagotchi.states.State;
import tamagotchi.utils.SLManager;

import java.awt.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class World implements Externalizable {
  private static final long serialVersionUID = 1L;

  private final transient EntityManager entityManager;
  private transient int updateTicks = 0;
  private transient int saveTicks = 0;

  private Pet pet;
  private long saveTime;

  public World() {
    this.entityManager = new EntityManager();
  }

  public void tick() {
    if (State.getState() == Game.getCurrentGame().getState(EState.GAME)) {
      pet.tick();
    }

    if (updateTicks < Config.WORLD_PERIOD) {
      updateTicks++;
    } else {
      updateTicks = 0;
      updateStats();
    }

    if (saveTicks < Game.FPS) {
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

  void updateStats() {
    if (pet.getStage() == Pet.Stage.DEAD) {
      return;
    }

    int poopsAmount = (int) (pet.getValue(Stat.WASTE) / Stat.WASTE.getMax() / Config.POOPS_MAX_AMOUNT);
    if (poopsAmount > entityManager.poopsAmount()) {
      entityManager.addPoop(new Poop(pet.getX()));
    }

    pet.incrementValue(Stat.AGE, Config.AGE_STEP);

    if (pet.getStage() == Pet.Stage.BORN &&
        pet.getValue(Stat.AGE) > Config.AGE_SMALL) {
      pet.makeSmall();
    }

    if (pet.getStage() == Pet.Stage.BORN) {
      return;
    }

    if (pet.getStage() == Pet.Stage.SMALL &&
        pet.getValue(Stat.AGE) > Config.AGE_MEDIUM) {
      pet.makeMedium();
    }

    if (pet.getStage() == Pet.Stage.MEDIUM &&
        pet.getValue(Stat.AGE) > Config.AGE_LARGE) {
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
      pet.decrementValue(Stat.HAPPINESS, Config.HAPPINESS_STEP);
    } else {
      pet.incrementValue(Stat.HUNGER, Config.HUNGER_STEP);
      pet.incrementValue(Stat.HAPPINESS, Config.HAPPINESS_STEP);
    }

    if (wasteIsMax) {
      pet.decrementValue(Stat.HAPPINESS, Config.HAPPINESS_STEP);
    } else {
      pet.incrementValue(Stat.WASTE, Config.WASTE_STEP);
    }
  }

  private void save() {
    SLManager.saveWorld(this);
    saveTime = System.currentTimeMillis();
  }
}