package tamagotchi.gfx;

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
  }
}
