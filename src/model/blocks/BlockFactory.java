package model.blocks;

public class BlockFactory {
	private static BlockFactory miBF=new BlockFactory();
	
	private BlockFactory() {}

	public static BlockFactory getBlockFactory() {
		return miBF;
	}
	
	public Block generate(String pType, int pX, int pY) {
		Block block=null;
		if(pType=="Soft") block=new SoftBlock(pX,pY);
		else if(pType=="Hard") block=new HardBlock(pX,pY);
		else if(pType=="Void") block=new Void(pX, pY);
		return block;
	}
}
