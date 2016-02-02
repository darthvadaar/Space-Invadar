//Enemy.java
//The enemy class for space invaders
//images are 40x30
//in box of 11x5 of enemies
public class Enemy{
	int x, y;
	boolean alive;
	
	public Enemy(int x, int y){
		this.x = x;
		this.y = y;
		this.alive = true;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public boolean getAlive(){
		return alive;
	}
	
}