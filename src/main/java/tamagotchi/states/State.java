package tamagotchi.states;

import tamagotchi.ui.UIManager;

import java.awt.*;


public abstract class State {

  private static State currentState = null;
  protected UIManager uiManager;

  public static State getState() {
    return currentState;
  }

  public static void setState(State state) {
    currentState = state;
  }

  public State() {
    uiManager = new UIManager();
  }

  public abstract void tick();

  public abstract void render(Graphics g);

  public UIManager getUiManager() {
    return uiManager;
  }

}
