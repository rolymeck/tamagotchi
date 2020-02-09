package tamagotchi;

import tamagotchi.handler.Game;

public class Launcher {

  public static void main(String[] args) {
    Game game = new Game("TAMAGOTCHI", 480, 480);
    game.start();
  }

}
