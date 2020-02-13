package tamagotchi.play;

import tamagotchi.Config;
import tamagotchi.gfx.Assets;
import tamagotchi.utils.PointManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Pipes {

  private final List<Pipe> pipes;
  private int score;

  public Pipes() {
    Pipe first = new Pipe(Config.DISPLAY_WIDTH, PointManager.getRandomY());
    Pipe second = new Pipe(Config.DISPLAY_WIDTH + Config.PIPES_HOR_INTERVAL, PointManager.getRandomY());
    Pipe third = new Pipe(Config.DISPLAY_WIDTH + Config.PIPES_HOR_INTERVAL * 2, PointManager.getRandomY());
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
    pipes.get(0).setX(Config.DISPLAY_WIDTH);
    pipes.get(1).setX(Config.DISPLAY_WIDTH + Config.PIPES_HOR_INTERVAL);
    pipes.get(2).setX(Config.DISPLAY_WIDTH + Config.PIPES_HOR_INTERVAL * 2);

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
        add(new Rectangle(x, y, Config.PIPES_WIDTH, Config.ONE_PIPE_HEIGHT));
        add(new Rectangle(x, y + Config.PIPES_VER_INTERVAL, Config.PIPES_WIDTH, Config.ONE_PIPE_HEIGHT));
      }};

    }

    public void tick() {
      x -= Config.PIPES_X_OFFSET;
      bounds.forEach((b) -> b.x = (int) x);
      if (x + Config.PIPES_WIDTH <= 0) {
        x = prev.getX() + Config.PIPES_HOR_INTERVAL;
        //bounds.forEach((b) -> b.x = (int) x);
        y = PointManager.getRandomY();
        //bounds.get(0).y = y;
        //bounds.get(1).y = y + Config.PIPES_VER_INTERVAL;
        updateBounds();
        stocked = false;
        return;
      }
      if (x < Config.SCORE_PLUS_X && !stocked) {
        score++;
        stocked = true;
      }

    }

    public void render(Graphics g) {
      g.drawImage(image, (int) x, y, Config.PIPES_WIDTH, Config.PIPES_HEIGHT, null);
    }

    public boolean checkCollision(List<Rectangle> player) {
      boolean up = player.stream().anyMatch((b) -> b.intersects(bounds.get(0)));
      boolean down = player.stream().anyMatch((b) -> b.intersects(bounds.get(1)));
      return up || down;
    }

    public void setPrev(Pipe prev) {
      this.prev = prev;
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

    private void updateBounds() {
      bounds = new ArrayList<>() {{
        add(new Rectangle((int) x, y, Config.PIPES_WIDTH, Config.ONE_PIPE_HEIGHT));
        add(new Rectangle((int) x, y + Config.PIPES_VER_INTERVAL, Config.PIPES_WIDTH, Config.ONE_PIPE_HEIGHT));
      }};
    }
  }
}
