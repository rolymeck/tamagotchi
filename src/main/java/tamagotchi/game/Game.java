package tamagotchi.game;

import tamagotchi.Config;
import tamagotchi.controller.MouseManager;
import tamagotchi.display.Display;
import tamagotchi.gfx.Assets;
import tamagotchi.pet.Pet;
import tamagotchi.states.*;
import tamagotchi.utils.SLManager;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

public class Game implements Runnable {

  public static final int FPS = 60;

  private static Game currentGame;

  public static Game getCurrentGame() {
    return currentGame;
  }

  private Display display;
  private Thread thread;
  private boolean running = false;

  private Map<EState, State> states;
  private World world;

  public void run() {
    init();

    //noinspection IntegerDivisionInFloatingPointContext
    double timePerTick = 1_000_000_000 / FPS;
    double delta = 0;
    long now;
    long lastTime = System.nanoTime();

    while (running) {
      now = System.nanoTime();
      delta += (now - lastTime) / timePerTick;
      lastTime = now;

      if (delta >= 1) {
        tick();
        render();
        delta--;
      }
    }

    stop();
  }

  public synchronized void start() {
    if (running)
      return;
    running = true;
    thread = new Thread(this);
    thread.start();
  }

  public synchronized void stop() {
    if (!running)
      return;
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public State getState(EState eState) {
    return states.get(eState);
  }

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  public void newPlayState() {
    states.put(EState.PLAY, new PlayState());
  }

  private void init() {
    currentGame = this;
    display = new Display();

    Assets.init();

    states = new HashMap<>();
    states.put(EState.GAME, new GameState());
    states.put(EState.SELECTION, new SelectionState());
    states.put(EState.DEATH, new DeathState());
    states.put(EState.PLAY, new PlayState());
    State.setState(getState(EState.SELECTION));

    MouseManager mouseManager = new MouseManager();
    display.getFrame().addMouseListener(mouseManager);
    display.getFrame().addMouseMotionListener(mouseManager);
    display.getCanvas().addMouseListener(mouseManager);
    display.getCanvas().addMouseMotionListener(mouseManager);

    World world = SLManager.loadWorld();

    if (world != null) {
      long now = System.currentTimeMillis();
      long saveTime = world.getSaveTime();
      long secPassed = ((int) (now - saveTime) / 1000);
      float ticksPerSecond = ((float) FPS / Config.WORLD_PERIOD);
      int ticksPassed = (int) (secPassed * ticksPerSecond);

      for (int i = 0; i < ticksPassed; i++) {
        world.updateStats();
      }

      setWorld(world);

      if (world.getPet().getStage() == Pet.Stage.DEAD) {
        State.setState(states.get(EState.DEATH));
      } else {
        State.setState(states.get(EState.GAME));
      }
    }
  }

  private void tick() {
    if (State.getState() != null)
      State.getState().tick();
  }

  private void render() {
    BufferStrategy bufferStrategy = display.getCanvas().getBufferStrategy();
    if (bufferStrategy == null) {
      display.getCanvas().createBufferStrategy(3);
      return;
    }
    Graphics g = bufferStrategy.getDrawGraphics();
    //Clear Screen
    g.clearRect(0, 0, Config.DISPLAY_WIDTH, Config.DISPLAY_HEIGHT);
    //Draw Here!

    if (State.getState() != null)
      State.getState().render(g);

    //End Drawing!
    bufferStrategy.show();
    g.dispose();
  }
}



