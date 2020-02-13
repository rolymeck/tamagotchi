package tamagotchi.play;

import tamagotchi.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player {
  private static final int WIDTH = 36;
  private static final int HEIGHT = 36;
  private static final int X = 120;
  private static final int SPAWN_Y = 220;
  private static final float MAX_VELOCITY = 7;
  private static final float ACC = 0.5f;

  private final BufferedImage image;

  private float y;
  private float velocity;
  private Bounds bounds;

  private boolean clicked;

  public Player() {
    image = Assets.poop;
    y = SPAWN_Y;
    bounds = new Bounds();
  }

  public void tick() {
    if (clicked) {
      velocity = -7;
      clicked = false;
      return;
    }
    velocity = Math.min(velocity + ACC, MAX_VELOCITY);
    y += velocity;
    updateBounds();
  }

  public void render(Graphics g) {
    g.drawImage(image, X, (int) y, WIDTH, HEIGHT, null);
  }

  public void setClicked(boolean clicked) {
    this.clicked = clicked;
  }

  public void reset() {
    y = SPAWN_Y;
    velocity = 0;
    clicked = false;
    updateBounds();
  }

  private void updateBounds() {
    bounds.update();
  }

  public List<Rectangle> getBounds() {
    return bounds.getBounds();
  }

  public float getY() {
    return y;
  }

  private class Bounds {
    private List<Rectangle> bounds;

    public Bounds() {
      Rectangle b1 = new Rectangle(X, (int) y + 26, WIDTH, 10);
      Rectangle b2 = new Rectangle(X + 4, (int) y + 19, 28, 7);
      Rectangle b3 = new Rectangle(X + 8, (int) y + 13, 20, 6);
      Rectangle b4 = new Rectangle(X + 11, (int) y + 9, 15, 4);
      Rectangle b5 = new Rectangle(X + 12, (int) y + 3, 8, 6);
      bounds = new ArrayList<>(){{
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
