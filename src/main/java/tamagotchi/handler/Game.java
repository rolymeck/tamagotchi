package tamagotchi.handler;

import tamagotchi.controller.MouseManager;
import tamagotchi.display.Display;
import tamagotchi.gfx.Assets;
import tamagotchi.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

public class Game implements Runnable {

  public static final int FPS = 60;

  public String title;

  private Map<EState, State> states;

  private Display display;
  private int width, height;
  private boolean running = false;
  private Thread thread;
  private BufferStrategy bufferStrategy;
  private Graphics g;

  private MouseManager mouseManager;

  private Handler handler;

  public Game(String title, int width, int height) {
    this.width = width;
    this.height = height;
    this.title = title;
  }

  private void init() {
    display = new Display(title, width, height);
    mouseManager = new MouseManager();
    display.getFrame().addMouseListener(mouseManager);
    display.getFrame().addMouseMotionListener(mouseManager);
    display.getCanvas().addMouseListener(mouseManager);
    display.getCanvas().addMouseMotionListener(mouseManager);

    Assets.init();

    handler = new Handler(this);

    states = new HashMap<>();
    states.put(EState.GAME, new GameState(handler));
    states.put(EState.SELECTION, new SelectionState(handler));
    states.put(EState.DEATH, new DeathState(handler));

    /*World world = SLManager.loadWorld();
    if (world == null) {*/
      State.setState(states.get(EState.SELECTION), handler);
   /* } else {
      world.setHandler(handler);
      world.setEntityManager(new EntityManager(handler));
      world.getPet().setHandler(handler);
      GameState gs = (GameState) states.get(EState.GAME);
      gs.setWorld(world);
      switch (world.getPet().getStage()) {
        case DEAD:
          State.setState(states.get(EState.DEATH), handler);
          break;
        default:
          State.setState(states.get(EState.GAME), handler);
      }
    }*/
  }

  private void tick() {
    if (State.getState() != null)
      State.getState().tick();
  }

  private void render() {
    bufferStrategy = display.getCanvas().getBufferStrategy();
    if (bufferStrategy == null) {
      display.getCanvas().createBufferStrategy(3);
      return;
    }
    g = bufferStrategy.getDrawGraphics();
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
        if (handler.getWorld().getPet() != null) {
          //System.out.println("AGE: " + handler.getWorld().getPet().getValue(Stat.AGE) + " HPN: " + handler.getWorld().getPet().getValue(Stat.HAPPINESS) + " HNG: " + handler.getWorld().getPet().getValue(Stat.HUNGER) + " WST: " + handler.getWorld().getPet().getValue(Stat.WASTE));
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

  public Map<EState, State> getStates() {
    return states;
  }
}



