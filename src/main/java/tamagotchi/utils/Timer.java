package tamagotchi.utils;

import tamagotchi.game.Game;

public class Timer {

  private final long duration;
  private long count;
  private boolean started;
  private boolean finished;

  private int ticks;

  public Timer(long duration) {
    this.duration = duration;
  }

  public void start() {
    if (finished) {
      return;
    }
    started = true;
  }

  public void tick() {
    if (finished) {
      return;
    }
    if (count == duration) {
      finished = true;
      return;
    }

    ticks++;

    if (ticks >= Game.FPS) {
      count++;
      ticks = 0;
    }
  }

  public long left() {
    return duration - count;
  }

  public boolean isStarted() {
    return started;
  }

  public boolean isFinished() {
    return finished;
  }

  public void reset() {
    count = 0;
    ticks = 0;
    started = false;
    finished = false;
  }
}
