package tamagotchi.ui;

import tamagotchi.Config;

import java.awt.*;

public class UIMouseListener extends UIObject {

  private final ClickListener clicker;

  public UIMouseListener(ClickListener clicker) {
    super(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_WIDTH);
    this.clicker = clicker;
  }

  @Override
  public void tick() {

  }

  @Override
  public void render(Graphics g) {

  }

  @Override
  public void onClick() {
    clicker.onClick();
  }
}
