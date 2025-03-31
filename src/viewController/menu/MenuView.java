package viewController.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.GameModel;
import viewController.game.GameView;

import java.awt.Font;

public class MenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controller controller;
	private JLabel[] bombermanList;
	private int selectedBomberman;

	public MenuView() {
		initialize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		setSize(930,580);
		this.setContentPane(getContentPane());
		addKeyListener(getController());
		
		selectedBomberman=1;
		this.addBombermans();
		this.addDeco();
		
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
                							"texture/background/menuBG1.png"));
                } catch (IOException e) {}
                g.drawImage(backgrnd, 0, 0, getWidth(), getHeight(), this);
            }
        };
			contentPane.setLayout(null);
		}
		return contentPane;
	}
	
//-----------------------------DECORATION-------------------------------------
	
	private void addDeco() {
		createDecoPanel("d1",273,120,400,400);
		createDecoPanel("d2",273,30,384,107);
		createDecoPanel("d3",80,260,100,100);
		createDecoPanel("d3",750,260,100,100);
		createDecoPanel("d7",860, 17, 50, 50);
		randomDeco();
		createTextPanel("USE ← / → TO SWITCH BOMBERS",12, 12, 239, 17);
	}
	
	private void createDecoPanel(String pType, int pX, int pY, int pWidth, int pHeigth) {
		JLabel jlDeco=new JLabel();
		jlDeco.setBounds(pX, pY, pWidth, pHeigth);
		jlDeco.setIcon(new ImageIcon(getClass().getResource("texture/deco/"+pType+".png")));
		contentPane.add(jlDeco);
	}	
	
	private void randomDeco() {
		Random r= new Random();
		for(int i=0; i<20;i++) 
			createDecoPanel("d"+r.nextInt(5, 7),r.nextInt(20, 910),r.nextInt(410,530),65,65);
	}
	
	private void createTextPanel(String pText, int pX, int pY, int pWidth, int pHeigth) {
		JLabel jlText=new JLabel(pText);
		jlText.setFont(new Font("DialogInput", Font.BOLD, 14));
		jlText.setHorizontalAlignment(SwingConstants.LEFT);
		jlText.setBounds(pX, pY, pWidth, pHeigth);
		contentPane.add(jlText);
	}
	
//-----------------------------BOMBERMANS-------------------------------------
	
	private void addBombermans() {
		bombermanList=new JLabel[2];
		createBomberman(0,12,113,260,100,100);
		createBomberman(1,21,783,260,100,100);
	}
	
	private void createBomberman(int pIndex, int pType, int pX, int pY, int pWidth, int pHeigth) {
		JLabel jlBomberman=new JLabel();
		jlBomberman.setBounds(pX,pY, pWidth, pHeigth);
		jlBomberman.setIcon(new ImageIcon(getClass().getResource("texture/choose/"+pType+".png")));
		bombermanList[pIndex]=jlBomberman;
		contentPane.add(jlBomberman);
	}
	
	private void setBomberman(int pBomberman, int pTexture) {
		bombermanList[pBomberman-1].setIcon(new ImageIcon(getClass().getResource(
				"texture/choose/"+pTexture+".png")));
		if(pTexture%10==2) selectedBomberman=pBomberman; 
	}
	
//-----------------------------CONTROLLER-------------------------------------
	
	private Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	private class Controller implements KeyListener {
		public void handleKeyPressed(int pKeyCode) {
			switch (pKeyCode) {
				case KeyEvent.VK_LEFT:
					setBomberman(1,12);
					setBomberman(2,21);
					break;
				case KeyEvent.VK_RIGHT:
					setBomberman(2,22);
					setBomberman(1,11);
					break;
				case KeyEvent.VK_ENTER:
					//MODEL
					GameModel.getGameModel();
					//VIEW
					GameView gameView=new GameView();
					//CLOSE
					dispose();
					break;
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
