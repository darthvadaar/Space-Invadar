//Enemy.java
//The enemy class for space invaders
//Enemy images are 40x30 in an 11x5 grid when the game begins

import java.util.ArrayList;

public class Enemy{
	private int x;
	private static int speed;
	private int y;
	
	
	public Enemy(int x, int y){
		speed = 1;
		this.x = x;
		this.y = y;
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	
	public void stepDown(){
		this.y += 1;
	}
	
	public void moveEnemy(){
		if (Panel.getRight() == 800 - 30 || Panel.getLeft() == 0){
			speed *= -1;
			Panel.stepDown();
		}
		this.x += speed;
	}
	
	
	
	
}