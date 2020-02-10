package tamagotchi.handler;

import tamagotchi.controller.MouseManager;
import tamagotchi.display.Display;
import tamagotchi.gfx.Assets;
import tamagotchi.states.DeathState;
import tamagotchi.states.GameState;
import tamagotchi.states.SelectionState;
import tamagotchi.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

  public static final int FPS = 60;

  public String title;
  //States
  public State gameState;
  public State selectionState;
  public State deathState;

  private Display display;
  private int width, height;
  private boolean running = false;
  private Thread thread;
  private BufferStrategy bs;
  private Graphics g;

  //Input
  private MouseManager mouseManager;

  //Camera

  //Handler
  private Handler handler;

  public Game(String title, int width, int height) {
    this.width = width;
    this.height = height;
    this.title = title;
    mouseManager = new MouseManager();
  }

  private void init() {
    display = new Display(title, width, height);
    display.getFrame().addMouseListener(mouseManager);
    display.getFrame().addMouseMotionListener(mouseManager);
    display.getCanvas().addMouseListener(mouseManager);
    display.getCanvas().addMouseMotionListener(mouseManager);
    Assets.init();

    handler = new Handler(this);

    gameState = new GameState(handler);
    selectionState = new SelectionState(handler);
    deathState = new DeathState(handler);

    State.setState(selectionState, handler);

  }

  private void tick() {
    if (State.getState() != null)
      State.getState().tick();
  }

  private void render() {
    bs = display.getCanvas().getBufferStrategy();
    if (bs == null) {
      display.getCanvas().createBufferStrategy(3);
      return;
    }
    g = bs.getDrawGraphics();
    //Clear Screen
    g.clearRect(0, 0, width, height);
    //Draw Here!

    if (State.getState() != null)
      State.getState().render(g);

    //End Drawing!
    bs.show();
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
        if (handler.getWorld().getPet() != null) {
          System.out.println("AGE: " + handler.getWorld().getPet().getValue(Stat.AGE) + " HPN: " + handler.getWorld().getPet().getValue(Stat.HAPPINESS) + " HNG: " + handler.getWorld().getPet().getValue(Stat.HUNGER) + " WST: " + handler.getWorld().getPet().getValue(Stat.WASTE));
        }
        ticks = 0;
        timer = 0;
        hz = 0;
      }
    }

    stop();

  }


  public MouseManager getMouseManager() {
    return mouseManager;
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

}



