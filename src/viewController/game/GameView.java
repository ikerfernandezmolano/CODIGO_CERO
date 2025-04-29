package viewController.game;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import model.GameModel;
import viewController.TimerViewTool;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class GameView extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller;
	private static int xBM,yBM;
	private static int WIDTH,HEIGHT;
	private JLabel timeLabel; 
	private JLabel enemyLabel;

	public GameView(int pWidth, int pHeight) {
		WIDTH=pWidth;
		HEIGHT=pHeight;
		initialize();
		getController().actionPerformed(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		setSize(930,580);
		this.setContentPane(getContentPane());
		
		for(int j=0;j<HEIGHT;j++) {
			for(int i=0;i<WIDTH;i++) {
				CellView cw= new CellView(i,j);
				contentPane.add(cw);
			}
		}
		
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		addKeyListener(getController());
		GameModel.getGameModel().addObserver(this);
	}
	private JPanel createTopBarPanel() {
	    JPanel topPanel = new JPanel(new BorderLayout());
	    topPanel.setPreferredSize(new java.awt.Dimension(930, 40));
	    topPanel.setOpaque(false);
	    JPanel infoPanel = new JPanel(new BorderLayout());
	    infoPanel.setOpaque(false); // Para que herede el fondo del topPanel

	    timeLabel = new JLabel("Tiempo: 00:00");
	    timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
	    timeLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Margen izquierdo

	    enemyLabel = new JLabel("Enemigos: " + GameModel.getGameModel());
	    enemyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    enemyLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); // Margen derecho

	    infoPanel.add(timeLabel, BorderLayout.WEST);
	    infoPanel.add(enemyLabel, BorderLayout.EAST);

	    topPanel.add(infoPanel, BorderLayout.CENTER);

	    iniciarCronometro();
	    return topPanel;
	}


	private void iniciarCronometro() {
	    TimerViewTool.getTimerViewTool().startChrono((min, sec) -> {
	        String tiempo = String.format("Tiempo: %02d:%02d", min, sec);
	        timeLabel.setText(tiempo);
	    });
	}
	private void actualizarEnemigos(int pNum) {
		    enemyLabel.setText("Enemigos: " + pNum);
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
                	backgrnd = ImageIO.read(this.getClass().getResource(
                			"texture/background/gameBG1.png"));
                } catch (IOException e) {}
                g.drawImage(backgrnd, 0, 0, getWidth(), getHeight(), this);
            }
        };
			contentPane.setLayout(new GridLayout(HEIGHT, WIDTH, 0, 0));
		}
		return contentPane;
	}

//-----------------------------CONTROLADOR-------------------------------------
	
	private Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	private class Controller implements ActionListener,KeyListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			GameModel.getGameModel().crearTablero();
	    }
	    
		private void handleKeyPressed(int pKeyCode) {
			int dirBM=0;
			if(!GameModel.getGameModel().getEstadoPartida()) {
				switch (pKeyCode) {
					case KeyEvent.VK_DOWN:
						dirBM=11;
						if (GameModel.getGameModel().moverseBM(xBM, yBM, xBM, yBM+1)) 
							yBM++;
						break;
					case KeyEvent.VK_UP:
						dirBM=21;
						if (GameModel.getGameModel().moverseBM(xBM, yBM, xBM, yBM-1))
							yBM--;
						break;
					case KeyEvent.VK_LEFT:
						dirBM=31;
						if (GameModel.getGameModel().moverseBM(xBM, yBM, xBM-1, yBM))
							xBM--;
						break;
					case KeyEvent.VK_RIGHT:
						dirBM=41;
						if (GameModel.getGameModel().moverseBM(xBM, yBM, xBM+1, yBM)) 
							xBM++;
						break;
					case KeyEvent.VK_SPACE:
							dirBM=5;
							GameModel.getGameModel().colocarBomba(xBM,yBM);
						break;
				}
				if(!GameModel.getGameModel().getEstadoPartida()) 
					((CellView)contentPane.getComponent(yBM*17+xBM)).setDirBM(dirBM);
				else {
					((CellView)contentPane.getComponent(yBM*17+xBM)).setDirBM(6);
					TimerViewTool.getTimerViewTool().stop(1);
				}
			}
		}
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode=e.getKeyCode();
			getController().handleKeyPressed(keyCode);
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GameModel) {
			int[] res= (int[]) arg;
			if(res.length==1) {
				actualizarEnemigos(res[0]);
			}
		}
		
	}
}
