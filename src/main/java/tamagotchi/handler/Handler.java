package tamagotchi.handler;

import tamagotchi.controller.MouseManager;
import tamagotchi.ui.UIManager;

public class Handler {

  private Game game;
  private World world;

  public Handler(Game game) {
    this.game = game;
  }

  public MouseManager getMouseManager() {
    return game.getMouseManager();
  }

  public void setUI(UIManager uiManager) {
    game.getMouseManager().setUIManager(uiManager);
  }

  public int getWidth() {
    return game.getWidth();
  }

  public int getHeight() {
    return game.getHeight();
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

}
