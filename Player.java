public class Player extends Entity{
	// Constructor
	public Player(GameState game, int x, int y){
		super(game, x, y);
	}

	@Override
	public void drawSelf(){
		String sprite = new String("\uD83D\uDC66");
		int x = this.getX();
		int y = this.getY();
		GameState game = this.getGame();
		game.drawSpriteAt(x, y, sprite);
	}
}