//Projectile.java
//This class handles all the projectiles in Space Invader.

import java.awt.Rectangle;

public class Projectile{
	private int x, y, w, h, speed;
	private final int dummyX, dummyY;
	private Rectangle rect;
	
	public Projectile(int x, int y){
		this.x = x;
		this.y = y;
		this.w = 5;
		this.h = 10;
		this.dummyX = -800;
		this.dummyY = 800;
		this.speed = 5;
		this.rect = new Rectangle(x, y, w, h);
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public int getW(){return this.w;}
	public int getH(){return this.h;}
	public int getdummyX(){return this.dummyX;}
	public int getdummyY(){return this.dummyY;}
	public Rectangle getRect(){return this.rect;}
	
	public void moveProjectile(boolean dir){ //true = (+), false = (-)
		if (dir){
			this.y += speed;
		}
		else{
			this.y -= speed;
		}
		this.updateRect();
	}
	
	public void updateRect(){
		this.rect.setLocation(this.x, this.y);
	}
	
}