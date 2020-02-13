package tamagotchi.entities;

import tamagotchi.game.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

  protected float x, y;
  protected int width, height;
  protected BufferedImage image;

  Entity(BufferedImage image) {
    this.y = World.FLOOR_Y;
    this.image = image;
  }

  public abstract void render(Graphics g);

  public abstract void tick();

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}
