public class Wall extends Entity{
	public Wall(GameState game, int x, int y){
		super(game, x, y);
	}

	@Override
	public boolean isBlockingTo(Entity other){
		/* Wall is always Solid... Duh!*/
		return true;
	}
	
	@Override
	public void drawSelf(){
		String sprite = new String("\u2588\u2588");
		int x = this.getX();
		int y = this.getY();
		GameState game = this.getGame();
		game.drawSpriteAt(x, y, sprite);
	}

}