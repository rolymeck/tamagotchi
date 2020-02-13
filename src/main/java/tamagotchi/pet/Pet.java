package tamagotchi.pet;

import tamagotchi.gfx.AnimationPack;
import tamagotchi.gfx.Assets;
import tamagotchi.game.Game;
import tamagotchi.game.Stat;
import tamagotchi.game.World;
import tamagotchi.entities.Food;
import tamagotchi.utils.PointManager;
import tamagotchi.utils.Timer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class Pet implements Externalizable {
  public static final int SPAWN_X = 240;
  public static final float ANIMATION_SPEED = 0.5f;
  
  private static final long serialVersionUID = 1L;
  
  protected float age;
  protected float happiness;
  protected float hunger;
  protected float waste;
  protected Stage stage;

  protected transient float x;
  protected transient int destination;
  protected transient boolean actualDestination;
  protected transient int velocity;
  protected transient Timer sitTimer;

  protected transient Food food;
  protected transient AnimationPack animPack;

  protected Pet() {
    this.sitTimer = new Timer(2);
    this.x = SPAWN_X;
    this.destination = (int) x;
    this.happiness = Stat.HAPPINESS.getMax();
    this.hunger = Stat.HUNGER.getMin();
    this.waste = Stat.WASTE.getMin();
    this.stage = Stage.BORN;
    this.age = 0;
  }

  public void tick() {
    if (stage == Stage.BORN || stage == Stage.DEAD)
      return;

    animPack.tick(stage);

    velocity = 0;

    World world = Game.getCurrentGame().getWorld();
    if (world.haveFood()) {
      destination = (int) world.getFood().getX();
      actualDestination = true;
      sitTimer.reset();
    }

    if (sitTimer.isStarted()) {
      sitTimer.tick();
    }

    if (!actualDestination && sitTimer.isStarted() && !sitTimer.isFinished()) {
      return;
    }

    if (!actualDestination && (!sitTimer.isStarted() || sitTimer.isFinished())) {
      System.out.println("RANDOM POINT WITHOUT FOOD");
      destination = PointManager.getRandomX();
      actualDestination = true;
      sitTimer.reset();
    }

    if (x != destination) {
      velocity = x > destination ? -1 : 1;
    }

    if (x == destination && world.haveFood()) {
      feed();
      actualDestination = false;
      sitTimer.start();
    }

    if (x == destination && actualDestination) {
      sitTimer.start();
      actualDestination = false;
    }

    x += velocity;

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

    int width = getWidth();
    int height = getHeight();
    final int xPoint = (int) (x) - width / 2;
    final int yPoint = World.FLOOR_Y - height;

    g.drawImage(image, xPoint, yPoint, width, height, null);
  }

  private BufferedImage getCurrentAnimationFrame() {
    if (velocity > 0) {
      return animPack.getPack(stage).right().getCurrentFrame();
    } else if (velocity < 0) {
      return animPack.getPack(stage).left().getCurrentFrame();
    } else {
      return animPack.getPack(stage).front().getCurrentFrame();
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
    stage = Stage.SMALL;
  }

  public void makeMedium() {
    stage = Stage.MEDIUM;
  }

  public void makeLarge() {
    stage = Stage.LARGE;
  }

  private int getWidth() {
    switch (stage) {
      case MEDIUM:
        return 96;
      case LARGE:
        return 128;
      default:
        return 64;
    }
  }

  private int getHeight() {
    switch (stage) {
      case MEDIUM:
        return 96;
      case LARGE:
        return 128;
      default:
        return 64;
    }
  }

  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeFloat(age);
    out.writeObject(stage);
    out.writeFloat(happiness);
    out.writeFloat(hunger);
    out.writeFloat(waste);
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    age = in.readFloat();
    stage = (Stage) in.readObject();
    happiness = in.readFloat();
    hunger = in.readFloat();
    waste = in.readFloat();
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

  @Override
  public String toString() {
    return "Pet{" +
        "age=" + age +
        ", stage=" + stage +
        ", happiness=" + happiness +
        ", hunger=" + hunger +
        ", waste=" + waste +
        ", x=" + x +
        ", destination=" + destination +
        ", actualDestination=" + actualDestination +
        ", xMove=" + velocity +
        ", food=" + food +
        ", animPack=" + animPack +
        '}';
  }

  public enum Stage {
    BORN, SMALL, MEDIUM, LARGE, DEAD
  }
}
