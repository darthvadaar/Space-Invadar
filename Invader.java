//Invader.java
//Sid Bedekar
//This is the main game file for the Space Invader remake. It handles all the graphics and the main method.
/*
 *Known bugs:
 *
 *
 *Pending additions:
 *Shields
 *Collisions
 *HUD (lives, score etc)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;

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
		game.controls();
		game.move();
		game.repaint();
	}
	
	public void start(){
		myTimer.start();
	} 
}
//______________________________________________________
//_______________________PANEL__________________________

class Panel extends JPanel implements KeyListener{
	private boolean []keys;
	private Invader mainFrame;
	BufferedImage playerImg = null;
	Image enemyImg1;
	Image enemyImg2;
	Image enemyImg3;
	
	private static Player p1 = new Player();
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public Panel(Invader m){
		keys = new boolean[KeyEvent.KEY_LAST+1];
		try {
    		playerImg = ImageIO.read(new File("playerImg.png"));
    		enemyImg1 = new ImageIcon("e1.gif").getImage();
    		enemyImg2 = new ImageIcon("e2.gif").getImage();
    		enemyImg3 = new ImageIcon("e3.gif").getImage();
		} 
		catch (IOException e) {
		}
		mainFrame = m;
		setSize(800,800);
        addKeyListener(this);
        
        //add all the enemies into 'enemies' array list
        for (int x = 100 ; x < 650; x += 50){	//enemies are in a 11x5 grid
			for(int y = 0; y < 200; y += 40){
				Enemy e;
				Rectangle r;
				if (y < 80){
					e = new Enemy(x,y,0);
				}
				else if(y < 120){
					e = new Enemy(x,y,1);
				}
				else{
					e = new Enemy(x,y,2);
				}
				enemies.add(e);
			}
		}
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
    
    public static int getRight(){
    	int rightMost = -9999;
    	for (Enemy e: enemies){	
	   		if(e.getX() > rightMost){
				rightMost = e.getX();
    		}
    	}
    	return rightMost;
    }
    
    public static int getLeft(){
    	int leftMost = 9999;
    	for (Enemy e: enemies){	
	   		if(e.getX() < leftMost){
				leftMost = e.getX();		    
    		}
    	}
    	return leftMost;
    }
    
    public void controls(){
		if(keys[KeyEvent.VK_RIGHT] ){
			p1.moveR();
		}
		if(keys[KeyEvent.VK_LEFT] ){
			p1.moveL();
		}
		int pauseTime = Projectile.minusPause();
		if (pauseTime <= 0){
			if(keys[KeyEvent.VK_SPACE] ){
				p1.shoot();	
				Projectile.resetPause();
			}
		}
	}
	
	public void move(){
		for (Projectile p : p1.getBullets()){
			p.moveProjectile();
		}
		for (Enemy e : enemies){
			int prevSpeed = Enemy.getSpeed();
			e.changeDir();
			if (prevSpeed != Enemy.getSpeed()){
				break;
			}	
		}
		for(Enemy e : enemies){
			e.moveEnemy();
		}
	}
	
	public static void stepDown(){
		for (Enemy e: enemies){
			e.stepDown();			
		}		
	}
    
    public void paintComponent(Graphics g){
    	g.setColor(Color.black);
		g.fillRect(0,0,800,800);
		g.drawImage(playerImg,p1.getX(),p1.getY(),this);
		g.setColor(Color.green);
		for (Projectile p : p1.getBullets()){
			g.fillRect(p.getX(), p.getY(), 5, 10);
		}
		for (Enemy e: enemies){
			if (e.getType() == 0){
				g.drawImage(enemyImg1,e.getX(),e.getY(),this);
			}
			else if (e.getType() == 1){
				g.drawImage(enemyImg2,e.getX(),e.getY(),this);
			}
			else{
				g.drawImage(enemyImg3,e.getX(),e.getY(),this);
			}
			g.fillRect((int)e.getRect().getX(), (int)e.getRect().getY(), 40, 30); /////////////////////////////////////////////////////////////////
		}
		
    }
    
    
}










