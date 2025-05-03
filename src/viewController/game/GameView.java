package viewController.game;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import model.GameModel;
import viewController.TimerViewTool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private JPanel contentPane, bossPanel, powerPanel;
	private Controller controller;
	private static int xBM,yBM;
	private static int WIDTH,HEIGHT;
	private JLabel timeLabel, enemyLabel;
	ImageIcon powerUpIcon;
	
	public GameView(int pWidth, int pHeight) {
		WIDTH=pWidth;
		HEIGHT=pHeight;
		initialize();
		getController().actionPerformed(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		setSize(930, 620);

	    JPanel mainPanel = new JPanel(new BorderLayout());
	    setContentPane(mainPanel); 
	    mainPanel.add(createTopBarPanel(), BorderLayout.NORTH);
	    contentPane = getContentPane();
	    mainPanel.add(contentPane, BorderLayout.CENTER);
		
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
		JPanel topPanel = new JPanel(){
			private Image backgrnd;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					backgrnd = ImageIO.read(this.getClass().getResource(
							"texture/background/gameBG2.png"));
				} catch (IOException e) {}
				g.drawImage(backgrnd, 0, 0, getWidth(), getHeight(), this);
			}
		};
		topPanel.setLayout(new BorderLayout());
	    topPanel.setPreferredSize(new Dimension(930, 40));
	    topPanel.setOpaque(false);

	    JPanel infoPanel = new JPanel();
	    infoPanel.setOpaque(false);
		infoPanel.setLayout(null);

	    timeLabel = new JLabel("Tiempo: 00:00");
	    timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
	    timeLabel.setBounds(130, 10, 100, 20); // x, y, ancho, alto
	    infoPanel.add(timeLabel);

	    enemyLabel = new JLabel("Enemigos: 6");
	    enemyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    enemyLabel.setBounds(280, 10, 100, 20); // Colocado a la derecha
	    infoPanel.add(enemyLabel);

	    // Boss Panel
	    bossPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
	    bossPanel.setOpaque(false);
	    bossPanel.setBounds(480, 10, 100, 50); // Ejemplo: x=700, y=10, ancho=90
	    JLabel bossTextLabel = new JLabel("Boss: ");
	    bossTextLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
	    bossPanel.add(bossTextLabel);

	    ImageIcon heartIcon = new ImageIcon(getClass().getResource("texture/attack/heart.png"));

	    for (int i = 0; i < 3; i++) {
	        bossPanel.add(new JLabel(heartIcon));
	    }
	    infoPanel.add(bossPanel);

	    // PowerUp Panel
	    powerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
	    powerPanel.setOpaque(false);
	    powerPanel.setBounds(700, 10, 110, 25); 
	    JLabel powerTextLabel = new JLabel("Power: ");
	    powerTextLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
	    powerPanel.add(powerTextLabel);

	    powerUpIcon = new ImageIcon(getClass().getResource("texture/powerUp/notPowerUp.png"));
	    Image scaledPowerUp = powerUpIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
	    powerPanel.add(new JLabel(new ImageIcon(scaledPowerUp)));
	    infoPanel.add(powerPanel);

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
	
	private void actualizarBossVidas(){
	    if(bossPanel.getComponents().length!=0) {
	    	bossPanel.remove(bossPanel.getComponents().length-1);
	    	bossPanel.revalidate();
	        bossPanel.repaint();
	    }
	}
	
	private void actualizarPower(int pNum) {
		if(pNum==0){//Ha gastado el powerUp
			powerPanel.remove(1);
			powerUpIcon = new ImageIcon(getClass().getResource("texture/powerUp/notPowerUp.png"));
		    Image scaledPowerUp = powerUpIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		    powerPanel.add(new JLabel(new ImageIcon(scaledPowerUp)));
		}
		else {//Ha conseguido el powerUp
			powerPanel.remove(1);
			powerUpIcon = new ImageIcon(getClass().getResource("texture/powerUp/powerUp.png"));
		    Image scaledPowerUp = powerUpIcon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		    powerPanel.add(new JLabel(new ImageIcon(scaledPowerUp)));
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
			if(res[0]==3) {
				actualizarEnemigos(res[1]);
			}
			else if(res[0]==31) {
				actualizarBossVidas();
			}
			else if(res[0]==8) {
				actualizarPower(res[1]);
			}
		}
		
	}
}
