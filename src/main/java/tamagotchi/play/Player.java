package tamagotchi.play;

import tamagotchi.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
  private static final int WIDTH = 51;
  private static final int HEIGHT = 36;
  private static final int SPAWN_X = 120;
  private static final int SPAWN_Y = 220;
  private static final float MAX_VELOCITY = 10;
  private static final float ACC = 2;

  private BufferedImage image;
  private final int x;
  private float y;
  private float velocity;
  private final int width, height;
  private final Rectangle bounds;
  private boolean clicked;
  private boolean alive;

  public Player() {
    image = Assets.poop;
    x = SPAWN_X;
    y = SPAWN_Y;
    width = WIDTH;
    height = HEIGHT;
    this.bounds = new Rectangle(x, (int) y, width, height);
    this.alive = true;
  }

  public void tick() {
    if (!alive) {
      return;
    }

    if (clicked) {
      velocity = -5;
      clicked = false;
      return;
    }
    velocity = Math.min(velocity + ACC, MAX_VELOCITY);
    y += velocity;
    bounds.y = (int) y;
  }

  public void render(Graphics g) {
    g.drawImage(image, x, (int) y, width, height, null);
  }

  public boolean isClicked() {
    return clicked;
  }

  public void setClicked(boolean clicked) {
    this.clicked = clicked;
  }
}
