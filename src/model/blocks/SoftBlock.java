package model.blocks;

public class SoftBlock extends Block{
	public SoftBlock(int pX,int pY) {
		super(pX,pY);
	}

	@Override
	public boolean is(String pType) {
		return pType=="Soft";
	}
}
