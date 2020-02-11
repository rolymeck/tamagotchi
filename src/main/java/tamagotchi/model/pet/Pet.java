package tamagotchi.model.pet;

import tamagotchi.gfx.Animation;
import tamagotchi.gfx.AnimationPack;
import tamagotchi.gfx.Assets;
import tamagotchi.handler.Game;
import tamagotchi.handler.Stat;
import tamagotchi.handler.World;
import tamagotchi.model.entities.Food;
import tamagotchi.utils.PointManager;
import tamagotchi.utils.Timer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Pet implements Serializable {
  public static final int SPAWN_X = 240;
  public static final int DEFAULT_WIDTH = 64;
  public static final int DEFAULT_HEIGHT = 64;
  public static final float ANIMATION_SPEED = 0.5f;

  protected float age; //ser
  protected Stage stage; //ser

  protected float happiness; //ser
  protected float hunger; //ser
  protected float waste; //ser

  protected transient float x;
  protected transient int width, height;

  protected transient int destination;
  protected transient boolean actualDestination;
  protected transient int xMove;
  protected transient Timer moveTimer;

  protected transient Food food;

  protected transient AnimationPack animPack;
  protected transient Animation animFront;
  protected transient Animation animLeft;
  protected transient Animation animRight;

  protected Pet() {
    this.moveTimer = new Timer(2);

    //tmp
    this.x = SPAWN_X;
    this.destination = (int) x;

    this.happiness = Stat.HAPPINESS.getMax();
    this.hunger = Stat.HUNGER.getMin();
    this.waste = Stat.WASTE.getMin();
    this.stage = Stage.BORN;
    this.width = DEFAULT_WIDTH;
    this.height = DEFAULT_HEIGHT;
    this.age = 0;
  }

  public void tick() {
    if (stage == Stage.BORN || stage == Stage.DEAD)
      return;

    animFront.tick();
    animRight.tick();
    animLeft.tick();

    xMove = 0;

    World world = Game.getCurrentGame().getWorld();
    if (world.haveFood()) {
      destination = (int) world.getFood().getX();
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
      destination = PointManager.getRandomX();
      actualDestination = true;
      moveTimer.reset();
    }

    if (x != destination) {
      xMove = x > destination ? -1 : 1;
    }

    if (x == destination && world.haveFood()) {
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
    final Image image;
    switch (stage) {
      case BORN:
        image = Assets.egg;
        break;
      case DEAD:
        image = Assets.grave;
        break;
      default:
        image = getCurrentAnimationFrame();
    }

    final int xPoint = (int) (x) - width / 2;
    final int yPoint = World.FLOOR_Y - height;

    g.drawImage(image, xPoint, yPoint, width, height, null);
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
    Game.getCurrentGame().getWorld().removeFood();
  }

  public void cleanUp() {
    setValue(Stat.WASTE, 0);
  }

  public void incrementValue(Stat stat, final float amount) {
    final float maxValue = stat.getMax();
    final float oldValue = getValue(stat);
    final float modifiedValue = oldValue + amount;
    final float newValue = Math.min(modifiedValue, maxValue);
    setValue(stat, newValue);
  }

  public void decrementValue(Stat stat, final float amount) {
    final float minValue = stat.getMin();
    final float oldValue = getValue(stat);
    final float modifiedValue = oldValue - amount;
    final float newValue = Math.max(modifiedValue, minValue);
    setValue(stat, newValue);
  }

  public float getValue(Stat stat) {
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

  public void setValue(Stat stat, final float value) {
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
        age = value;
        break;
    }
  }

  public void makeSmall() {
    animFront = animPack.small().front();
    animLeft = animPack.small().left();
    animRight = animPack.small().right();
    width = DEFAULT_WIDTH;
    height = DEFAULT_HEIGHT;
    stage = Stage.SMALL;
  }

  public void makeMedium() {
    animFront = animPack.medium().front();
    animLeft = animPack.medium().left();
    animRight = animPack.medium().right();
    width = 96;
    height = 96;
    stage = Stage.MEDIUM;
  }

  public void makeLarge() {
    animFront = animPack.large().front();
    animLeft = animPack.large().left();
    animRight = animPack.large().right();
    width = 128;
    height = 128;
    stage = Stage.LARGE;
  }

  public Food getFood() {
    return food;
  }

  public float getX() {
    return x;
  }

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public enum Stage {
    BORN, SMALL, MEDIUM, LARGE, DEAD
  }

  @Override
  public String toString() {
    return "Pet{" +
        "age=" + age +
        ", stage=" + stage +
        ", happiness=" + happiness +
        ", hunger=" + hunger +
        ", waste=" + waste +
        '}';
  }
}
