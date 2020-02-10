package tamagotchi.model.pet;

import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationPack;
import tamagotchi.gfx.Assets;
import tamagotchi.handler.Handler;
import tamagotchi.handler.Stat;
import tamagotchi.handler.World;
import tamagotchi.model.entities.Food;
import tamagotchi.utils.PointManager;
import tamagotchi.utils.Timer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Pet implements Serializable {
  public static final int X = 240;
  public static final int DEFAULT_WIDTH = 64;
  public static final int DEFAULT_HEIGHT = 64;

  public static final int ANIMATION_SPEED = 500;
  protected final long birthday;
  protected Handler handler;
  protected boolean alive;
  protected int age;

  protected double happiness;
  protected double hunger;
  protected double waste;

  protected float x;
  protected int width, height;

  protected int destination;
  protected boolean actualDestination;
  protected int xMove;
  protected Timer moveTimer;

  protected Food food;

  protected AnimationPack animPack;
  protected Animation animFront;
  protected Animation animLeft;
  protected Animation animRight;

  protected Pet(Handler handler) {
    this.handler = handler;
    this.moveTimer = new Timer(2);

    //tmp
    this.x = X;
    this.destination = (int) x;

    this.happiness = Stat.HAPPINESS.getMax();
    this.hunger = Stat.HUNGER.getMin();
    this.waste = Stat.WASTE.getMin();
    this.alive = true;
    this.birthday = System.currentTimeMillis();
    this.age = 0;
  }

  public void tick() {
    if (!isAlive() || age <= World.BORN_AGE)
      return;

    animFront.tick();
    animRight.tick();
    animLeft.tick();

    xMove = 0;

    if (handler.getWorld().haveFood()) {
      destination = (int) handler.getWorld().getFood().getX();
      actualDestination = true;
      moveTimer.reset();
    }

    if (moveTimer.isStarted()) {
      moveTimer.tick();
    }

    if (!actualDestination && moveTimer.isStarted() && !moveTimer.isFinished()) {
      return;
    }

    if (!actualDestination && (!moveTimer.isStarted() || moveTimer.isFinished())) {
      System.out.println("RANDOM POINT WITHOUT FOOD");
      destination = PointManager.getRandomX(handler);
      actualDestination = true;
      moveTimer.reset();
    }

    if (x != destination) {
      xMove = x > destination ? -1 : 1;
    }

    if (x == destination && handler.getWorld().haveFood()) {
      feed();
      actualDestination = false;
      moveTimer.start();
    }

    if (x == destination && actualDestination) {
      moveTimer.start();
      actualDestination = false;
    }

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
    incrementValue(Stat.WASTE, 10);
    handler.getWorld().feed();
  }

  public void cleanUp() {
    setValue(Stat.WASTE, 0);
  }

  public void incrementValue(Stat stat, final double amount) {
    final double maxValue = stat.getMax();
    final double oldValue = getValue(stat);
    final double modifiedValue = oldValue + amount;
    final double newValue = Math.min(modifiedValue, maxValue);
    setValue(stat, newValue);
  }

  public void decrementValue(Stat stat, final double amount) {
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

  public void makeSmall() {
    animFront = animPack.small().front();
    animLeft = animPack.small().left();
    animRight = animPack.small().right();
    width = DEFAULT_WIDTH;
    height = DEFAULT_HEIGHT;
  }

  public void makeMedium() {
    animFront = animPack.medium().front();
    animLeft = animPack.medium().left();
    animRight = animPack.medium().right();
    width = 96;
    height = 96;
  }

  public void makeLarge() {
    animFront = animPack.large().front();
    animLeft = animPack.large().left();
    animRight = animPack.large().right();
    width = 128;
    height = 128;
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

  public float getX() {
    return x;
  }
}
