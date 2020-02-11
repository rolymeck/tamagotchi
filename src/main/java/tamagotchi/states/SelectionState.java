package tamagotchi.states;

import tamagotchi.gfx.Assets;
import tamagotchi.handler.Game;
import tamagotchi.handler.World;
import tamagotchi.model.pet.BluePet;
import tamagotchi.model.pet.GreenPet;
import tamagotchi.model.pet.Pet;
import tamagotchi.model.pet.RedPet;
import tamagotchi.ui.UIImageAnimatedButton;
import tamagotchi.ui.UIObject;
import tamagotchi.ui.UIStaticScreen;

import java.awt.*;

public class SelectionState extends State {

  public SelectionState() {
    initUI();
  }

  @Override
  public void tick() {
    uiManager.tick();
  }

  @Override
  public void render(Graphics g) {
    uiManager.render(g);
  }

  private void initUI() {
    UIObject selectionScreen = new UIStaticScreen(0, 0, 480, 480,
        Assets.selectionScreen);

    UIObject green = new UIImageAnimatedButton(32, 128, 128, 192,
        Assets.greenPetSelectionTile,
        () -> createWorld(new GreenPet()));

    UIObject red = new UIImageAnimatedButton(176, 128, 128, 192,
        Assets.redPetSelectionTile,
        () -> createWorld(new RedPet()));

    UIObject blue = new UIImageAnimatedButton(320, 128, 128, 192,
        Assets.bluePetSelectionTile,
        () -> createWorld(new BluePet()));

    uiManager.addObject(selectionScreen);
    uiManager.addObject(green);
    uiManager.addObject(red);
    uiManager.addObject(blue);
  }

  private void createWorld(Pet pet) {
    World world = new World();
    world.setPet(pet);

    Game game = Game.getCurrentGame();
    game.setWorld(world);
    State.setState(game.getState(EState.GAME));
  }
}
