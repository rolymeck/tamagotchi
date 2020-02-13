package tamagotchi.entities;

import tamagotchi.gfx.Assets;
import tamagotchi.Config;

import java.awt.*;

public class Poop extends Entity {

  public Poop(final float x) {
    super(Assets.poop);
    this.width = Config.POOP_WIDTH;
    this.height = Config.POOP_HEIGHT;
    this.x = x;
  }

  public void render(final Graphics g) {
    int x = (int) this.x - width / 2;
    int y = Config.GAME_FLOOR_Y - height;
    g.drawImage(image, x, y, width, height, null);
  }
}
