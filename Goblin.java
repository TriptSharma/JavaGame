// import java.lang.*;

public class Goblin extends Monster{
    private static int counter;
    public Goblin(GameState game, int x, int y){
        super(game, x, y);
        counter=0;
    }
    
    public void tick(){
        if(counter%3==0){
            counter++;
            return;
        }
        Player player = this.getGame().getPlayer();
        int x = this.getX();
        int y = this.getY();
        
        int xdiff = x-player.getX();
        int ydiff = y-player.getY();
        boolean canLeft, canRight, canUp, canDown;
        canLeft = this.canMoveLeft();
        canRight = this.canMoveRight();
        canUp = this.canMoveUp();
        canDown = this.canMoveDown();

        if(Math.abs(xdiff)>Math.abs(ydiff)){
            if(xdiff>0){
                if(canLeft){
                    moveLeft();
                }
                else{
                    if(ydiff>0){
                        if(canUp)
                            moveUp();
                        else if(canDown)
                            moveDown();
                    }
                    else{
                        if(canDown)
                            moveDown();
                        else if(canUp)
                            moveUp();
                    }
                }
            }
            else{
                if(canRight){
                    moveRight();
                }
                else{
                    if(ydiff>0){
                        if(canUp)
                            moveUp();
                        else if(canDown)
                            moveDown();
                    }
                    else{
                        if(canDown)
                            moveDown();
                        else if(canUp)
                            moveUp();
                    }
                }
            }
        }
        else{
            if(ydiff>0){
                if(canUp){
                    moveUp();
                }
                else{
                    if(xdiff>0){
                        if(canLeft)
                            moveLeft();
                        else if(canRight)
                            moveRight();
                    }
                    else{
                        if(canRight)
                            moveRight();
                        else if(canLeft)
                            moveLeft();
                    }
                }
            }
            else{
                if(canDown){
                    moveDown();
                }
                else{
                    if(xdiff>0){
                        if(canLeft)
                            moveLeft();
                        else if(canRight)
                            moveRight();
                    }
                    else{
                        if(canRight)
                            moveRight();
                        else if(canLeft)
                            moveLeft();
                    }
                }
            }
        }
        counter++;
    }

    @Override
	public void drawSelf(){
		String sprite = new String("\uD83D\uDC7A");
		int x = this.getX();
		int y = this.getY();
		GameState game = this.getGame();
		game.drawSpriteAt(x, y, sprite);
    }

    @Override
    public void onPlayerColision(Player p){
        int x = this.getX();
		int y = this.getY();
		GameState game = this.getGame();
		if(p.occupiesPosition(x, y)){
			System.out.println("Caught Ya! Your truly, Mr. Goblin \nGame Over...");
			game.stop();
        }
    }
}