public class Flag extends Entity {
	public Flag(GameState game, int x, int y){
		super(game, x, y);
	}
	
	@Override
	public void drawSelf(){
		String sprite = new String("\uD83C\uDFF4");
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
		// System.out.println("Inside onPLayer");
		if(p.occupiesPosition(x, y)==true){
			System.out.println("You Won!! Hurray!!");
			game.stop();
		}
	}
}