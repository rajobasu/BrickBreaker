package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	private static ImageLoader singleton;
	
	private BufferedImage brick0;
	private BufferedImage brick1;
	private BufferedImage brick2;
	private BufferedImage brick3;
	private BufferedImage brick4;
	private BufferedImage brick5;
	
	private BufferedImage paddle;
	private BufferedImage ball;
	private BufferedImage background;
	private ImageLoader() {
		try {
			brick0=ImageIO.read(ImageLoader.class.getResource("/lvl0_br.png"));
			brick1=ImageIO.read(ImageLoader.class.getResource("/lvl1_br.png"));
			brick2=ImageIO.read(ImageLoader.class.getResource("/lvl2_br.png"));
		     
			paddle=ImageIO.read(ImageLoader.class.getResource("/paddle.png"));
			ball=ImageIO.read(ImageLoader.class.getResource("/ball.png"));
			background=ImageIO.read(ImageLoader.class.getResource("/BackGround.png"));		
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getSprite(String id){
		switch(id){
		case "paddle":return paddle;
		case "ball":return ball;
		case "br0":return brick0;
		case "br1":return brick1;
		case "br2":return brick2;
		case "background":return background;
		default :
			System.out.println("in Default");
			return null;		
		
		}
	}
	public static ImageLoader getInstance(){
		
		
		if(singleton==null){
			singleton=new ImageLoader();
		}
		
		return singleton;
		
	}
	
}
