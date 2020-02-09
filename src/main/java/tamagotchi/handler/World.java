package tamagotchi.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tamagotchi.model.pet.Pet;
import tamagotchi.model.world.Stage;
import tamagotchi.model.world.Stat;

import java.io.Serializable;

public class World implements Serializable {

  private static transient final Logger log = LogManager.getLogger(World.class);

  private final int speed;
  private Pet pet;
  private Stage stage;

  //private LocalDateTime lastFeeding;
  //private LocalDateTime lastCleaning;

  public World(int speed) {
    this.speed = speed;
    this.stage = Stage.BIRTH;
    log.debug("World created with speed " + speed);
  }

  public Stage getStage() {
    return stage;
  }

  public int getSpeed() {
    return speed;
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
    this.stage = Stage.LIFE;
  }

  // life stage - work with pet stats
  // 2nd level of abstraction
  public void update() {

    if (pet == null) {
      log.error("Method 'update' called with pet == null");
      return;
    }

    if (!pet.isAlive()) {
      log.error("Method 'update' called with a dead pet");
      return;
    }

    final boolean happinessIsMin = getValue(Stat.HAPPINESS) == Stat.HAPPINESS.getMin();

    if (happinessIsMin) {
      this.pet.setAlive(false);
      this.stage = Stage.DEATH;
      return;
    }

    final boolean hungerIsMax = getValue(Stat.HUNGER) == Stat.HUNGER.getMax();
    final boolean wasteIsMax = getValue(Stat.HUNGER) == Stat.HUNGER.getMax();

    if (hungerIsMax) {
      decrementValue(Stat.HAPPINESS, 1);
    } else {
      incrementValue(Stat.HUNGER, 1);
    }

    if (wasteIsMax) {
      decrementValue(Stat.HAPPINESS, 1);
    } else {
      incrementValue(Stat.WASTE, 1);
    }
  }

  public void feed() {
    if (pet == null) {
      log.error("Method 'feed' called with pet == null");
      return;
    }

    if (!pet.isAlive()) {
      log.error("Method 'feed' called with a dead pet");
      return;
    }

    decrementValue(Stat.HUNGER, 50);
  }

  public void cleanUp() {
    if (pet == null) {
      log.error("Method 'cleanUp' called with pet == null");
      return;
    }

    if (!pet.isAlive()) {
      log.error("Method 'cleanUp' called with a dead pet");
      return;
    }

    setValue(Stat.WASTE, 0);
  }

  // 1st level of abstraction
  private void incrementValue(Stat stat, final double amount) {
    final double maxValue = stat.getMax();
    final double oldValue = getValue(stat);
    final double modifiedValue = oldValue + amount;
    final double newValue = Math.min(modifiedValue, maxValue);
    setValue(stat, newValue);
  }

  private void decrementValue(Stat stat, final double amount) {
    final double minValue = stat.getMin();
    final double oldValue = getValue(stat);
    final double modifiedValue = oldValue - amount;
    final double newValue = Math.max(modifiedValue, minValue);
    setValue(stat, newValue);
  }

  private double getValue(Stat stat) {
    if (pet == null) {
      log.error("Method 'getValue' called with pet == null");
      throw new RuntimeException("Method 'getValue' called with pet == null");
    }

    if (!pet.isAlive()) {
      log.error("Method 'getValue' called with a dead pet");
      throw new RuntimeException("Method 'getValue' called with a dead pet");
    }

    switch (stat) {
      case HUNGER:
        return pet.getHunger();
      case WASTE:
        return pet.getWaste();
      case HAPPINESS:
        return pet.getHappiness();
    }
    return 0; //ignore
  }

  private void setValue(Stat stat, final double value) {
    if (pet == null) {
      log.error("Method 'setValue' called with pet == null");
      throw new RuntimeException("Method 'setValue' called with pet == null");
    }

    if (!pet.isAlive()) {
      log.error("Method 'setValue' called with a dead pet");
      throw new RuntimeException("Method 'setValue' called with a dead pet");
    }

    switch (stat) {
      case HUNGER:
        pet.setHunger(value);
        break;
      case WASTE:
        pet.setWaste(value);
        break;
      case HAPPINESS:
        pet.setHappiness(value);
        break;
    }
  }

  // death stage
  public void wipe() {
    this.pet = null;
    this.stage = Stage.BIRTH;
  }

}
