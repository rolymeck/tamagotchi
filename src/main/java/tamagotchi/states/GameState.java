package tamagotchi.states;

import tamagotchi.entities.Food;
import tamagotchi.game.Game;
import tamagotchi.game.Stat;
import tamagotchi.game.World;
import tamagotchi.gfx.Assets;
import tamagotchi.gfx.Text;
import tamagotchi.pet.Pet;
import tamagotchi.ui.UIBar;
import tamagotchi.ui.UIImageButton;
import tamagotchi.ui.UIObject;
import tamagotchi.ui.UIStaticScreen;
import tamagotchi.utils.PointManager;

import java.awt.*;

public class GameState extends State {

  public GameState() {
    initUI();
  }

  @Override
  public void tick() {
    uiManager.tick();
    World world = Game.getCurrentGame().getWorld();
    world.tick();
  }

  @Override
  public void render(Graphics g) {
    uiManager.render(g);
    World world = Game.getCurrentGame().getWorld();
    Text.drawString(g, String.valueOf((int) world.getPet().getValue(Stat.AGE)), 115,
        40, Color.BLACK, Assets.font34);
    world.render(g);
  }

  private void initUI() {

    UIObject mainScreen = new UIStaticScreen(0, 0, 480, 480,
        Assets.mainScreen);

    UIObject btn_feed = new UIImageButton(48, 410, 96, 48,
        Assets.btn_feed,
        () -> {
          World world = Game.getCurrentGame().getWorld();
          if (world.haveFood() || world.getPet().getStage() == Pet.Stage.DEAD) {
            return;
          }
          Food food = world.getPet().getFood();
          int point = PointManager.getRandomX();
          food.setX(point);
          world.setFood(food);
        });

    UIObject btn_clean = new UIImageButton(192, 410, 96, 48,
        Assets.btn_clean,
        () -> {
          World world = Game.getCurrentGame().getWorld();
          if (world.getPet().getStage() == Pet.Stage.DEAD) {
            return;
          }
          world.cleanUp();
          world.getPet().cleanUp();
        });

    UIObject btn_play = new UIImageButton(336, 410, 96, 48,
        Assets.btn_play,
        () -> {
          Game.getCurrentGame().newPlayState();
          State.setState(Game.getCurrentGame().getState(EState.PLAY));
        });

    UIObject hapBar = new UIBar(115, 53, 110, 22, Assets.happinessBar, Stat.HAPPINESS);
    UIObject hunBar = new UIBar(335, 18, 110, 22, Assets.hungerBar, Stat.HUNGER);
    UIObject wasBar = new UIBar(335, 53, 110, 22, Assets.wasteBar, Stat.WASTE);

    uiManager.addObject(mainScreen);
    uiManager.addObject(hapBar);
    uiManager.addObject(hunBar);
    uiManager.addObject(wasBar);
    uiManager.addObject(btn_feed);
    uiManager.addObject(btn_clean);
    uiManager.addObject(btn_play);
  }
}
