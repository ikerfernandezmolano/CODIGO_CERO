package viewController;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.Casilla;

public class CasillaView extends JLabel implements Observer{
	
	private static final long serialVersionUID = 1L;

	public CasillaView() {
		super();
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
	}
	
	public void setImagen(int pTipo) {
		if(pTipo==0) {
			this.setIcon(null);
		} else {
			String path=null;
			if(pTipo==1) {
				Random r=new Random();
				path="block/soft"+r.nextInt(2)+".png";
			}else if(pTipo==2) path="block/hard1.png";
			else if(pTipo==3) path="character/monster/m11.png";
			else if(pTipo==4) path="character/player/whitefront1.png";
			else if(pTipo==5) path="attack/bomb1.png";
			else if(pTipo==6) path="attack/bomb1exp.gif";
			this.setIcon(new ImageIcon(getClass().getResource("texture/"+path)));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		int[] res = (int[]) arg;
		this.setImagen(res[0]);
		
	}
}
