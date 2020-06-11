public class Ball extends Monster{
    // The Ball, travels in one direction until it cannot go any further, 
    // then it bounces backwards
    private Direction DIRECTION;

    public Ball(GameState game, int x, int y, Direction DIRECTION){
        super(game, x, y);
        this.DIRECTION = DIRECTION;
    }

    public void tick(){
        switch(DIRECTION){
            case UP:
                if(canMoveUp())
                    this.moveUp();
                else{
                    this.DIRECTION = Direction.DOWN;
                    this.moveDown();
                }
                break;
            case DOWN:
                if(canMoveDown())
                    this.moveDown();
                else{
                    this.moveUp();
                    this.DIRECTION = Direction.UP;
                }
                break;
            case LEFT:
                if(canMoveLeft())
                    this.moveLeft();
                else{
                    this.moveRight();
                    this.DIRECTION = Direction.RIGHT;
                }
                break;
            case RIGHT:
                if(canMoveRight())
                    this.moveRight();
                else{
                    this.moveLeft();
                    this.DIRECTION = Direction.LEFT;
                }
                break;
            default:
                break;
        }

    }

    @Override
	public void drawSelf(){
		String sprite = new String("\uD83C\uDFC0");
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
			System.out.println("Bounce Off! Game Over...");
			game.stop();
        }
    }
}