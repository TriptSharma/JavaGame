public class SafeZone extends MultiWall{
    public SafeZone(GameState game, int x, int y, int width, int height){
        super(game, x, y, width, height);
    }

    @Override
	public boolean isBlockingTo(Entity other){
		/* The other Entity wants to occupy your position.
		Make the zone solid to all entities except the player.
		 */
		if(other instanceof Player)
			return false;
		return true;
    }
    
    @Override
	public void drawSelf(){
		String sprite = new String("\u2591\u2591");
		int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();
		GameState game = this.getGame();
        
        for(int ix=x; ix<x+width; ix++){
            for(int jy=y; jy<y+height; jy++){
                game.drawSpriteAt(ix, jy, sprite);
            }
        }
	}
}