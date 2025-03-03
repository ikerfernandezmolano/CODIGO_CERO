package viewController;

import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Casilla;
import model.GestorSemaforos;
import model.Tablero;
import viewController.SemaforoPeatones.Controlador;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Pantalla extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador;

	public Pantalla() {
		Tablero.getTablero().addObserver(this);
		initialize();
		getControlador().actionPerformed(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		setSize(930,580);
		this.setContentPane(getContentPane());
		
		for(int i=0;i<17*11;i++) {
			CasillaView cw= new CasillaView();
			contentPane.add(cw);
		}
		
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		Object[] res= (Object[]) arg;
		Casilla[][] cas= (Casilla[][]) res[0];
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				cas[i][j].addObserver(
						(CasillaView)contentPane.getComponent(j*17+i));
			}
		}
	}
	
//-----------------------------BACKGROUND-------------------------------------
	
	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel(){
			private Image backgrnd;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                	backgrnd = ImageIO.read(
                					this.getClass().getResource(
                							"texture/background/background1.png"));
                } catch (IOException e) {}
                g.drawImage(backgrnd, 0, 0, getWidth(), getHeight(), this);
            }
        };
			contentPane.setLayout(new GridLayout(11, 17, 0, 0));
		}
		return contentPane;
	}

//-----------------------------CONTROLADOR-------------------------------------
	
	private Controlador getControlador() {
		if (controlador == null) {
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Tablero.getTablero().crearPantalla();
		}
	}

}