//Enemy.java
//The player class for space invaders
//40x25

import java.util.ArrayList;

public class Player{
	private int x, y, lives, score;
	private ArrayList<Projectile> bullets = new ArrayList<Projectile>();
	
	public Player(){
		this.lives = 3;
		this.x = 400;
		this.y = 750;
		this.score = 0;
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public ArrayList<Projectile> getBullets(){return this.bullets;}
	
	public void moveR(){
		this.x += 3;	
	}
	
	public void moveL(){
		this.x -= 3;		
	}
	
	public void shoot(){
		Projectile n = new Projectile(this.x, this.y);
		bullets.add(n);
	}
	
	public void plusScore(){
		this.score += 10;
	}
	
}