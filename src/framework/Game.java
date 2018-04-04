package framework;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import entities.Ball;
import entities.GameObject;
import entities.Paddle;
import entities.Tile;
import ui.ImageLoader;
import ui.Window;

public class Game extends Canvas {
	/**
	 * 
	 */
	private static Window window; 
	private static final long serialVersionUID = -5326392748901964222L;
	private InputHandler input=null;
	private ObjectManager manager;
	private Movable paddle;
	public static int WIDTH, HEIGHT;
	private int tilesHit;
	private int level = 1;
	private boolean gameEnded = false;
	private int score;
	private int life;
	private static Game game;  
	private boolean first=true;
	private long last;
	private final int PADDLE_WIDTH=50;
	private final int PADDLE_HEIGHT=15;
	private final int BALL_DIA=15;
	public static  boolean stop=false;
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		game = new Game();
		window = new Window(game);
		window.setIcon();
		game.init();
		game.start();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	private void init() {
		life=3;
		ImageLoader.getInstance();
		gameEnded=false;
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		Paddle paddle = new Paddle(225, 400, PADDLE_WIDTH, PADDLE_HEIGHT);
		manager.add(paddle);
		game.paddle = paddle;
		
		if(input==null){
		   input=new InputHandler(game.paddle);
		   this.addKeyListener(input);

		}
		createLevel(1);
		setToOriginal();
		//manager.add(new Ball(225, 390, 20, 20));
		manager.add(new Tile(-10, -10, 5 + 10, getHeight() + 20, -1));
		manager.add(new Tile(getWidth() - 5, 0 - 5, 15, getHeight() + 10, -1));
		manager.add(new Tile(-10, -10, getWidth() + 20, 15, -1));
		//manager.add(new Tile(-10, getHeight() - 5, getWidth() + 20, 15, -1));
		// manager.add(new Tile(getWidth()/2-5,getHeight()/2-5,20,20,2));
		// manager.add(new Tile(getWidth()/4-7,getHeight()/2-115,20,20,2));
		// manager.add(new Tile(getWidth()/2-55,getHeight()/3-15,20,20,2));
		// manager.add(new Tile(getWidth()/3+98,getHeight()-75,20,20,2));
		
		
		render();

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private void start() {
		this.requestFocus();
		last=System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (!gameEnded) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				
				
				updates++;
				delta--;
				if(last+1000<System.currentTimeMillis()){
					first=false;
				}
				if(first||stop)continue;
				tick();
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		window.wonMsg();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			// bs = this.getBufferStrategy();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		/////////////////////////////////////////////
		////////////////////////////////
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.drawImage(ImageLoader.getInstance().getSprite("background"), 0, 0, WIDTH, HEIGHT,null);
		/*  
		 * 
		 */
		
		manager.render(g2d);
        
		///////////////////////////////
		/////////////////////////////////////////
		g.dispose();
		bs.show();

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private void tick() {
		if(life==0){
			life=3;
			
			stop=true;
			window.lostMsg();
		    window.setLife(life);
			level=1;
		    
		    score=0;
		    manager.clearAll();
		    init();
		    System.out.println("Life is 0");
		}
		if(first){
			/*try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			first=false;*/
		}
		
		if (hasLevelEnded()) {
			life++;
			window.setLife(life);
			window.setLevel(++level);
			createLevel(level);
			setToOriginal();
		} else {
			manager.tick();
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	Game() {
		manager = new ObjectManager();
	}

	public static Movable getPaddle() {
		return game.paddle;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private void createLevel(int level) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(Game.class.getResource("/level" + Integer.toString(level) + ".png"));

			int w = image.getWidth();
			int h = image.getHeight();

			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					int pixel = image.getRGB(x, y);

					int red = (pixel >> 16) & 0xff;
					int green = (pixel >> 8) & 0xff;
					int blue = (pixel) & 0xff;

					if (red == 255 && green == 0 && blue == 0) {
						manager.add(new Tile(x * 25, y * 15, 25, 15, 2));
					}

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e2) {
			gameEnded = true;
		}
	}

	public static synchronized void tileHit() {
		game.tilesHit++;
		game.score += 100;
		window.setHitCount(game.score);
	}

	public boolean hasLevelEnded() {
		for (GameObject ob : manager.getObjectList()) {
			if (ob instanceof Tile) {
				if (((Tile) ob).getHits() > 0) {
					return false;
				}
			}
		}
		return true;
	}
	public static void decreaseLife(){
		game.life--;
		game.setToOriginal();
		window.setLife(game.life);
		
	}
	public static void increaseLife(){
		game.life++;
		window.setLife(game.life);

	}
	public void setToOriginal(){
		GameObject object =null;
		
		LinkedList<GameObject> list=manager.getObjectList();
		for(int i=0;i<list.size();i++){
			object=list.get(i);
			if(object instanceof Ball){
				manager.remove(object);
			}else if(object instanceof Paddle){
				manager.remove(object);
			}
		}
		Paddle paddle = new Paddle(225, 400, PADDLE_WIDTH, PADDLE_HEIGHT);
		manager.add(paddle);
		game.paddle = paddle;
		input.setPaddle(paddle);
		manager.add(new Ball(230, 380, BALL_DIA, BALL_DIA));
        first=true;
		last=System.currentTimeMillis();
		
	}

}
