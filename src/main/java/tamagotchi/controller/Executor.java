package tamagotchi.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tamagotchi.controller.massage.Message;
import tamagotchi.model.world.World;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Executor implements Runnable {

  private static final Logger log = LogManager.getLogger(Executor.class);

  private final LinkedBlockingQueue<Message> queue;
  private final ExecutorService service;
  private final World world;

  public Executor(World world) {
    this.world = world;
    this.queue = new LinkedBlockingQueue<>(3);
    this.service = Executors.newSingleThreadExecutor();
  }

  @Override
  public void run() {
    while (true) {
      Message message = queue.poll();
      if (message == null) {
        service.submit(world::update);
        try {
          Thread.sleep(1_000);
        } catch (InterruptedException ignore) {
          log.debug("Executor is interrupted");
          return;
        }
      } else {
        switch (message) {
          case FEED:
            service.submit(world::feed);
            continue;
          case CLEANUP:
            service.submit(world::cleanUp);
        }
      }
    }
  }

  public void submit(Message message) {
    this.queue.offer(message);
  }
}
