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
	private int tipoPantalla=1;
	private Bloque[] listaBloques=new Bloque[17*11];

	public Pantalla() {
		GestorDePantalla.getGestorDePantalla().addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}
	
	private void initialize() {
		setSize(850,550);
		this.setContentPane(getContentPane());
		
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void añadirBloque(boolean pVacio,boolean pDuro, int pPos) {
		if(pVacio) {
			JPanel jp=new JPanel();
			contentPane.add(jp);
		}
		else {
			Bloque bloque=new Bloque(pDuro, tipoPantalla);
			listaBloques[pPos]=bloque;
			contentPane.add(bloque);
		}
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
		Object[] res= (Object[]) arg;
		int[] r1= (int[])res[0];
		for(int i=0; i<r1.length;i++) {
			System.out.println(r1[i]);
			if(r1[i]==0) añadirBloque(true, false, i);
			else if(r1[i]==1) añadirBloque(false, false, i);
			else if(r1[i]==2) añadirBloque(false, true, i);
		}
	}

}