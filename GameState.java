import java.util.*;
import java.io.*;
import java.awt.Graphics;

public class GameState
{
	private static int FRAME_HEIGHT = 10;
	private static int FRAME_WIDTH = 20;
	private String[][] frame;
	private Player player;
	private Random rand;
	private boolean gameFinished;
	private Entity[] entities;
	
	// Constructor
	public GameState()
	{
		rand = new Random();
		frame = new String[FRAME_HEIGHT][FRAME_WIDTH];
		this.resetGame();
	}
	
	// call this from the constructor 
	private void resetGame()
	{
		this.gameFinished = false;
		
		// Instantiate the Player
		this.player = new Player(this, 5,3);	
		
		// Remeber you can initalise an array like this.
		// Uncomment each entity when you are ready to test them.
		this.entities = new Entity[] {
			new Ghost(this,10, 8),
			new Alien(this, 12,  9),
			new Ball(this, 8,  9, Direction.UP),
			new Ball(this, 9,  9, Direction.RIGHT),
			new Wall(this, 4,  5),
			new MultiWall(this, 3,  2, 5, 1),
			new Goblin(this, 9,  7),
			new Flag(this, 5, 8),
			new Cactus(this, 7, 9),
			new SafeZone(this, 1, 3, 1, 3)
		};
	}
	
	public void showMenu()
	{
		System.out.println("== Menu ==");
		System.out.println("Press R to start a new game");
		System.out.println("Press esc to exit");
		
		// Scanner key = new Scanner(System.in);
		// while(1){
		// 	input = key.next().charAt(0);
		// 	if(input=='r' || input=='R'){
		// 		this.resetGame();
		// 	}
		// 	else if((int)input==27){
		// 		return false;
		// 	}
		// }
	}
	
	private void drawEntities()
	{
		// call drawSelf() on each entity in the array.
		for(int i=0; i<entities.length; i++){
			entities[i].drawSelf();
		}
		//print player location
		this.player.drawSelf();
	}
	private void clearFrame()
	{
		// Clear the frame array and fill it will nulls.
		for(int i=0; i<FRAME_HEIGHT; i++){
			for(int j=0; j<FRAME_WIDTH; j++){
				frame[i][j]=null;
			}
		}
	}
	
	// Draw the entities to the screen.
	// This method was previosly called drawMap()	
	public void renderFrame()
	{
		clearFrame();
		
		String borderV = new String("\u2502");
		String borderH = new String("\u2500\u2500");
		
		String borderC1 = new String("\u2513");
		String borderC2 = new String("\u2519");
		String borderC3 = new String("\u2515");
		String borderC4 = new String("\u250D");
		
		for(int i=0; i<FRAME_HEIGHT; i++){
			frame[i][0]=borderV;
			frame[i][FRAME_WIDTH-1]=borderV;
		}
		
		for(int j=0; j<FRAME_WIDTH; j++){
			frame[0][j]=borderH;
			frame[FRAME_HEIGHT-1][j]=borderH;
		}
		frame[0][FRAME_WIDTH-1]=borderC1;
		frame[FRAME_HEIGHT-1][FRAME_WIDTH-1]=borderC2;
		frame[FRAME_HEIGHT-1][0]=borderC3;
		frame[0][0]=borderC4;

		drawEntities();
		
		// print out the contents of frame using two for loops.
		for(int i=0; i<FRAME_HEIGHT; i++){
			for(int j=0; j<FRAME_WIDTH; j++){
				if((i>0 && i<(FRAME_HEIGHT-1)) && (j>0 && j<(FRAME_WIDTH-1)) && (frame[i][j]==null)){
					System.out.print("  ");
				}
				else{
					System.out.print(frame[i][j]);
				}
			}
			System.out.println();
		}

	}
	public boolean isOutOfBounds(int x, int y){
		if(x>=0 && y>=0 && x<FRAME_WIDTH && y<FRAME_HEIGHT)
			return false;
		return true;
	}

	public void drawSpriteAt(int x, int y, String sprite)
	{
		// put a sprite string to the frame array.
		boolean isOutBounds = isOutOfBounds(x, y);
		if(!isOutBounds){			
			// Y = height (which in terms of array is X dim, similarly x)
			// X represents cols
			frame[y][x]=sprite;
		}
	}
	
	// This method was previosly called update()
	public void tick()
	{
		if(this.gameFinished==true)
			return;
		// Update all the entities in the array.
		for(int i=0;i<entities.length;i++){
			entities[i].tick();
		}
		if(this.gameFinished==true)
			this.showMenu();
		else
			renderFrame();
	}
	
	// Called when the user presses a key on the keyboard.
	// return false to exit the game.
	// This method was previosly called onInput()
	public boolean onUserInput(int asciiCode)
	{
		// handle user input in here.
		//W Pressed
		if(asciiCode==119 || asciiCode==87){
			player.moveUp();
		}
		//A Pressed
		else if(asciiCode==65 || asciiCode==97){
			player.moveLeft();
		}
		//S Pressed
		else if(asciiCode==83 || asciiCode==115){
			player.moveDown();
		}
		//D Pressed
		else if(asciiCode==68 || asciiCode==100){
			player.moveRight();
		}
		//R Pressed
		else if(asciiCode==82 || asciiCode==114){
			resetGame();
		}
		//Esc Pressed
		else if(asciiCode==27){
			return false;
		}

		// System.out.printf("You Pressed ASCII code: %d \n", asciiCode);
		return true;
	}
	
	public void stop(){
		renderFrame();
		gameFinished=true;
	}
	public Player getPlayer(){
		return player;
	}

	public Random getRandom(){
		return rand;
	}

	public boolean isGameFinished()
	{
		return this.gameFinished;
	}

	public boolean isBlocked(Entity e, int x, int y){
		for(int i=0;i<entities.length;i++){
			if(entities[i].isBlockingTo(e) && entities[i].occupiesPosition(x, y)){
				return true;
			}
		}
		return false;
	}

	public void checkPlayerColision(){
		int playerX = this.player.getX();
		int playerY = this.player.getY();
		
		for(int i=0; i<entities.length; i++){
			if(entities[i].occupiesPosition(playerX, playerY)){
				
				entities[i].onPlayerColision(this.player);
			}
		}
	}

	public void removeEntity(Entity e){
		if (entities == null) { 
            return; 
        }
		int index = -1;

		for(int i=0; i<entities.length; i++){
			if(entities[i]==e){
				index=i;
				break;
			}
		}
		if(index!=-1){
			// Create another array of size one less 
			Entity[] dummy = new Entity[entities.length - 1]; 
	
			// Copy the elements except the index from original array to the other array 
			for (int i = 0, k = 0; i < entities.length; i++) { 
	
				// if the index is 
				// the removal element index 
				if (i == index) { 
					continue; 
				}
				// if the index is not the removal element index 
				dummy[k++] = entities[i]; 
			} 
			this.entities = dummy;
		}
	}
}