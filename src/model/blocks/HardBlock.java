package model.blocks;

public class HardBlock extends Block{
	public HardBlock(int pX,int pY) {
		super(pX,pY);
	}

	@Override
	public boolean is(String pType) {
		return pType=="Hard";
	}
	
	@Override
	public boolean canMove() {
		return false;
	}

	@Override
	public int getId() {
		return 2;
	}
	@Override
	public boolean kills() {
		return false;
	}

}