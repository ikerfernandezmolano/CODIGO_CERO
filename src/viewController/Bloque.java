package viewController;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Bloque extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean duro;
	private int indice;
	private JLabel lblBloque;
	
	public Bloque(boolean pDuro, int pIndice) {
		super();
		initialize();
		indice = pIndice;
		setDuro(pDuro);
	}

	private void initialize() {
		setLayout(new BorderLayout());
		this.add(getLblBloque(),BorderLayout.CENTER);
		this.setOpaque(false);
	}

	private JLabel getLblBloque() {
		if (lblBloque == null) {
			lblBloque = new JLabel("");
		}
		return lblBloque;
	}

	public void setDuro(boolean pDuro) {
		duro = pDuro;
		String imagenBloque;
		
		if (duro) imagenBloque = "texture/block/hard"+indice+".png";
		else imagenBloque = "texture/block/soft"+indice+".png";
		
		getLblBloque().setIcon(
				new ImageIcon(this.getClass().getResource(imagenBloque)));
	}
}