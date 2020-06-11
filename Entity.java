import java.util.*;
import java.io.*;

public class Entity 
{
	private int x; // Increase to move Right
	private int y; // Increase to move Down
	private GameState game;

	public Entity(GameState game, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.game = game;
	}
	
	public int getX() { return x;}
	public int getY() { return y;}
	public GameState getGame() { return game;}

	public void drawSelf(){
		String sprite = new String("  ");
		game.drawSpriteAt(x, y, sprite);
	}

	public void tick(){}

	public boolean canMoveTo(int newx, int newy){
		boolean isOutBounds = game.isOutOfBounds(newx, newy);
		boolean blocked = game.isBlocked(this, newx, newy);
		if(isOutBounds)
			return false;
		if(blocked)
			return false;
		return true;
	}

	public boolean isBlockingTo(Entity wantsToMoveHere)
	{
		// Returning false means that this entity is not solid and other entites can walk though it
		return false;
	}

	public boolean canMoveUp(){
		boolean val = canMoveTo(x, y-1);
		return val;
	}
	public boolean canMoveDown(){
		boolean val = canMoveTo(x, y+1);
		return val;
	}
	public boolean canMoveLeft(){
		boolean val = canMoveTo(x-1, y);
		return val;
	}
	public boolean canMoveRight(){
		boolean val = canMoveTo(x+1, y);
		return val;
	}

	public void moveUp(){
		boolean movePossible = canMoveUp();
		if(movePossible){
			// System.out.println("Check by "+ this.getClass());
			this.y = this.y-1;
			game.checkPlayerColision();
		}
	}
	public void moveDown(){
		boolean movePossible = canMoveDown();
		if(movePossible){
			// System.out.println("Check by "+ this.getClass());
			this.y = this.y+1;
			game.checkPlayerColision();
		}
	}
	public void moveLeft(){
		boolean movePossible = canMoveLeft();
		if(movePossible){
			// System.out.println("Check by "+ this.getClass());
			this.x = this.x-1;
			game.checkPlayerColision();
		}
	}
	public void moveRight(){
		boolean movePossible = canMoveRight();
		if(movePossible){
			// System.out.println("Check by "+ this.getClass());
			this.x = this.x+1;
			game.checkPlayerColision();
		}
	}

	public boolean occupiesPosition(int x, int y){
		if(this.x==x && this.y==y){
			// System.out.println("Occupy is True");
			return true;
		}
		// System.out.println("Occupy is false");
		return false;
	}

	public void onPlayerColision(Player p){
		
		return;
	}
}