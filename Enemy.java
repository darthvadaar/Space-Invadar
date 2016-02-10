//Enemy.java
//The enemy class for space invaders
//Enemy images are 40x30 in an 11x5 grid when the game begins

import java.util.ArrayList;

public class Enemy{
	private int x;
	private static int speed;
	private int y;
	
	
	public Enemy(int x, int y){
		speed = 5;
		this.x = x;
		this.y = y;
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	
	public boolean checkR(){
		//returns true if there are other enemies to the right of this enemy
		for (Enemy e: Panel.enemies){
			if (this.x < e.x){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkL(){
		//returns true if there are other enemies to the left of this enemy
		for (Enemy e: Panel.enemies){
			if (this.x > e.x){
				return true;
			}
		}
		return false;
	}
	
	public void moveEnemy(){
		if (this.checkR() == false || this.checkL() == false){
			if (this.x == 800 || this.x == 0){
				speed *= -1;
			}
		}
		this.x += speed;
	}
	
	
	
	
}