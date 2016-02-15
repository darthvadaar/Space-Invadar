//Enemy.java
//Sid Bedekar
//The player class for space invaders handles player attributs 
//such as x, y, player's bullet etc.

import java.util.ArrayList;
import java.awt.Rectangle;

public class Player{
	private int x, y, w, l, lives, score;
	private Projectile activeBullet;
	private Rectangle rect;
	
	public Player(){
		this.lives = 3;
		this.x = 400;
		this.y = 750;
		this.w = 40;
		this.l = 25;
		this.activeBullet = new Projectile(-800,800); //dummy bullet with location (-800, 800) for when no bullet has been shot
		this.rect = new Rectangle(x, y, w, l);
		this.score = 0;
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public Rectangle getRect(){return this.rect;}
	public int getLives(){return this.lives;}
	public int getScore(){return this.score;}
	public Projectile getBullet(){return this.activeBullet;}
	
	public void moveR(){
		this.x += 3;
		if (this.x + 40 > 800){
			this.x = 760; 		
		}
		this.updateRect();
	}
	
	public void moveL(){
		this.x -= 3;
		if (this.x < 0){
			this.x = 0; 		
		}
		this.updateRect();
	}
	
	public void updateRect(){
		this.rect.setLocation(this.x, this.y);
	}
	
	public void removeLife(){
		this.lives -= 1;
		//insert some kind of pause or animation
	}
	
	public void gameOver(){
		//player gameOver means player has eliminated all enemies on screen
		this.lives += 1;
	}
	
	public void shoot(){
		Projectile n = new Projectile(this.x + 18, this.y);
		this.activeBullet = n;
	}
	
	public void removeBullet(){
		//draw it out of the game to avoid null pointers (make dummy bullet which doesnt affect game)
		this.activeBullet = new Projectile(activeBullet.getdummyX(),activeBullet.getdummyY());
	}
	
	public void plusScore(int n){
		this.score += n;
	}
	
}