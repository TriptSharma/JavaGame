public class Cactus extends Monster {
    public Cactus(GameState game, int x, int y){
		super(game, x, y);
    }
    
    @Override
    public void onPlayerColision(Player p){
        int x = this.getX();
		int y = this.getY();
		GameState game = this.getGame();
		if(p.occupiesPosition(x, y)){
			System.out.println("Ouch, Thorny! Game Over...");
			game.stop();
        }
    }

    @Override
	public void drawSelf(){
		String sprite = new String("\uD83C\uDF35");
		int x = this.getX();
		int y = this.getY();
		GameState game = this.getGame();
		game.drawSpriteAt(x, y, sprite);
	}

}