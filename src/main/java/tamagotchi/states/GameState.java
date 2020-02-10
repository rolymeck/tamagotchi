package tamagotchi.states;

import tamagotchi.gfx.Assets;
import tamagotchi.gfx.Text;
import tamagotchi.handler.Handler;
import tamagotchi.handler.Stat;
import tamagotchi.handler.World;
import tamagotchi.model.entities.Food;
import tamagotchi.ui.UIBar;
import tamagotchi.ui.UIImageAnimatedButton;
import tamagotchi.ui.UIObject;
import tamagotchi.ui.UIStaticScreen;
import tamagotchi.utils.PointManager;

import java.awt.*;

public class GameState extends State {

  private World world;

  public GameState(Handler handler) {
    super(handler);
    setWorld(new World(handler, 1));

    UIObject mainScreen = new UIStaticScreen(0, 0, 480, 480,
        Assets.mainScreen);

    UIObject btn_feed = new UIImageAnimatedButton(48, 410, 96, 48,
        Assets.btn_feed,
        () -> {
          if (world.haveFood() || !world.getPet().isAlive()) {
            return;
          }
          Food food = world.getPet().getFood();
          int point = PointManager.getRandomX(handler);
          food.setX(point);
          world.setFood(food);
        });

    UIObject btn_clean = new UIImageAnimatedButton(192, 410, 96, 48,
        Assets.btn_clean,
        () -> {
          if (!world.getPet().isAlive()) {
            return;
          }
          world.cleanUp();
          world.getPet().cleanUp();
        });

    UIObject btn_play = new UIImageAnimatedButton(336, 410, 96, 48,
        Assets.btn_play,
        () -> {

        });

    UIObject hapBar = new UIBar(115, 53, 110, 22, Assets.happinessBar, world, Stat.HAPPINESS);
    UIObject hunBar = new UIBar(335, 18, 110, 22, Assets.hungerBar, world, Stat.HUNGER);
    UIObject wasBar = new UIBar(335, 53, 110, 22, Assets.wasteBar, world, Stat.WASTE);

    uiManager.addObject(mainScreen);
    uiManager.addObject(hapBar);
    uiManager.addObject(hunBar);
    uiManager.addObject(wasBar);
    uiManager.addObject(btn_feed);
    uiManager.addObject(btn_clean);
    uiManager.addObject(btn_play);
  }

  @Override
  public void tick() {
    uiManager.tick();
    world.tick();
  }

  @Override
  public void render(Graphics g) {
    uiManager.render(g);
    Text.drawString(g, String.valueOf((int) world.getPet().getValue(Stat.AGE)), 115,
        40, Color.BLACK, Assets.font26);
    world.render(g);
  }

  private void setWorld(World world) {
    this.world = world;
    handler.setWorld(world);
  }
}
