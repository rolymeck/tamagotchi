package tamagotchi.states;

import tamagotchi.gfx.Assets;
import tamagotchi.gfx.Text;
import tamagotchi.handler.Handler;
import tamagotchi.handler.Stat;
import tamagotchi.ui.UIImageAnimatedButton;
import tamagotchi.ui.UIManager;
import tamagotchi.ui.UIObject;
import tamagotchi.ui.UIStaticScreen;
import tamagotchi.utils.Timer;

import java.awt.*;

public class DeathState extends State {

  private UIObject btn_new;
  private Timer timer;

  public DeathState(Handler handler) {
    super(handler);
    timer = new Timer(20);
    initUI();
  }

  @Override
  public void tick() {
    uiManager.tick();
    if (timer.isFinished()){
      uiManager.addObject(btn_new);
    }
    if (timer.isStarted()) {
      timer.tick();
    }
  }

  @Override
  public void render(Graphics g) {
    uiManager.render(g);
    Text.drawString(g, String.valueOf((int) handler.getWorld().getPet().getValue(Stat.AGE)), 254,
        76, Color.BLACK, Assets.font34);
    if (!timer.isFinished()){
      Text.drawString(g, String.format("You can create a new pet after %d s", timer.left()), 30,
          440, Color.BLACK, Assets.font20);
    }
    handler.getWorld().render(g);
  }

  private void initUI() {
    UIObject deathScreen = new UIStaticScreen(0, 0, 480, 480,
        Assets.deathScreen);

    uiManager.addObject(deathScreen);

    btn_new = new UIImageAnimatedButton(192, 410, 96, 48,
        Assets.btn_new,
        () -> {
          handler.getGame().gameState = new GameState(handler);
          State.setState(handler.getGame().selectionState, handler);
          reset();
        });

  }

  public Timer getTimer() {
    return timer;
  }

  private void reset() {
    timer = new Timer(20);
    uiManager = new UIManager(handler);
    initUI();
  }
}
