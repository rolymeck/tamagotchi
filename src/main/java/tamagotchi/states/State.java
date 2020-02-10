package tamagotchi.states;

import tamagotchi.handler.Handler;
import tamagotchi.ui.UIManager;

import java.awt.*;


public abstract class State {

  private static State currentState = null;
  protected Handler handler;
  protected UIManager uiManager;

  //CLASS

  public State(Handler handler) {
    this.handler = handler;
    uiManager = new UIManager(handler);
  }

  public static void setState(State state, Handler handler) {
    currentState = state;
    handler.setUI(state.uiManager);
  }

  public static State getState() {
    return currentState;
  }

  public abstract void tick();

  public abstract void render(Graphics g);

  public UIManager getUiManager() {
    return uiManager;
  }

}
