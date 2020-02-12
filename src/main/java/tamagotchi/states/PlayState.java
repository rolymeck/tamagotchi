package tamagotchi.states;

import tamagotchi.gfx.Assets;
import tamagotchi.play.Pipes;
import tamagotchi.play.Player;
import tamagotchi.play.Sprite;
import tamagotchi.ui.ScoreBar;
import tamagotchi.ui.UIObject;

import java.awt.*;

public class PlayState extends State {

  private Sprite bg1, bg2, bg3, bg4, bg5, grass;
  private Pipes pipes;
  private Player player;
  private boolean started, finished;
  private int score;

  public PlayState() {
    bg1 = new Sprite(Assets.bg1, 48, 405, 0f);
    bg2 = new Sprite(Assets.bg2, 48, 405, 0.5f);
    bg3 = new Sprite(Assets.bg3, 48, 405, 1f);
    bg4 = new Sprite(Assets.bg4, 48, 405, 1.5f);
    bg5 = new Sprite(Assets.bg5, 48, 405, 2f);
    grass = new Sprite(Assets.grass, 448, 32, 2f);
    pipes = new Pipes();
    initUI();
  }

  @Override
  public void tick() {
    bg1.tick();
    bg2.tick();
    bg3.tick();
    bg4.tick();
    bg5.tick();
    uiManager.tick();
    grass.tick();
    pipes.tick();
  }

  @Override
  public void render(Graphics g) {
    bg1.render(g);
    bg2.render(g);
    bg3.render(g);
    bg4.render(g);
    bg5.render(g);
    pipes.render(g);
    uiManager.render(g);
    grass.render(g);

  }

  private void initUI() {
    UIObject scoreBar = new ScoreBar(this);
    uiManager.addObject(scoreBar);
  }

  public int getScore() {
    return score;
  }
}
