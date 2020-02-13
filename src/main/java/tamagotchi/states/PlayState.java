package tamagotchi.states;

import tamagotchi.game.Game;
import tamagotchi.game.World;
import tamagotchi.gfx.Assets;
import tamagotchi.play.Pipes;
import tamagotchi.play.Player;
import tamagotchi.play.Sprite;
import tamagotchi.ui.UIImageButton;
import tamagotchi.ui.UIMouseListener;
import tamagotchi.ui.UIObject;
import tamagotchi.ui.UIScoreBar;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {

  private List<Sprite> background;
  private Sprite grass;
  private Pipes pipes;
  private Player player;
  private boolean started, finished;
  private UIObject btn_new, btn_return, mouseListener;

  public PlayState() {
    background = new ArrayList<>() {{
      add(new Sprite(Assets.bg1, 48, 405, 0f));
      add(new Sprite(Assets.bg2, 48, 405, 0.5f));
      add(new Sprite(Assets.bg3, 48, 405, 1f));
      add(new Sprite(Assets.bg4, 48, 405, 1.5f));
      add(new Sprite(Assets.bg5, 48, 405, 2f));
    }};
    grass = new Sprite(Assets.grass, 448, 32, 2f);
    pipes = new Pipes();
    player = new Player();
    initUI();
  }

  @Override
  public void tick() {
    World world = Game.getCurrentGame().getWorld();
    world.tick();
    if (started && !finished) {
      background.forEach(Sprite::tick);
      uiManager.tick();
      grass.tick();
      pipes.tick();
      player.tick();
      if (checkCollision() || player.getY() <= 0 || player.getY() >= 412) {
        finished = true;
        initUI();
        uiManager.removeObject(mouseListener);
        uiManager.addObject(btn_new);
        uiManager.addObject(btn_return);

      }
    }
  }

  @Override
  public void render(Graphics g) {
    background.forEach(bg -> bg.render(g));
    pipes.render(g);
    grass.render(g);
    player.render(g);
    uiManager.render(g);
  }

  private void initUI() {
    UIObject scoreBar = new UIScoreBar(this);

    mouseListener = new UIMouseListener(() -> {
      if (!started) {
        started = true;
      } else {
        player.setClicked(true);
      }
    });

    btn_new = new UIImageButton(134, 216, 96, 48,
        Assets.btn_new,
        this::reset);

    btn_return = new UIImageButton(250, 216, 96, 48,
        Assets.btn_return,
        () -> State.setState(Game.getCurrentGame().getState(EState.GAME)));

    uiManager.addObject(scoreBar);
    uiManager.addObject(mouseListener);
  }

  private void reset() {
    background.forEach(Sprite::reset);
    grass.reset();
    pipes.reset();
    player.reset();
    started = false;
    finished = false;
    uiManager.removeObject(btn_new);
    uiManager.removeObject(btn_return);
    uiManager.addObject(mouseListener);
  }

  private boolean checkCollision() {
    return pipes.checkCollision(player.getBounds());
  }

  public Pipes getPipes() {
    return pipes;
  }
}
