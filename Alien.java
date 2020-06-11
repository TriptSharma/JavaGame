public class Alien extends Monster{
    // The Alien, moves in leftwards, until it cannot go any further, 
    // then it will turn 90 degrees to the left and continue.
    private int counter;

    public Alien(GameState game, int x, int y){
        super(game, x, y);
        counter=0;
    }

   public void tick(){
        // counter=0 left, counter=1 down, counter=2 right, counter=3 up
        switch(counter){
            case 0:
                if(this.canMoveLeft()){
                    moveLeft();
                    break;
                }
                counter=(counter+1)%4;
            case 1:
                if(this.canMoveDown()){
                    moveDown();
                    break;
                }
                counter=(counter+1)%4;
            case 2:
                if(this.canMoveRight()){
                    moveRight();
                    break;
                }
                counter=(counter+1)%4;
            case 3:
                if(this.canMoveUp()){
                    moveUp();
                    break;
                }
                counter=(counter+1)%4;
            default:
                break;
        }
    }

    @Override
    public void drawSelf(){
        String sprite = new String("\uD83D\uDC7D");
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
            System.out.println("Captured by Aliens! Game Over...");
            game.stop();
        }
    }
}