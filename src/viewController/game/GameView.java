package viewController.game;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.GameModel;
import viewController.TimerViewTool;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameView extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller;
	private int xBM;
	private int yBM;

	public GameView() {
		initialize();
		getController().actionPerformed(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		setSize(930,580);
		this.setContentPane(getContentPane());
		
		for(int j=0;j<11;j++) {
			for(int i=0;i<17;i++) {
				CellView cw= new CellView(i,j);
				contentPane.add(cw);
			}
		}
		
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		addKeyListener(getController());
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
			contentPane.setLayout(new GridLayout(11, 17, 0, 0));
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
		public void actionPerformed(ActionEvent e) {
			GameModel.getGameModel().crearTablero();
			TimerViewTool.getTimerViewTool().startEnemyMovement(1000);
	    }
	    
		public void handleKeyPressed(int pKeyCode) {
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
}
