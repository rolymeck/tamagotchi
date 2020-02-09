package tamagotchi.gfx;

import java.awt.image.BufferedImage;

public class AnimationStatic extends Animation {

  private BufferedImage image;

  public AnimationStatic(int speed, BufferedImage image) {
    super(speed, null);
    this.image = image;
  }

  @Override
  public void tick() {
    // TODO Stub
  }

  @Override
  public BufferedImage getCurrentFrame() {
    return image;
  }
}
