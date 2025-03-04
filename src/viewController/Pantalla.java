package viewController;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Tablero;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Pantalla extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador;

	public Pantalla() {
		initialize();
		getControlador().actionPerformed(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		setSize(930,580);
		this.setContentPane(getContentPane());
		
		for(int j=0;j<11;j++) {
			for(int i=0;i<17;i++) {
				CasillaView cw= new CasillaView(i,j);
				contentPane.add(cw);
			}
		}
		
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
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
			Tablero.getTablero().crearTablero();
		}
	}

}