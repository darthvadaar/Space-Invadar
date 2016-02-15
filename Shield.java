//Shield.java
//Sid Bedekar
//The shield class for Space Invader

import java.awt.Rectangle;

public class Shield{
	private int x, y, w, h;
	private Rectangle rect;
	
	public Shield(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.rect = new Rectangle(x, y, w, h);
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public int getW(){return this.w;}
	public int getH(){return this.h;}
	public Rectangle getRect(){return this.rect;}
	
	
	
	
	
}