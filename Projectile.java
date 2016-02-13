//Projectile.java
//This class handles all the projectiles in Space Invader.

public class Projectile{
	private int x, y, speed;
	private static int pause;
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	
	public Projectile(int x, int y){
		this.x = x;
		this.y = y;
		this.speed = 5;
		pause = 75;
	}
	
	public static int minusPause(){
		return pause -= 1;
	}
	
	public static void resetPause(){
		pause = 75;
	}
	
	public void moveProjectile(){
		this.y -= speed;
	}
	
	//method for removing bullets that have travelled off screen******
	
}