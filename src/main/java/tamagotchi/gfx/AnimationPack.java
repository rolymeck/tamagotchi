package tamagotchi.gfx;

import tamagotchi.model.pet.Pet;

public class AnimationPack {

  private Size small;
  private Size medium;
  private Size large;

  public AnimationPack(Size small, Size medium, Size large) {
    this.small = small;
    this.medium = medium;
    this.large = large;
  }

  public Size small() {
    return this.small;
  }

  public Size medium() {
    return this.medium;
  }

  public Size large() {
    return this.large;
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

  public void render() {

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
    private Animation front;
    private Animation left;
    private Animation right;

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
