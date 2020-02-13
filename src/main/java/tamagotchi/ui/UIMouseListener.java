package tamagotchi.ui;

import tamagotchi.game.Game;

import java.awt.*;

public class UIMouseListener extends UIObject {

  private final ClickListener clicker;

  public UIMouseListener(ClickListener clicker) {
    super(0, 0, Game.WIDTH, Game.HEIGHT);
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
