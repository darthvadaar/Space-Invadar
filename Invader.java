//Invader.java
//Sid Bedekar
//This is the main game file for the Space Invader remake. It handles all the graphics
//and the general game related methods.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
		game.gameOver();
		game.controls();
		game.move();
		game.collisions();
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
	private static ArrayList<ArrayList<Shield>> shields = new ArrayList<ArrayList<Shield>>();
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
        
        //enemy creation
        for (int x = 100 ; x < 650; x += 50){	//enemies are in a 11x5 grid
			for(int y = 0; y < 200; y += 40){
				Enemy e;
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
		
		//shield creation: large shields w = 150, l = 50
		for (int i = 0; i < 3; i ++){
			ArrayList<Shield> tempList = new ArrayList<Shield>();
			shields.add(tempList);
		}
		for(int x = 75; x < 225; x += 10){	//create 3 large shields out of many smaller ones
		gap:
			for (int y = 650; y < 700; y += 10){
				if (x > 80 && x < 215 && y > 680){
					break gap;					
				}
				Shield n = new Shield(x, y, 10, 10);
				shields.get(0).add(n);
			}
		}
		for (int x = 325; x < 475; x += 10){
		gap2:
			for (int y = 650; y < 700; y += 10){
				if (x > 330 && x < 465 && y > 680){
					break gap2;
				}
				Shield n = new Shield(x, y, 10, 10);
				shields.get(1).add(n);
			}
		}
		for (int x = 575; x < 725; x += 10){
		gap3:
			for (int y = 650; y < 700; y += 10){
				if (x > 580 && x < 715 && y > 680){
					break gap3;
				}
				Shield n = new Shield(x, y, 10, 10);
				shields.get(2).add(n);
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
    	//get rightmost enemy.x
    	int rightMost = -9999;
    	for (Enemy e: enemies){	
	   		if(e.getX() > rightMost){
				rightMost = e.getX();
    		}
    	}
    	return rightMost;
    }
    
    public static int getLeft(){
    	//get leftmost enemy.x
    	int leftMost = 9999;
    	for (Enemy e: enemies){	
	   		if(e.getX() < leftMost){
				leftMost = e.getX();		    
    		}
    	}
    	return leftMost;
    }
    
    public static ArrayList<Enemy> getLowest(){
    	ArrayList<Enemy> canShoot = new ArrayList<Enemy>();
    	for (Enemy e : enemies){
    		canShoot.add(e);
    	}
    	return canShoot;
    }
 
   	public static void stepDown(){
		for (Enemy e: enemies){
			e.stepDown();
		}		
	}
	
	public static void spawnUfo(){
		if (p1.getScore() % 100 == 0 && p1.getScore() > 0){
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
	
	public void gameOver(){
		//end round routine
		if (enemies.size() == 0){
			p1.gameOver();
			respawnEnemies();
		}
		else{
			for (Enemy e : enemies){
				if (e.getY() > 750){
					System.exit(0);
				}				
			}
			if (p1.getLives() < 1){
				System.exit(0);
			}
		}
		
	}
	
	public void respawnEnemies(){
		for (int x = 100 ; x < 650; x += 50){	//enemies are in a 11x5 grid
			for(int y = 0; y < 200; y += 40){
				Enemy e;
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
	
	public void move(){
		//all movement is controlled through this method
		if (p1.getBullet().getX() != p1.getBullet().getdummyX()  && p1.getBullet().getY() != p1.getBullet().getdummyY()){ //doesnt move dummy bullet
			p1.getBullet().moveProjectile(false);
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
		for (Enemy e : getLowest()){
			if (e.getAlive()){
				e.shoot();
				e.getBullet().moveProjectile(true);			
			}
			if (e.getBullet().getY() > 900){
				e.removeBullet();
			}
		}
		
	}
	
	public void collisions(){
		ArrayList<Enemy> removeE = new ArrayList<Enemy>();
		ArrayList<Shield> removeS = new ArrayList<Shield>();
		for (Enemy e : enemies){
			if (e.getRect().intersects(p1.getBullet().getRect())){ //player bullet and enemy
				removeE.add(e);
				p1.removeBullet();
				p1.plusScore(e.getScore());
			}
			if (p1.getRect().intersects(e.getBullet().getRect())){	//enemy bullet and player
				e.removeBullet();
				p1.removeLife();			
			}
			for (int x = 0; x < 3 ; x++){
				for (Shield s : shields.get(x)){
					if (s.getRect().intersects(p1.getBullet().getRect())){	//player bullet and shield
						removeS.add(s);
						p1.removeBullet();
					}
					if (s.getRect().intersects(e.getRect())){ //enemy and shield
						removeS.add(s);
					}
					if (s.getRect().intersects(e.getBullet().getRect())){	//enemy bullet and shield
						removeS.add(s);
						e.removeBullet();
					}
				}
			}
		}
		if (ufo.getRect().intersects(p1.getBullet().getRect())){
			p1.removeBullet();
			ufoStat = false;
			ufo.dummy();
			p1.plusScore(ufo.getScore());			
		}
		enemies.removeAll(removeE);
		shields.get(0).removeAll(removeS);
		shields.get(1).removeAll(removeS);
		shields.get(2).removeAll(removeS);
		
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
			if (e.getAlive()){
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
		}
		for(Enemy e : getLowest()){
			g.fillRect(e.getBullet().getX(), e.getBullet().getY(), e.getBullet().getW(), e.getBullet().getH());
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
		
		//Shield Painting
		for (int x = 0; x < 3 ; x++){
			for (Shield s : shields.get(x)){
				g.fillRect(s.getX(), s.getY(), s.getW(), s.getH());				
			}
		}
						
    }
    
    
    
    
    
    
    
}










