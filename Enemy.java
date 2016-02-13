//Enemy.java
//The enemy class for space invaders
//Enemy images are 40x30 in an 11x5 grid

import java.util.ArrayList;
import java.awt.Rectangle;

public class Enemy{
	private int x, y, type; //type is used for image choice
	private static int speed;
	private Rectangle rect;
	
	
	public Enemy(int x, int y, int type){
		speed = 1;
		this.x = x;
		this.y = y;
		this.type = type;
		this.rect = new Rectangle(x, y, 40, 30);
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	public int getType(){return this.type;}
	public Rectangle getRect(){return this.rect;}
	public static int getSpeed(){return speed;}
	
	public void stepDown(){
		this.y += 20;
	}
	
	public void changeDir(){
		if (Panel.getRight() == 800 - 30 || Panel.getLeft() == 0){ //checks if the this enemy hits either wall
			speed *= -1;
			Panel.stepDown();
		}
	}
	
	public void moveEnemy(){
		this.x += speed;
		this.updateRect();
	}
	
	public void updateRect(){
		this.rect.setLocation(this.x, this.y);
	}
	
	
	
	
}