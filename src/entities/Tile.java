package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.Game;
import framework.ObjectManager;
import ui.ImageLoader;

public class Tile extends GameObject {

	public int hits;

	public int getHits() {
		return hits;
	}

	public Tile(int x, int y, int w, int h, int destroy) {
		super( x, y,w,h);
		// TODO Auto-generated constructor stub
		this.hits = destroy;
	}

	public void render(Graphics2D g) {
		BufferedImage b = null;
		if (hits == 2)
			g.drawImage(ImageLoader.getInstance().getSprite("br2"), x, y, width, height, null);
		if (hits < 0)
			g.drawImage(ImageLoader.getInstance().getSprite("br0"), x, y, width, height, null);
		if (hits == 1)
			g.drawImage(ImageLoader.getInstance().getSprite("br1"), x, y, width, height,null);

		

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void tick(ObjectManager manager) {

		if (hits == 0) {
			manager.remove(this);
		}
	}

	public void hasBeenHit() {
		if (hits < 0)
			return;
		Game.tileHit();
		hits--;
	}

}
