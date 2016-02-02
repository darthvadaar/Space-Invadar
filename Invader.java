//Invader.java
//Sid Bedekar

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Invader extends JFrame implements ActionListener{
	Timer myTimer;
	Panel game;
	
	public Invader(){
		super("Space Invader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,800);
		setResizable(false);
		setVisible(true);
		myTimer = new Timer(10, this);
		game = new Panel(this);
		add(game);
		
	}
	
	public static void main(String[]args){
		Invader b = new Invader();
		
	}
		
	@Override
	public void actionPerformed(ActionEvent evt){
		game.move();
		game.repaint();
	}
	
	public void start(){
		myTimer.start();
	
	} 
}


class Panel extends JPanel implements KeyListener{
	private boolean []keys;
	private Invader mainFrame;
	BufferedImage playerImg = null;
	
	private Player p1 = new Player();
	private Enemy[]enemies = new Enemy[55];
	for (int x = 0 ; x < 440; x += 40 ){
		for (int y = 0; y < 150; y += 30){
			
		}
		
	}
	
	public Panel(Invader m){
		keys = new boolean[KeyEvent.KEY_LAST+1];
		try {
    		playerImg = ImageIO.read(new File("playerImg.png"));
		} 
		catch (IOException e) {
		}
		mainFrame = m;
		setSize(800,800);
        addKeyListener(this);
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }
	
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    
    public void keyReleased(KeyEvent e) {
    	keys[e.getKeyCode()] = false;
    }
    
    public void move(){
		if(keys[KeyEvent.VK_RIGHT] ){
			p1.moveR();
		}
		if(keys[KeyEvent.VK_LEFT] ){
			p1.moveL();
		}
	}
    
    public void paintComponent(Graphics g){ 	
    	g.setColor(Color.black);
		g.fillRect(0,0,800,800);
		g.drawImage(playerImg,p1.getX(),p1.getY(),this);
		
    }
    
    
}










