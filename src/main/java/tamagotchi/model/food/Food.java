package tamagotchi.model.food;

import tamagotchi.handler.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Food {

  private static final int DEFAULT_WIDTH = 32, DEFAULT_HEIGHT = 32;

  private float x, y;
  private int width, height;
  private BufferedImage image;

  public Food(BufferedImage image) {
    this.y = World.FLOOR_Y;
    this.width = DEFAULT_WIDTH;
    this.height = DEFAULT_HEIGHT;
    this.image = image;
  }

  public void render(Graphics g) {
    g.drawImage(image, (int) (x) - width / 2, World.FLOOR_Y - height, width, height, null);
  }

  // GETTERS SETTERS

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

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }
}
