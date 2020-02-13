package tamagotchi.play;

import tamagotchi.Config;
import tamagotchi.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player {

  private final BufferedImage image;
  private final Bounds bounds;
  private float y;
  private float velocity;
  private boolean clicked;

  public Player() {
    image = Assets.poop;
    y = Config.PLAYER_SPAWN_Y;
    bounds = new Bounds();
  }

  public void tick() {
    if (clicked) {
      velocity = Config.PLAYER_JUMP_VELOCITY;
      clicked = false;
      return;
    }
    velocity = Math.min(velocity + Config.PLAYER_ACC, Config.PLAYER_MAX_VELOCITY);
    y += velocity;
    updateBounds();
  }

  public void render(Graphics g) {
    g.drawImage(image,
        Config.PLAYER_SPAWN_X,
        (int) y,
        Config.PLAYER_WIDTH,
        Config.PLAYER_HEIGHT,
        null);
  }

  public void setClicked(boolean clicked) {
    this.clicked = clicked;
  }

  public void reset() {
    y = Config.PLAYER_SPAWN_Y;
    velocity = 0;
    clicked = false;
    updateBounds();
  }

  public List<Rectangle> getBounds() {
    return bounds.getBounds();
  }

  public float getY() {
    return y;
  }

  private void updateBounds() {
    bounds.update();
  }

  private class Bounds {
    private final List<Rectangle> bounds;

    public Bounds() {
      int x = Config.PLAYER_SPAWN_X;
      Rectangle b1 = new Rectangle(x, (int) y + 26, Config.PLAYER_WIDTH, 10);
      Rectangle b2 = new Rectangle(x + 4, (int) y + 19, 28, 7);
      Rectangle b3 = new Rectangle(x + 8, (int) y + 13, 20, 6);
      Rectangle b4 = new Rectangle(x + 11, (int) y + 9, 15, 4);
      Rectangle b5 = new Rectangle(x + 12, (int) y + 3, 8, 6);
      bounds = new ArrayList<>() {{
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
      }};
    }

    public List<Rectangle> getBounds() {
      return bounds;
    }

    public void update() {
      bounds.get(0).y = (int) y + 26;
      bounds.get(1).y = (int) y + 19;
      bounds.get(2).y = (int) y + 13;
      bounds.get(3).y = (int) y + 9;
      bounds.get(4).y = (int) y + 3;
    }
  }
}
