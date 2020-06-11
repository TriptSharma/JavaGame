public class Ghost extends Monster{
    public Ghost(GameState game, int x, int y){
		super(game, x, y);
    }
    
    public void onPlayerColision(Player p){
        int x = this.getX();
		int y = this.getY();
        GameState game = this.getGame();
        
		if(p.occupiesPosition(x, y)){
			System.out.println("Scared to Death! Game Over...");
			game.stop();
        }
    }

    @Override
	public void drawSelf(){
		String sprite = new String("\uD83D\uDC7B");
		int x = this.getX();
		int y = this.getY();
		GameState game = this.getGame();
		game.drawSpriteAt(x, y, sprite);
    }
    
    @Override
    public void tick(){
        GameState game = this.getGame();
        
        int directionIndex = game.getRandom().nextInt(5);
        switch(directionIndex){
            case 0:
                this.moveUp();
                break;
            case 1:
                this.moveDown();
                break;
            case 2:
                this.moveLeft();
                break;
            case 3:
                this.moveRight();
                break;
            case 4:
                break;
            default:
                break;
        }
        
    }
}