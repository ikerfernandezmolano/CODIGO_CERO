package viewController;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Tablero;

public class CasillaView extends JLabel implements Observer{

	 /*Void = 0
	 * SoftBlock = 1
	 * HardBlock = 2
	 * Enemie = 3
	 * Bomberman = 4
	 * Bomb = 5
	 * Explosion = 6
	 */
	
	private static final long serialVersionUID = 1L;
	private int x,y;
	private int dirBM=0;

	public CasillaView(int pX,int pY) {
		super();
		x=pX;
		y=pY;
		Tablero.getTablero().getCell(pX, pY).addObserver(this);
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
	}
	
	public void setImage(int pType) {
		if(pType==0) {
			this.setIcon(null);
		} else {
			String path=null;
			if(pType==1) {
				Random r=new Random();
				path="block/soft"+r.nextInt(2)+".png";
			}else if(pType==2) path="block/hard1.png";
			else if(pType==3) path="character/monster/m11.png";
			else if(pType==4) path="character/player/w"+dirBM+".png";
			else if(pType==5) path="attack/bomb1.png";
			else if(pType==6) path="attack/bomb1exp.gif";
			else if(pType==7) {
				path="character/player/w6.png";
				TimerTool.getTimerTool().stop(2);
			}
			this.setIcon(new ImageIcon(getClass().getResource("texture/"+path)));
		}
	}
	
	public void setDirBM(int pDirBM) {
		if(pDirBM/10!=0 && pDirBM/10==dirBM/10) {
			if(dirBM % 10 == 1) dirBM++;
			else dirBM--;
		} else dirBM=pDirBM;
		this.setIcon(new ImageIcon(getClass().getResource(
				"texture/character/player/w"+dirBM+".png")));
	}

	@Override
	public void update(Observable o, Object arg) {
		int[] res = (int[]) arg;
		this.setImage(res[0]);	
	}
}