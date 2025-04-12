package model.blocks;

public class SoftBlock extends Block{
	public SoftBlock(int pX,int pY) {
		super(pX,pY);
	}

	@Override
	public boolean is(String pType) {
		return pType=="Soft";
	}
	
	@Override
	public boolean canMove() {
		return false;
	}

	@Override
	public int getId() {
		return 1;
	}
	@Override
	public boolean kills() {
		return false;
	}
}
