public class Monster extends Entity{
	public Monster(GameState game, int x, int y){
		super(game, x, y);
	}

	@Override
	public boolean isBlockingTo(Entity other){
		/* The other Entity wants to occupy your position.
		Make the monsters solid to all entities except the player.
		 */
		if(other instanceof Player)
			return false;
		return true;
	}
	
}