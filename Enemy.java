//Enemy.java
//Sid Bedekar
//The enemy class for space invaders handles all enemy attributs
//There are four possible enemy types 3 of which are always on screen
//and the 4th (UFO) which appears every 100 points.

import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;

public class Enemy{
	private int x, y, type, score; //type is used for image choice
	private boolean alive;	//score = # of points awarded to player
	private static int speed;
	private final int dummyX, dummyY;	//location of UFO when it hasn't appeared (avoiding null pointers)
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
		//only allows the enemy to shoot if the bullet is located at dummyX && dummyY
		Random rand = new Random();
		if (rand.nextInt(250) == 5 && this.activeBullet.getX() == this.activeBullet.getdummyX() && this.activeBullet.getY() == this.activeBullet.getdummyY()){
			Projectile n = new Projectile(this.x, this.y);
			this.activeBullet = n;
		}
	}
	
	public void removeBullet(){
		//relocated the bullet to dummyX,dummyY (of the Projectile class)
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
		//updates the rectangle to the moving image
		this.rect.setLocation(this.x, this.y);
	}
	
	public void dummy(){
		//relocated the enemy to dummyX, dummyY
		if (this.type == 4){
			this.x = -40;
			this.y = 0;
		}
		
	}
	
	
	
	
}