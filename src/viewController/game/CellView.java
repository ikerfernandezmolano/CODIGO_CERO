package viewController.game;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.GameModel;
import viewController.TimerViewTool;

public class CellView extends JLabel implements Observer{

	 /*Void = 0
	 * SoftBlock = 1
	 * HardBlock = 2
	 * Enemy = 3
	 * Bomberman = 4
	 * Bomb = 5
	 * Explosion = 6
	 * PowerUp = 8
	 */
	
	private static final long serialVersionUID = 1L;
	private int dirBM=0;
	private String typeBM="white";

	public CellView(int pX,int pY) {
		super();
		GameModel.getGameModel().getCell(pX, pY).addObserver(this);
		
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
	}
	
	public void setImage(int pType) {
		if(pType==0) {
			this.setIcon(null);
		} else {
			String path="texture/";
			switch(pType) {
				case 1:
					Random r=new Random();
					path+="block/soft"+r.nextInt(2)+".png";
					break;
				case 2:
					path+="block/hard1.png";
					break;
				case 3:
					path+="character/monster/m11.png";
					break;
				case 31:
					path+="character/monster/boss.png";
					break;
				case 41:
					path+="character/player/white/"+dirBM+".png";
					break;
				case 42:
					typeBM="black";
					path+="character/player/black/"+dirBM+".png";
					break;
				case 51:
					path+="attack/bomb1.png";
					break;
				case 52:
					path+="attack/bomb2.png";
					break;
				case 53:
					path+="attack/bomb3.png";
					break;
				case 6:
					path+="attack/bombexp.gif";
					break;
				case 71:
					path+="character/player/white/6.png";
					TimerViewTool.getTimerViewTool().stop(2);
					break;
				case 72:
					path+="character/player/black/6.png";
					TimerViewTool.getTimerViewTool().stop(2);
					break;
				case 8:
					path+="powerUp/powerUp.png";
					break;
			}
			this.setIcon(new ImageIcon(getClass().getResource(path)));
		}
	}
	
	public void setDirBM(int pDirBM) {
		if(pDirBM/10!=0 && pDirBM/10==dirBM/10) {
			if(dirBM % 10 == 1) dirBM++;
			else dirBM--;
		} else dirBM=pDirBM;
		this.setIcon(new ImageIcon(getClass().getResource(
				"texture/character/player/"+typeBM+"/"+dirBM+".png")));
	}

	@Override
	public void update(Observable o, Object arg) {
		int[] res= (int[])arg;
		this.setImage(res[0]);	
	}
}