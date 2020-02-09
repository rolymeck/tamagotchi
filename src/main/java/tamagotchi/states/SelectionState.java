package tamagotchi.states;

import tamagotchi.gfx.Assets;
import tamagotchi.handler.Handler;
import tamagotchi.model.pet.BluePet;
import tamagotchi.model.pet.GreenPet;
import tamagotchi.model.pet.RedPet;
import tamagotchi.ui.UIImageButton;
import tamagotchi.ui.UIObject;
import tamagotchi.ui.UIStaticScreen;

import java.awt.*;

public class SelectionState extends State {


  public SelectionState(Handler handler) {
    super(handler);

    UIObject selectionScreen = new UIStaticScreen(0, 0, 480, 480,
        Assets.selectionScreen);

    UIObject green = new UIImageButton(32, 128, 128, 192,
        Assets.greenPetSelectionTile,
        () -> {
          handler.getWorld().setPet(new GreenPet(handler));
          State.setState(handler.getGame().gameState, handler);
        });

    UIObject red = new UIImageButton(176, 128, 128, 192,
        Assets.redPetSelectionTile,
        () -> {
          handler.getWorld().setPet(new RedPet(handler));
          State.setState(handler.getGame().gameState, handler);
        });

    UIObject blue = new UIImageButton(320, 128, 128, 192,
        Assets.bluePetSelectionTile,
        () -> {
          handler.getWorld().setPet(new BluePet(handler));
          State.setState(handler.getGame().gameState, handler);
        });

    uiManager.addObject(selectionScreen);
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
