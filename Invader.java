//Invader.java
//Sid Bedekar
//This is the main game file for the Space Invader remake. It handles all the graphics and the main method.
/*
 *Known bugs:
 *Check private/publics
 *
 *Pending additions:
 *Enemy shooting and player hitbox
 *Shields
 *main menu
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
		setSize(800,850);
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
		game.enemyCollisions();
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
	BufferedImage ufoImg = null;
	Image enemyImg1;
	Image enemyImg2;
	Image enemyImg3;
	
	private static Player p1 = new Player();
	private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private static Enemy ufo = new Enemy(-40, 0, 4);
	private static boolean ufoStat = false;
	
	public Panel(Invader m){
		keys = new boolean[KeyEvent.KEY_LAST+1];
		try {
    		playerImg = ImageIO.read(new File("playerImg.png"));
    		ufoImg = ImageIO.read(new File("ufo.png"));
    		enemyImg1 = new ImageIcon("e1.gif").getImage();
    		enemyImg2 = new ImageIcon("e2.gif").getImage();
    		enemyImg3 = new ImageIcon("e3.gif").getImage();
		} 
		catch (IOException e) {
		}
		mainFrame = m;
		setSize(800,850);
        addKeyListener(this);
        
        //add all the enemies into 'enemies' array list
        for (int x = 100 ; x < 650; x += 50){	//enemies are in a 11x5 grid
			for(int y = 0; y < 200; y += 40){
				Enemy e;
				Rectangle r;
				if (y < 40){
					e = new Enemy(x,y,1);
				}
				else if(y < 120){
					e = new Enemy(x,y,2);
				}
				else{
					e = new Enemy(x,y,3);
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
    
   	public static void stepDown(){
		for (Enemy e: enemies){
			e.stepDown();
		}		
	}
	
	public static void spawnUfo(){
		if (p1.getScore() % 50 == 0 && p1.getScore() > 0){
			ufoStat = true;
		}
		if (ufo.getX() > 800){
			ufoStat = false;
			ufo.dummy();			
		}
	}
    
    public void controls(){
		if(keys[KeyEvent.VK_RIGHT] ){
			p1.moveR();
		}
		if(keys[KeyEvent.VK_LEFT] ){
			p1.moveL();
		}
		if(keys[KeyEvent.VK_SPACE] ){
			if (p1.getBullet().getX() == p1.getBullet().getdummyX() && p1.getBullet().getY() == p1.getBullet().getdummyY()){
				p1.shoot();
			}
		}
	}
	
	public void move(){
		if (p1.getBullet().getX() != p1.getBullet().getdummyX()  && p1.getBullet().getY() != p1.getBullet().getdummyY()){ //doesnt move dummy bullet
			p1.getBullet().moveProjectile();
			if (p1.getBullet().getY() < 0){
				p1.removeBullet();				
			}
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
		spawnUfo();
		if (ufoStat){
			ufo.moveEnemy();			
		}
		
	}
	
	public void enemyCollisions(){
		ArrayList<Enemy> removeE = new ArrayList<Enemy>();
		ArrayList<Projectile> removeP = new ArrayList<Projectile>();
		for (Enemy e : enemies){
			if (e.getRect().intersects(p1.getBullet().getRect())){
				removeE.add(e);
				p1.removeBullet();
				p1.plusScore(e.getScore());
			}	
		}
		if (ufo.getRect().intersects(p1.getBullet().getRect())){
			p1.removeBullet();
			ufoStat = false;
			ufo.dummy();
			p1.plusScore(ufo.getScore());			
		}
		enemies.removeAll(removeE);
	}
		
    
    public void paintComponent(Graphics g){
    	Font f = new Font("Monospaced",Font.BOLD, 20);
    	g.setFont(f);
    	g.setColor(Color.black);
		g.fillRect(0,0,800,850);
		g.drawImage(playerImg,p1.getX(),p1.getY(),this);
		g.setColor(Color.green);
		g.fillRect(p1.getBullet().getX(), p1.getBullet().getY(), p1.getBullet().getW(), p1.getBullet().getH());
		for (Enemy e: enemies){
			if (e.getType() == 1){
				g.drawImage(enemyImg1,e.getX(),e.getY(),this);
			}
			else if (e.getType() == 2){
				g.drawImage(enemyImg2,e.getX(),e.getY(),this);
			}
			else{
				g.drawImage(enemyImg3,e.getX(),e.getY(),this);
			}
		}
		if (ufoStat){
			g.drawImage(ufoImg, ufo.getX(), ufo.getY(), this);
		}
		
		
		//HUD Painting
		g.setColor(Color.green);
		g.drawLine(0,775,800,775);
		for (int i = 0; i < p1.getLives(); i++){
			g.drawImage(playerImg, 125 + 50 * i, 780, this);			
		}
		g.drawString("LIVES:", 50, 805);
		
		g.drawString("SCORE:", 350, 805);
		g.drawString(Integer.toString(p1.getScore()), 425, 805);
						
    }
    
    
    
    
    
    
    
}










