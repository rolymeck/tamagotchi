package tamagotchi.ui;

import tamagotchi.gfx.Assets;
import tamagotchi.gfx.Text;
import tamagotchi.states.PlayState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScoreBar extends UIObject {

  private final PlayState playState;
  private final BufferedImage image;

  public ScoreBar(PlayState playState) {
    super(0, 0, 480, 48);
    this.playState = playState;
    this.image = Assets.scoreBar;
  }

  @Override
  public void tick() {
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(image, (int) x, (int) y, width, height, null);
    Text.drawString(g, String.valueOf(playState.getScore()), 155,
        35, Color.BLACK, Assets.font34);
  }

  @Override
  public void onClick() {
  }
}
