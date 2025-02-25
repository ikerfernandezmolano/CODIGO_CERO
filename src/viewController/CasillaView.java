package viewController;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CasillaView extends JLabel{
	
	public CasillaView() {
		super();
	}
	
	public void setImagen(int pTipo) {
		String path="";
		if(pTipo==1 || pTipo==2) {
			if(pTipo==1) path="soft1.png";
			else if(pTipo==2) path="hard1.png";
			this.setIcon(new ImageIcon(getClass().getResource("texture/block/"+path)));
		}
	}
}
