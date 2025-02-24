package viewController;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GestorDePantalla;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

public class Pantalla extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int tipoPantalla;
	private Bloque[] listaBloques;

	public Pantalla(int pTipoPantalla) {
		GestorDePantalla.getGestorDePantalla().addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tipoPantalla=pTipoPantalla;
		initialize();
	}
	
	private void initialize() {
		setSize(850,550);
		this.setContentPane(getContentPane());
		colocarBloques();
		
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void colocarBloques() {
		listaBloques=new Bloque[17*11];
		Random r = new Random();
		for(int j=0;j<11;j++) {
			for(int i=0;i<17;i++) {
				if(i%2!=0 && j%2!=0) {
					añadirBloque(true, i, j);
				} else {
					if(r.nextInt(100)<=65 && i+j>1) {
						añadirBloque(false, i, j);
					} else {
						contentPane.add(new JLabel(""));
					}
				}
			}
		}
	}
	
	private void añadirBloque(boolean pDuro, int pX, int pY) {
		Bloque bloque=new Bloque(pDuro, tipoPantalla);
		listaBloques[pX+pY]=bloque;
		contentPane.add(bloque);
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}