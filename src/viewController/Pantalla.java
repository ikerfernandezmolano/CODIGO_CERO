package viewController;

import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Tablero;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

public class Pantalla extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Pantalla() {
		Tablero.getTablero().addObserver(this);
		initialize();
		Tablero.getTablero().crearPantalla();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		setSize(930,580);
		this.setContentPane(getContentPane());
		
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				CasillaView cw= new CasillaView();
				contentPane.add(cw);
			}
		}
		
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void actualizar(int[] pLista) {
		for (int i = 0; i < pLista.length; i++) {
	        if (contentPane.getComponent(i) instanceof CasillaView) {
	            ((CasillaView) contentPane.getComponent(i)).setImagen(pLista[i]);
	        }
	    }
	}

	@Override
	public void update(Observable o, Object arg) {
		Object[] res= (Object[]) arg;
		actualizar((int[]) res[0]);
	}
	
	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel(){
			private Image backgroundImage;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                	backgroundImage = ImageIO.read(
                					this.getClass().getResource("texture/background/background1.png"));
                } catch (IOException e) {}
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
			contentPane.setLayout(new GridLayout(11, 17, 0, 0));
		}
		return contentPane;
	}

}