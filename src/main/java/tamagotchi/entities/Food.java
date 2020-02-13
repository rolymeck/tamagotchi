package tamagotchi.entities;

import tamagotchi.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Food extends Entity {

  public Food(final BufferedImage image) {
    super(image);
    this.width = Config.FOOD_WIDTH;
    this.height = Config.FOOD_HEIGHT;
  }

  public void render(final Graphics g) {
    int x = (int) this.x - width / 2;
    int y = Config.GAME_FLOOR_Y - height;
    g.drawImage(image, x, y, width, height, null);
  }
}
