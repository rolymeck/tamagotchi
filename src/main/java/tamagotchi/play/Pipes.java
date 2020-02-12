package tamagotchi.play;

import tamagotchi.gfx.Assets;
import tamagotchi.utils.PointManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Pipes {

  private static final int WIDTH = 39;
  private static final int HEIGHT = 615;
  private static final float X_OFFSET = 2f;

  private List<Pipe> pipes;

  public Pipes() {
    Pipe first = new Pipe(480, PointManager.getRandomY(), X_OFFSET);
    Pipe second = new Pipe(480 + 186, PointManager.getRandomY(), X_OFFSET);
    Pipe third = new Pipe(480 + 186 + 186, PointManager.getRandomY(), X_OFFSET);
    first.setPrev(third);
    second.setPrev(first);
    third.setPrev(second);

    pipes = new ArrayList<>() {{
      add(first);
      add(second);
      add(third);
    }};
  }

  public void tick() {
    pipes.forEach(Pipe::tick);
  }

  public void render(Graphics g) {
    pipes.forEach(pipe -> pipe.render(g));
  }

  private class Pipe {
    private BufferedImage image;
    private float x;
    private int y;
    private int width, height;
    private Rectangle bounds;
    private final float xOffset;
    private Pipe prev;

    public Pipe(int x, int y, float xOffset) {
      image = Assets.pipes;
      this.x = x;
      this.y = y;
      width = WIDTH;
      height = HEIGHT;
      bounds = new Rectangle(x, y + 240, 39, 135);
      this.xOffset = xOffset;
    }

    public void tick() {
      x -= xOffset;
      bounds.x = (int) x;
      if (x + width <= 0) {
        x = prev.getX() + 186;
        bounds.x = (int) x;
        y = PointManager.getRandomY();
        bounds.y = y;
      }

    }

    public void render(Graphics g) {
      g.drawImage(image, (int) x, y, width, height, null);
    }

    public float getX() {
      return x;
    }

    public void setPrev(Pipe prev) {
      this.prev = prev;
    }
  }
}
