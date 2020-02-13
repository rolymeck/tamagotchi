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
  private int score;

  public Pipes() {
    Pipe first = new Pipe(480, PointManager.getRandomY());
    Pipe second = new Pipe(480 + 186, PointManager.getRandomY());
    Pipe third = new Pipe(480 + 186 + 186, PointManager.getRandomY());
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

  public void reset() {
    pipes.get(0).setX(480);
    pipes.get(1).setX(480 + 186);
    pipes.get(2).setX(480 + 186 + 186);

    pipes.forEach(pipe -> pipe.setY(PointManager.getRandomY()));
    pipes.forEach(Pipe::updateBounds);

    score = 0;
  }

  public boolean checkCollision(List<Rectangle> player) {
    return pipes.stream().anyMatch((p) -> p.checkCollision(player));
  }

  public int getScore() {
    return score;
  }

  private class Pipe {
    private final BufferedImage image;
    private float x;
    private int y;
    private List<Rectangle> bounds;
    private Pipe prev;
    private boolean stocked;

    public Pipe(int x, int y) {
      image = Assets.pipes;
      this.x = x;
      this.y = y;
      bounds = new ArrayList<>() {{
        add(new Rectangle(x, y, WIDTH, 240));
        add(new Rectangle(x, y + 378, WIDTH, 240));
      }};

    }

    public void tick() {
      x -= X_OFFSET;
      bounds.forEach((b) -> b.x = (int) x);
      if (x + WIDTH <= 0) {
        x = prev.getX() + 186;
        bounds.forEach((b) -> b.x = (int) x);
        y = PointManager.getRandomY();
        bounds.get(0).y = y;
        bounds.get(1).y = y + 378;
        stocked = false;
        return;
      }
      if (x < 95 && !stocked) {
        score++;
        stocked = true;
      }

    }

    public void render(Graphics g) {
      g.drawImage(image, (int) x, y, WIDTH, HEIGHT, null);
    }

    public float getX() {
      return x;
    }

    public void setX(float x) {
      this.x = x;
    }

    public void setY(int y) {
      this.y = y;
    }

    public void setPrev(Pipe prev) {
      this.prev = prev;
    }

    public boolean checkCollision(List<Rectangle> player) {
      boolean up = player.stream().anyMatch((b) -> b.intersects(bounds.get(0)));
      boolean down = player.stream().anyMatch((b) -> b.intersects(bounds.get(1)));
      return up || down;
    }

    private void updateBounds() {
      bounds = new ArrayList<>() {{
        add(new Rectangle((int) x, y, WIDTH, 240));
        add(new Rectangle((int) x, y + 378, WIDTH, 240));
      }};
    }
  }
}
