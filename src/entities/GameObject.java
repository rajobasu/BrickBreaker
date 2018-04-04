package entities;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.ObjectManager;

public abstract class GameObject {
	
	protected boolean isColliding;
	protected int height;
	protected int width;
	protected int x;
	protected int y;

	
	
	
	public GameObject(int x,int y,int width,int height) {
	    this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}

	public Point getCenter() {
		return new Point(x+width/2,y+height/2);
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract void render(Graphics2D g);
	public abstract void tick(ObjectManager manager);
	public abstract Rectangle getBounds();
}
