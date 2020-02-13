package tamagotchi.play;

import tamagotchi.game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Sprite {
  private List<Frame> frames;

  public Sprite(BufferedImage image, int y, int height, float xOffset) {
    frames = new ArrayList<>() {{
      add(new Frame(image, 0, y, Game.WIDTH, height, xOffset));
      add(new Frame(image, Game.WIDTH, y, Game.WIDTH, height, xOffset));
    }};
  }

  public void tick() {
    frames.forEach(Frame::tick);
  }

  public void render(Graphics g) {
    frames.forEach(frame -> frame.render(g));
  }

  public void reset() {
    frames.get(0).setX(0);
    frames.get(1).setX(Game.WIDTH);
  }

  private class Frame {

    private final BufferedImage image;
    private float x;
    private final int y;
    private final int width, height;
    private final float xOffset;

    public Frame(BufferedImage image, float x, int y, int width, int height, float xOffset) {
      this.image = image;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.xOffset = xOffset;
    }

    public void tick() {
      x -= xOffset;
      if (x + width <= 0) {
        x = width;
      }
    }

    public void render(Graphics g) {
      g.drawImage(image, (int) x, y, width, height, null);
    }

    public void setX(float x) {
      this.x = x;
    }
  }
}
