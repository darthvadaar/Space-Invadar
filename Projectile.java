//Projectile.java
//This class handles all the projectiles in Space Invader.

import java.awt.Rectangle;

public class Projectile{
	private int x, y, w, h, speed;
	private static int pause;
	private Rectangle rect;
	
	public Projectile(int x, int y){
		this.x = x;
		this.y = y;
		this.w = 5;
		this.h = 10;
		this.speed = 5;
		this.rect = new Rectangle(x, y, w, h);
		pause = 75;
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public int getW(){return this.w;}
	public int getH(){return this.h;}
	public Rectangle getRect(){return this.rect;}
	
	public static int minusPause(){
		return pause -= 1;
	}
	
	public static void resetPause(){
		pause = 75;
	}
	
	public void moveProjectile(){
		this.y -= speed;
		this.updateRect();
	}
	
	public void updateRect(){
		this.rect.setLocation(this.x, this.y);
	}
	
	//method for removing bullets that have travelled off screen******
	
}