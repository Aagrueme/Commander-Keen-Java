package commanderKeen.util;

import java.awt.image.BufferedImage;

public class Animation {
	private final Spritesheet sprite;
	private int states;
	private int frames;
	private int currentState;
	private int currentFrame;
	private long lastTimeMillis;
	private long delay;
	private boolean running;

	public Animation(Spritesheet sprite, int states, int frames, long delay){
		this.sprite = sprite;
		this.states = states;
		this.frames = frames;
		this.delay = delay;
		this.running = false;
	}

	public void startAnimation(){
		this.running = true;
		this.currentFrame = 0;
		this.currentState = 0;
		this.lastTimeMillis = System.currentTimeMillis();
	}

	public void update(){
		if(running){
			if(lastTimeMillis + delay <= System.currentTimeMillis()){
				if(currentFrame != frames){
					currentFrame ++;
				}else{
					currentFrame = 0;
				}
				lastTimeMillis = System.currentTimeMillis();
			}
		}
	}

	public BufferedImage getImage(){
		return sprite.getImage(currentState, currentFrame);
	}

	public void setState (int state){
		if(!(state > states)) {
			this.currentState = state;
		}
	}
}