package tamagotchi.model.pet;

import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationPack;
import tamagotchi.gfx.Assets;
import tamagotchi.handler.Handler;
import tamagotchi.handler.Stat;
import tamagotchi.handler.World;
import tamagotchi.model.food.Food;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Pet implements Serializable {
  public static final int X = 240;
  public static final int DEFAULT_WIDTH = 64;
  public static final int DEFAULT_HEIGHT = 64;
  public static final int BORN_AGE = 5;
  public static final int ANIMATION_SPEED = 500;

  protected Handler handler;

  protected final long birthday;
  protected boolean alive;
  protected int age;

  protected double happiness;
  protected double hunger;
  protected double waste;

  protected float x;
  protected int width, height;

  protected int destination;
  protected int xMove;

  protected Food food;

  protected AnimationPack animPack;
  protected Animation animFront;
  protected Animation animLeft;
  protected Animation animRight;

  protected Pet(Handler handler) {
    this.handler = handler;

    //tmp
    this.x = X;
    this.destination = (int) x;
    this.width = DEFAULT_WIDTH;
    this.height = DEFAULT_HEIGHT;

    this.happiness = Stat.HAPPINESS.getMax();
    this.hunger = Stat.HUNGER.getMin();
    this.waste = Stat.WASTE.getMin();
    this.alive = true;
    this.birthday = System.currentTimeMillis();
    this.age = 0;
  }

  public void tick() {
    if (!isAlive())
      return;

    age++;

    if (age < BORN_AGE)
      return;

    if (handler.getWorld().getFood() != null) {
      destination = (int) handler.getWorld().getFood().getX();
    }

    xMove = 0;

    if (x != destination) {
      xMove = x > destination ? -3 : 3;
    }

    move();

    if (age == 10) {
      animFront = animPack.medium().front();
      animLeft = animPack.medium().left();
      animRight = animPack.medium().right();
      width += 32;
      height += 32;
    }

    if (age == 15) {
      animFront = animPack.large().front();
      animLeft = animPack.large().left();
      animRight = animPack.large().right();
      width += 32;
      height += 32;
    }

    animFront.tick();
    animRight.tick();
    animLeft.tick();
    move();

    final boolean happinessIsMin = happiness == Stat.HAPPINESS.getMin();

    if (happinessIsMin) {
      setAlive(false);
      return;
    }

    final boolean hungerIsMax = hunger == Stat.HUNGER.getMax();
    final boolean wasteIsMax = waste == Stat.HUNGER.getMax();

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

  public void move() {
    x += xMove;
  }

  public void render(Graphics g) {
    if (!isAlive()) {
      g.drawImage(Assets.grave, (int) (x) - width / 2, World.FLOOR_Y - height, width, height, null);
      return;
    }
    if (age < 5) {
      g.drawImage(Assets.egg, (int) (x) - width / 2, World.FLOOR_Y - height, width, height, null);
    } else {
      g.drawImage(getCurrentAnimationFrame(), (int) (x) - width / 2, World.FLOOR_Y - height, width, height, null);
    }
  }

  private BufferedImage getCurrentAnimationFrame() {
    if (xMove > 0) {
      return animRight.getCurrentFrame();
    } else if (xMove < 0) {
      return animLeft.getCurrentFrame();
    } else {
      return animFront.getCurrentFrame();
    }
  }

  public void feed() {
    decrementValue(Stat.HUNGER, 50);
  }

  public void cleanUp() {
    setValue(Stat.WASTE, 0);
  }

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

  public double getValue(Stat stat) {
    switch (stat) {
      case HUNGER:
        return hunger;
      case WASTE:
        return waste;
      case HAPPINESS:
        return happiness;
      case AGE:
        return age;
    }
    return 0; //ignore
  }

  public void setValue(Stat stat, final double value) {
    switch (stat) {
      case HUNGER:
        hunger = value;
        break;
      case WASTE:
        waste = value;
        break;
      case HAPPINESS:
        happiness = value;
        break;
      case AGE:
        age = (int) value;
        break;
    }
  }

  // GETTERS SETTERS


  public Food getFood() {
    return food;
  }

  public long getBirthday() {
    return birthday;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }
}
