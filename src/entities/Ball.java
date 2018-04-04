package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import framework.Game;
import framework.ObjectManager;
import ui.ImageLoader;

public class Ball extends GameObject {
	/**
	 * 1 :: up 2 :: down 3 :: left 4 :: right
	 */
	
	private int deltaX;
	private int deltaY;

	public Ball(int x, int y, int h, int w) {
		super( x, y,w,h);
		// TODO Auto-generated constructor stub
	
		deltaX = 3;
		deltaY = -3;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.GREEN);
		//g.fillOval(x, y, width, height);
		g.drawImage(ImageLoader.getInstance().getSprite("ball"), x, y,width, height,null);
		//g.draw(getBounds());
//	    g.draw(getBoundsBottom());
//	    g.draw(getBoundsLeft());
//	    g.draw(getBoundsRight());
//	    g.draw(getBoundsTop());
//	    
	
	
	
	
	
	}

	@Override
	public void tick(ObjectManager manager) {
		
		
		
		for (GameObject ob : manager.getObjectList()) {
			if (ob instanceof Ball) {
				continue;
			}
			if (ob.getBounds().intersects(getBoundsRight())) {

				if (getCenter().getX() < ob.getCenter().getX()) {
					
					
					if (ob instanceof Tile) {
						deltaX = -deltaX;
						x=ob.getX()-width-1;
						((Tile) ob).hasBeenHit();
					}else if(ob instanceof Paddle){
						y=ob.getY()-height-1;
												
						x=ob.getX()-width-1;
						deltaY = -(new Random().nextInt(3)+2);
						deltaX=-(new Random().nextInt(3)+2);
					}
				
				}

			}
			else if (ob.getBounds().intersects(getBoundsLeft())) {
				if (getCenter().getX() > ob.getCenter().getX()) {
					
					if (ob instanceof Tile) {
						x=ob.getX()+ob.getWidth()+1;

						deltaX = -deltaX;
						((Tile) ob).hasBeenHit();
					}else if(ob instanceof Paddle){
						x=ob.getX()+ob.getWidth()+1;
						y=ob.getY()-height-1;
												
						deltaX=-(new Random().nextInt(3)+2);
						deltaY = -(new Random().nextInt(3)+2);
						
					}
				}
			}
			else if (ob.getBounds().intersects(getBoundsTop())) {
				if (getCenter().getY() > ob.getCenter().getY()) {
					y=ob.getY()+ob.getHeight()+1;
					deltaY = -deltaY;
					if (ob instanceof Tile) {
						((Tile) ob).hasBeenHit();
					}
				}
			}
			else if (ob.getBounds().intersects(getBoundsBottom())) {
				if (getCenter().getY() < ob.getCenter().getY()) {
					y=ob.getY()-height-1;
					if (ob instanceof Tile) {
						deltaY = -deltaY;
						
						((Tile) ob).hasBeenHit();
					}if(ob instanceof Paddle){
						deltaY = -(new Random().nextInt(3)+2);
						
					}
				}
			}

		}
		
		x += deltaX;
		y += deltaY;
		if(y>Game.HEIGHT-height){
			Game.decreaseLife();
		}
	}

	public Rectangle getBoundsBottom() {

		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + (height / 2)),
				(int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsTop() {

		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsRight() {

		return new Rectangle((int) ((int) x + width - 5), (int) y +5, (int) 5, (int) height-10 );
	}

	public Rectangle getBoundsLeft() {

		return new Rectangle((int) x, (int) y+5 , (int) 5, (int) height-10);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, width, height);
	}

}
