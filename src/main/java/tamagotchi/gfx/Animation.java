package tamagotchi.gfx;

import java.awt.image.BufferedImage;

public class Animation {

  private int index;
  private float frequency;
  private long lastTime, timer;
  private BufferedImage[] frames;

  public Animation(float frequency, BufferedImage[] frames) {
    this.frequency = frequency;
    this.frames = frames;
    index = 0;
    timer = 0;
    lastTime = System.currentTimeMillis();
  }

  public void tick() {
    timer += (System.currentTimeMillis() - lastTime);
    lastTime = System.currentTimeMillis();
    float floatTimer = ((float) timer) / 1_000;

    if (floatTimer > frequency) {
      index++;
      timer = 0;
      if (index >= frames.length)
        index = 0;
    }
  }

  public BufferedImage getCurrentFrame() {
    return frames[index];
  }

}
