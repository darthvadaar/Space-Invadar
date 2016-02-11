//Projectile.java
//This class handles all the projectiles in Space Invader.

public class Projectile{
	private int x, y, speed;
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	
	public Projectile(int x, int y){
		this.x = x;
		this.y = y;
		this.speed = 1;
	}
	
	public void move(){
		this.y -= 1;
	}
	
	//method for removing bullets that have travelled off screen******
	
}