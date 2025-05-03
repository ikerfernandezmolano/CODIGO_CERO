package viewController.game;

public class Position {
	private int x,y;
	
	public Position() {}
	
	public void changePosition(int pX, int pY) {
		x=pX;
		y=pY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void moveUp(){
		y++;
	}

	public void moveDown(){
		y--;
	}

	public void moveLeft(){
		x--;
	}

	public void moveRight(){
		x++;
	}
	
	public boolean isPosition(int pX,int pY) {
        return x == pX && y == pY;
    }

}
