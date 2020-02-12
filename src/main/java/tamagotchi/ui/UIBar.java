package tamagotchi.ui;

import tamagotchi.game.Game;
import tamagotchi.game.Stat;
import tamagotchi.game.World;
import tamagotchi.model.pet.Pet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIBar extends UIObject {

  private BufferedImage[] images;
  private Stat stat;
  private int size = 0;

  public UIBar(float x, float y, int width, int height, BufferedImage[] images, Stat stat) {
    super(x, y, width, height);
    this.images = images;
    this.stat = stat;
  }

  @Override
  public void tick() {
    World world = Game.getCurrentGame().getWorld();
    int percent = Math.round(world.getPet().getValue(stat));
    int dozens = percent / 10;
    boolean moreThan5 = (percent % 10) >= 5;
    int additive = moreThan5 ? 1 : 0;
    size = dozens + additive;
  }

  @Override
  public void render(Graphics g) {
    World world = Game.getCurrentGame().getWorld();
    if (size > 0 && world.getPet().getStage() != Pet.Stage.DEAD) {
      g.drawImage(images[0], (int) x, (int) y, 10, 22, null);
      int i = 1;
      for (; i < size; i++) {
        g.drawImage(images[i], (int) x + i * 10, (int) y, 10, 22, null);
      }
      g.drawImage(images[images.length - 1], (int) x + i * 10, (int) y, 10, 22, null);
    }
  }

  @Override
  public void onClick() {
  }
}
