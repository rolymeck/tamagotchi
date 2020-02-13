package tamagotchi.entities;

import java.awt.image.BufferedImage;

public abstract class Entity {

  protected final BufferedImage image;
  protected float x;
  protected int width, height;

  Entity(final BufferedImage image) {
    this.image = image;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }
}
