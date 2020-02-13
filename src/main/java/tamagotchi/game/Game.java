package tamagotchi.game;

import tamagotchi.controller.MouseManager;
import tamagotchi.display.Display;
import tamagotchi.gfx.Assets;
import tamagotchi.utils.SLManager;
import tamagotchi.pet.Pet;
import tamagotchi.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

public class Game implements Runnable {

  public static final int WIDTH = 480;
  public static final int HEIGHT = 480;
  public static final String TITLE = "TAMAGOTCHI";
  public static final int FPS = 60;
  private static Game currentGame;
  public static Game getCurrentGame() {
    return currentGame;
  }

  public String title;

  private Map<EState, State> states;

  private Display display;
  private int width, height;
  private boolean running = false;
  private Thread thread;

  private MouseManager mouseManager;

  private World world;

  public Game() {
    this.width = WIDTH;
    this.height = HEIGHT;
    this.title = TITLE;
  }

  private void init() {
    currentGame = this;
    display = new Display(title, width, height);

    Assets.init();

    states = new HashMap<>();
    states.put(EState.GAME, new GameState());
    states.put(EState.SELECTION, new SelectionState());
    states.put(EState.DEATH, new DeathState());
    states.put(EState.PLAY, new PlayState());
    State.setState(getState(EState.SELECTION));

    mouseManager = new MouseManager();
    display.getFrame().addMouseListener(mouseManager);
    display.getFrame().addMouseMotionListener(mouseManager);
    display.getCanvas().addMouseListener(mouseManager);
    display.getCanvas().addMouseMotionListener(mouseManager);

    World world = SLManager.loadWorld();
    if (world == null) {
      State.setState(getState(EState.SELECTION));
    } else {
      long timePassed = (int) ((System.currentTimeMillis() - world.getSaveTime()) / 1000);
      float ticksPerSecond = ((float) FPS / World.PERIOD);
      int amount = (int) (timePassed * ticksPerSecond);
      for (int i = 0; i < amount; i++) {
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
    g.clearRect(0, 0, width, height);
    //Draw Here!

    if (State.getState() != null)
      State.getState().render(g);

    //End Drawing!
    bufferStrategy.show();
    g.dispose();
  }

  public void run() {
    init();

    //noinspection IntegerDivisionInFloatingPointContext
    double timePerTick = 1_000_000_000 / FPS;
    double delta = 0;
    long now;
    long lastTime = System.nanoTime();
    long timer = 0;
    int ticks = 0;
    int hz = 0;

    while (running) {
      hz++;
      now = System.nanoTime();
      delta += (now - lastTime) / timePerTick;
      timer += now - lastTime;
      lastTime = now;

      if (delta >= 1) {
        tick();
        render();
        ticks++;
        delta--;
      }

      if (timer >= 1_000_000_000) {
        double MHz = Math.rint(100.0 * hz / 1_000_000f) / 100.0;
        System.out.println("FPS: " + ticks + " MHz: " + MHz);
        ticks = 0;
        timer = 0;
        hz = 0;
      }
    }
    stop();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
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
}



