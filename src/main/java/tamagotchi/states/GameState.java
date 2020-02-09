package tamagotchi.states;

import tamagotchi.handler.Handler;

// abs - tmp
public abstract class GameState extends State {

	//tmp
	public GameState(Handler handler) {
		super(handler);
	}
/*
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}*/

}
