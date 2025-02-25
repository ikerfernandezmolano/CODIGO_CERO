package viewController;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Tablero;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

public class Pantalla extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int tipoPantalla=1;
	private Celda[] listaBloques=new Celda[17*11];

	public Pantalla() {
		Tablero.getTablero().addObserver(this);
		Tablero.getTablero().crearPantalla();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}
	
	public static void main(String[] args) {
		//VISTA//
		@SuppressWarnings("unused")
		Pantalla pantalla= new Pantalla();
	}
	
	private void initialize() {
		setSize(850,550);
		this.setContentPane(getContentPane());
		
		for(int i=0;i<17*11;i++) contentPane.add(new JLabel());
		
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
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
                					this.getClass().getResource("texture/background/background"+tipoPantalla+".png"));
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
		
	}

}