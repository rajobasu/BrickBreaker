package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.Game;
import framework.Movable;
import framework.ObjectManager;
import ui.ImageLoader;

public class Paddle extends GameObject implements Movable{
	
	private int deltaX=0;
	
	public Paddle(int x, int y, int w, int h) {
		super( x, y,w,h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.drawImage(ImageLoader.getInstance().getSprite("paddle"), x, y, width, height,null);
	
	}

	@Override
	public void tick(ObjectManager manager) {
		if(x<5)x=5;
		if(x>Game.WIDTH-(width+5))x=Game.WIDTH-(width+5);
		x+=deltaX;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}
	

}
