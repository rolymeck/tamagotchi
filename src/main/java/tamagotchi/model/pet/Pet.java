package tamagotchi.model.pet;

import tamagotchi.gfx.Animation;
import tamagotchi.handler.Handler;
import tamagotchi.handler.Stat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Pet implements Serializable {
  public static final int Y = 0;
  public static final int X = 0;
  public static final int DEFAULT_WIDTH = 32;
  public static final int DEFAULT_HEIGHT = 32;

  protected Handler handler;

  protected final long birthday;
  protected boolean alive;

  protected double happiness;
  protected double hunger;
  protected double waste;

  protected float x;
  protected int width, height;

  protected Animation animFront;
  protected Animation animLeft;
  protected Animation animRight;

  protected Pet(Handler handler) {
    this.handler = handler;

    //tmp
    this.x = X;
    this.width = DEFAULT_WIDTH;
    this.height = DEFAULT_HEIGHT;

    this.happiness = Stat.HAPPINESS.getMax();
    this.hunger = Stat.HUNGER.getMin();
    this.waste = Stat.WASTE.getMin();
    this.alive = true;
    this.birthday = System.currentTimeMillis();
  }

  public void tick() {
    animFront.tick();
    animRight.tick();
    animLeft.tick();
    move();

  }

  public void move() {};

  public void render(Graphics g) {
    g.drawImage(getCurrentAnimationFrame(), (int) (x), Y, width, height, null);
  }

  private BufferedImage getCurrentAnimationFrame(){
    return animFront.getCurrentFrame();
  }

  // GETTERS SETTERS

  public long getBirthday() {
    return birthday;
  }

  public double getHappiness() {
    return happiness;
  }

  public void setHappiness(double happiness) {
    this.happiness = happiness;
  }

  public double getHunger() {
    return hunger;
  }

  public void setHunger(double hunger) {
    this.hunger = hunger;
  }

  public double getWaste() {
    return waste;
  }

  public void setWaste(double waste) {
    this.waste = waste;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }
}
