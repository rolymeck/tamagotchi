package tamagotchi.states;

import tamagotchi.gfx.Assets;
import tamagotchi.handler.Handler;
import tamagotchi.ui.UIImageButton;
import tamagotchi.ui.UIObject;
import tamagotchi.ui.UIStaticScreen;

import java.awt.*;

public class SelectionState extends State {


  public SelectionState(Handler handler) {
    super(handler);

    UIObject screen = new UIStaticScreen(0, 0, 480, 480,
        Assets.selectionScreen);

    UIObject green = new UIImageButton(32, 128, 128, 192,
        Assets.greenPetSelectionTile,
        () -> {
          handler.setUI(handler.getGame().gameState.uiManager);
          //State.setState(handler.getGame().gameState);
        });

    UIObject red = new UIImageButton(176, 128, 128, 192,
        Assets.redPetSelectionTile,
        () -> {
          //handler.getMouseManager().setUIManager(null);
          //State.setState(handler.getGame().gameState);
        });

    UIObject blue = new UIImageButton(320, 128, 128, 192,
        Assets.bluePetSelectionTile,
        () -> {
          //handler.getMouseManager().setUIManager(null);
          //State.setState(handler.getGame().gameState);
        });

    uiManager.addObject(screen);
    uiManager.addObject(green);
    uiManager.addObject(red);
    uiManager.addObject(blue);

  }

  @Override
  public void tick() {
    uiManager.tick();
    // Temporarily just go directly to the GameState, skip the menu state!
    //handler.getMouseManager().setUIManager(null);
    //State.setState(handler.getGame().gameState);
  }

  @Override
  public void render(Graphics g) {
    uiManager.render(g);
  }

}
