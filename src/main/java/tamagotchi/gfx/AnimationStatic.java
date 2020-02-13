package tamagotchi.gfx;

import java.awt.image.BufferedImage;

public class AnimationStatic extends Animation {

  private final BufferedImage image;

  public AnimationStatic(float frequency, BufferedImage image) {
    super(frequency, null);
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
