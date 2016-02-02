//Enemy.java
//The player class for space invaders
//40x25

public class Player{
	int x, y, lives;
	
	public Player(){
		this.lives = 3;
		this.x = 400;
		this.y = 750;
		
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	
	public void moveR(){
		this.x += 5;
			
	}
	
	public void moveL(){
		this.x -= 5;		
	}
	
}