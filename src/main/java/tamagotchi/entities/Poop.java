package tamagotchi.entities;

import tamagotchi.gfx.Assets;
import tamagotchi.game.World;

import java.awt.*;

public class Poop extends Entity {

  private static final int DEFAULT_WIDTH = 32, DEFAULT_HEIGHT = 32;

  public Poop(float x) {
    super(Assets.poop);
    this.width = DEFAULT_WIDTH;
    this.height = DEFAULT_HEIGHT;
    this.x = x;
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(image, (int) (x) - width / 2, World.FLOOR_Y - height, width, height, null);
  }

  @Override
  public void tick() {
  }
}
