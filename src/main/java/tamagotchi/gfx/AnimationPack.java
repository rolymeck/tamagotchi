package tamagotchi.gfx;

import tamagotchi.pet.Pet;

public class AnimationPack {

  private final Size small;
  private final Size medium;
  private final Size large;

  public AnimationPack(Size small, Size medium, Size large) {
    this.small = small;
    this.medium = medium;
    this.large = large;
  }

  public void tick(Pet.Stage stage) {
    switch (stage) {
      case SMALL:
        small.tick();
        break;
      case MEDIUM:
        medium.tick();
        break;
      case LARGE:
        large.tick();
    }
  }

  public Size getPack(Pet.Stage stage) {
    switch (stage) {
      case SMALL:
        return small;
      case MEDIUM:
        return medium;
      case LARGE:
        return large;
    }
    return null;
  }

  public static class Size {
    private final Animation front;
    private final Animation left;
    private final Animation right;

    public Size(Animation front, Animation left, Animation right) {
      this.front = front;
      this.left = left;
      this.right = right;
    }

    public Animation front() {
      return this.front;
    }

    public Animation left() {
      return this.left;
    }

    public Animation right() {
      return this.right;
    }

    public void tick() {
      front.tick();
      left.tick();
      right.tick();
    }

  }
}
