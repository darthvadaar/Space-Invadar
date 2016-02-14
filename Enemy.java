//Enemy.java
//The enemy class for space invaders
//Enemy images are 40x30 in an 11x5 grid

import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;

public class Enemy{
	private int x, y, type, score; //type is used for image choice
	private boolean alive;
	private static int speed;
	private final int dummyX, dummyY;
	private Projectile activeBullet;
	private Rectangle rect;
	
	
	public Enemy(int x, int y, int type){
		speed = 1;
		this.x = x;
		this.y = y;
		this.alive = true;
		this.type = type;
		this.rect = new Rectangle(x, y, 40, 30);
		this.dummyX = -40;
		this.dummyY = 0;
		this.activeBullet = new Projectile(-800, 800);
		
		if(type == 1){
			this.score = 30;
		}
		else if(type == 2){
			this.score = 20;
		}
		else if(type == 3){
			this.score = 10;
		}
		else{
			this.score = 200;
		}
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public int getType(){return this.type;}
	public boolean getAlive(){return this.alive;}
	public Rectangle getRect(){return this.rect;}
	public Projectile getBullet(){return this.activeBullet;}
	public int getScore(){return this.score;}
	public static int getSpeed(){return speed;}
	
	public void stepDown(){
		this.y += 15;
	}
	
	public void shoot(){
		Random rand = new Random();
		if (rand.nextInt(250) == 5 && this.activeBullet.getX() == this.activeBullet.getdummyX() && this.activeBullet.getY() == this.activeBullet.getdummyY()){
			Projectile n = new Projectile(this.x, this.y);
			this.activeBullet = n;
		}
	}
	
	public void removeBullet(){
		this.activeBullet = new Projectile(activeBullet.getdummyX(),activeBullet.getdummyY());
	}
	
	public void changeDir(){
		if (Panel.getRight() == 800 - 30 || Panel.getLeft() == 0){ //checks if the this enemy hits either wall
			speed *= -1;
			Panel.stepDown();
		}
	}
	
	public void moveEnemy(){
		if (this.type == 4){
			this.x += 1;			
		}
		else{
			this.x += speed;
		}
		this.updateRect();
		
	}
	
	public void updateRect(){
		this.rect.setLocation(this.x, this.y);
	}
	
	public void dummy(){
		if (this.type == 4){
			this.x = -40;
			this.y = 0;
		}
		
	}
	
	
	
	
}