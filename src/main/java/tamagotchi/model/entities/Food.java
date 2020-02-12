package tamagotchi.model.entities;

import tamagotchi.game.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Food extends Entity {

  private static final int DEFAULT_WIDTH = 48, DEFAULT_HEIGHT = 48;

  public Food(BufferedImage image) {
    super(image);
    this.width = DEFAULT_WIDTH;
    this.height = DEFAULT_HEIGHT;
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(image, (int) (x) - width / 2, World.FLOOR_Y - height, width, height, null);
  }

  @Override
  public void tick() {
  }


}
