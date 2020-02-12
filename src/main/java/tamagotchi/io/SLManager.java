package tamagotchi.io;

import tamagotchi.game.World;

import java.io.*;

public class SLManager {

  public static void saveWorld(World world) {
    try (ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream("world.tmg"))) {
      OOS.writeObject(world);
    } catch (IOException ignore) {
    }
  }

  public static World loadWorld() {
    try (ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("world.tmg"))) {
      return (World) OIS.readObject();
    } catch (IOException | ClassNotFoundException ignore) {
    }

    return null;
  }
}
