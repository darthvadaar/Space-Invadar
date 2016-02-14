//Enemy.java
//The player class for space invaders
//40x25

import java.util.ArrayList;

public class Player{
	private int x, y, lives, score;
	private Projectile activeBullet;
	
	public Player(){
		this.lives = 3;
		this.x = 400;
		this.y = 750;
		this.activeBullet = new Projectile(-800,800); //dummy bullet with location (-800, 800) for when no bullet has been shot
		this.score = 0;
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public int getLives(){return this.lives;}
	public int getScore(){return this.score;}
	public Projectile getBullet(){return this.activeBullet;}
	
	public void moveR(){
		this.x += 3;
		if (this.x + 40 > 800){
			this.x = 760; 		
		}
	}
	
	public void moveL(){
		this.x -= 3;
		if (this.x < 0){
			this.x = 0; 		
		}
	}
	
	public void shoot(){
		Projectile n = new Projectile(this.x + 18, this.y);
		this.activeBullet = n;
	}
	
	public void removeBullet(){
		this.activeBullet = new Projectile(activeBullet.getdummyX(),activeBullet.getdummyY());//draw it out of the game to avoid null pointers (make dummy bullet which doesnt affect game)
	}
	
	public void plusScore(int n){
		this.score += n;
	}
	
}