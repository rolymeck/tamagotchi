package tamagotchi.states;

import tamagotchi.Config;
import tamagotchi.game.Game;
import tamagotchi.game.Stat;
import tamagotchi.game.World;
import tamagotchi.gfx.Assets;
import tamagotchi.gfx.Text;
import tamagotchi.ui.UIImageButton;
import tamagotchi.ui.UIManager;
import tamagotchi.ui.UIObject;
import tamagotchi.ui.UIStaticScreen;
import tamagotchi.utils.Timer;

import java.awt.*;

public class DeathState extends State {

  private final Timer timer;
  private UIObject btn_new;

  public DeathState() {
    timer = new Timer(Config.NEW_GAME_WAIT_SEC);
    initUI();
  }

  @Override
  public void tick() {
    uiManager.tick();
    if (timer.isFinished()) {
      uiManager.addObject(btn_new);
    }
    if (timer.isStarted()) {
      timer.tick();
    }
  }

  @Override
  public void render(Graphics g) {
    uiManager.render(g);

    World world = Game.getCurrentGame().getWorld();
    String age = String.valueOf((int) world.getPet().getValue(Stat.AGE));
    Text.drawString(g, age, 254, 76, Color.BLACK, Assets.font34);
    if (!timer.isFinished()) {
      Text.drawString(g, String.format("You can create a new pet in %d s", timer.left()), 30,
          440, Color.BLACK, Assets.font20);
    }
    world.render(g);
  }

  public Timer getTimer() {
    return timer;
  }

  private void initUI() {
    UIObject deathScreen = new UIStaticScreen(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT,
        Assets.deathScreen);

    uiManager.addObject(deathScreen);

    btn_new = new UIImageButton(192, 410, 96, 48,
        Assets.btn_new,
        () -> {
          State selectionState = Game.getCurrentGame().getState(EState.SELECTION);
          State.setState(selectionState);
          reset();
        });

  }

  private void reset() {
    timer.reset();
    uiManager = new UIManager();
    initUI();
  }
}
