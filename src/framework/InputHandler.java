package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter{
	private Movable paddle;
	public InputHandler(Movable paddle){
		this.paddle=paddle;
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			paddle.setDeltaX(-4);
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			paddle.setDeltaX(4);
		}
	}
	public void keyReleased(KeyEvent e){
		paddle.setDeltaX(0);
	}
	public void setPaddle(Movable paddle){
		this.paddle=paddle;
	}
}
