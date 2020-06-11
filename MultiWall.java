public class MultiWall extends Wall{
    private int width;
    private int height;

    public MultiWall(GameState game, int x, int y, int width, int height){
        super(game, x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean occupiesPosition(int x, int y){
        int wallX = this.getX();
		int wallY = this.getY();

        if((wallX<=x && wallX+width>x) && (wallY<=y && wallY+height>y))
			return true;
		return false;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public void drawSelf(){
        String sprite = new String("\u2588\u2588");
		int x = this.getX();
		int y = this.getY();
		GameState game = this.getGame();
        
        for(int ix=x; ix<x+width; ix++){
            for(int jy=y; jy<y+height; jy++){
                game.drawSpriteAt(ix, jy, sprite);
            }
        }
    }
}