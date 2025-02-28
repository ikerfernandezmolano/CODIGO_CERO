package viewController;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CasillaView extends JLabel{
	
	private static final long serialVersionUID = 1L;

	public CasillaView() {
		super();
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
	}
	
	public void setImagen(int pTipo) {
		String path="";
		if(pTipo==1) {
			Random r=new Random();
			if(r.nextInt(5)<3) path="block/soft1.png";
			else path="block/soft2.png";
		} else if(pTipo==2) {
			path="block/hard1.png";
		} else if(pTipo==3) {
			path="character/monster/m11.png";
		} else if(pTipo==4) {
			path="character/player/whitefront1.png";
		} else if(pTipo==5) {
			path="attack/bomb1.png";
		} else if(pTipo==6) {
			path="attack/bomb1exp.gif";
		}
		this.setIcon(new ImageIcon(getClass().getResource("texture/"+path)));
	}
}
