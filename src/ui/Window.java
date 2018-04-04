package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import framework.Game;

public class Window {

	private JFrame frame;
	private Canvas canvas;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblScore;
	private JLabel score;
	private JLabel lblLevel;
	private JLabel level;
	private JLabel life;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Window(Game game) {
		initialize(game);
	}

	
	public void setIcon() {
		Image icon=null;
		try {
			icon = ImageIO.read(Window.class.getResource("/bb.ico"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setIconImage(icon);
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Game game) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
           
        } catch (InstantiationException ex) {

        } catch (IllegalAccessException ex) {

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

        }
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(600, 575);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		canvas = game;
		canvas.setBackground(Color.GRAY);
		canvas.setVisible(true);
		canvas.setBounds(0, 0, 450, 450);
		frame.getContentPane().setLayout(null);
		
		
		
		panel = new JPanel();
		panel.setBounds(0, 0, 450, 450);
		frame.getContentPane().add(panel);
		panel.add(canvas);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBounds(460, 0, 114, 450);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblScore = new JLabel("Score");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setBackground(new Color(0, 100, 0));
		lblScore.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblScore.setForeground(Color.RED);
		lblScore.setBounds(10, 11, 94, 30);
		panel_1.add(lblScore);
		
		score = new JLabel("0");
		score.setFont(new Font("Tahoma", Font.BOLD, 14));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(10, 52, 94, 30);
		panel_1.add(score);
		
		lblLevel = new JLabel("Level");
		lblLevel.setForeground(new Color(255, 0, 0));
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
		lblLevel.setBounds(10, 93, 94, 20);
		panel_1.add(lblLevel);
		
		level = new JLabel("1");
		level.setFont(new Font("Tahoma", Font.BOLD, 14));
		level.setHorizontalAlignment(SwingConstants.CENTER);
		level.setBounds(20, 113, 84, 20);
		panel_1.add(level);
		
		JLabel lblLife = new JLabel("Life");
		lblLife.setForeground(new Color(255, 140, 0));
		lblLife.setHorizontalAlignment(SwingConstants.CENTER);
		lblLife.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
		lblLife.setBounds(10, 155, 94, 20);
		panel_1.add(lblLife);
		
		life = new JLabel("3");
		life.setFont(new Font("Tahoma", Font.BOLD, 14));
		life.setHorizontalAlignment(SwingConstants.CENTER);
		life.setBounds(20, 186, 84, 20);
		panel_1.add(life);
		
		JLabel lblMadeByRajarshi = new JLabel("Programmed  By ");
		lblMadeByRajarshi.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblMadeByRajarshi.setHorizontalAlignment(SwingConstants.CENTER);
		lblMadeByRajarshi.setBounds(0, 381, 114, 30);
		panel_1.add(lblMadeByRajarshi);
		
		JLabel lblRajarshiBasu = new JLabel("Rajarshi Basu");
		lblRajarshiBasu.setFont(new Font("Tekton Pro Ext", Font.PLAIN, 13));
		lblRajarshiBasu.setHorizontalAlignment(SwingConstants.CENTER);
		lblRajarshiBasu.setBounds(0, 409, 114, 30);
		panel_1.add(lblRajarshiBasu);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setForeground(Color.RED);
		panel_2.setBounds(0, 467, 437, 69);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel txtrToMoveUse = new JLabel();
		txtrToMoveUse.setHorizontalAlignment(SwingConstants.CENTER);
		txtrToMoveUse.setFont(new Font("Felix Titling", Font.PLAIN, 14));
		txtrToMoveUse.setText("  To  Move   Use   The   Arrow   Keys");
		txtrToMoveUse.setBounds(0, 0, 437, 69);
		panel_2.add(txtrToMoveUse);
	}
	public  void setHitCount(int score){
		this.score.setText(Integer.toString(score));
	}
	public  void setLevel(int level){
		this.level.setText(Integer.toString(level));;
		}
	public  void setLife(int life){
		this.life.setText(Integer.toString(life));
	}
	public  void wonMsg(){
		JOptionPane.showMessageDialog(frame, "You have won!!!");
	}
	public  void lostMsg(){
		JOptionPane.showMessageDialog(frame, "You have Lost!!!");
		Game.stop=false;
	}
}
