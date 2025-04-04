package viewController.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private String selectedBomberman;
	private JButton[] mapList;
	private int selectedMap;
	private boolean menuMapOpen;

	public MenuView() {
		initialize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		setSize(930,580);
		this.setContentPane(getContentPane());
		addKeyListener(getController());
		
		selectedBomberman="White";
		selectedMap=1;
		
		this.addDeco();
		this.addBombermans();
		
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
	
	private void addDeco() {// TODO Auto-generated method stub
		createMapButtons();
		createDecoPanel("d1",273,120,400,400);
		createDecoPanel("d2",273,30,384,107);
		createButton("set1",860, 17, 50, 50);
		createButton("map1",800, 17, 50, 50);
		randomDeco();
		createTextPanel("USE < / > TO SWITCH BOMBERS",12, 12, 239, 17);
	}
	
	private void createMapButtons() {
		mapList=new JButton[3];
		mapList[0]=createButton("m1",155, 190, 200, 200);
		mapList[0].setVisible(false);
		mapList[1]=createButton("m2",365, 190, 200, 200);
		mapList[1].setVisible(false);
		mapList[2]=createButton("m3",575, 190, 200, 200);
		mapList[2].setVisible(false);
		createDecoPanel("d7",113,140,700,300).setVisible(false);
	}
	
	private void viewMapButtons(boolean pSet) {
		contentPane.getComponentAt(113, 140).setVisible(!pSet);;
		for(int i=0;i<mapList.length;i++) mapList[i].setVisible(!pSet); 
	}
	
	private JLabel createDecoPanel(String pType, int pX, int pY, int pWidth, int pHeigth) {
		JLabel jlDeco=new JLabel();
		jlDeco.setBounds(pX, pY, pWidth, pHeigth);
		jlDeco.setIcon(new ImageIcon(getClass().getResource("texture/deco/"+pType+".png")));
		contentPane.add(jlDeco);
		return jlDeco;
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
	
	private JButton createButton(String pTexture, int pX, int pY, int pWidth, int pHeigth) {
		JButton jb=new JButton();
		jb.setName(pTexture);
		jb.setBounds(pX, pY, pWidth, pHeigth);
		jb.setIcon(new ImageIcon(getClass().getResource("texture/button/"+pTexture+".png")));
		jb.setContentAreaFilled(false);
		jb.setBorderPainted(false);
		jb.addMouseListener(getController());
		jb.setFocusable(false);
		contentPane.add(jb);
		return jb;
	}
	
//-----------------------------BOMBERMANS-------------------------------------
	
	private void addBombermans() {
		bombermanList=new JLabel[2];
		createBomberman(0,12,113,260,100,100);
		createBomberman(1,21,783,260,100,100);
		createDecoPanel("d3",80,260,100,100);
		createDecoPanel("d3",750,260,100,100);
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
		if(pTexture%10==2) selectedBomberman = (pTexture==1)? "White":"Black"; 
	}
	
//-----------------------------CONTROLLER-------------------------------------
	
	private Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	private class Controller implements KeyListener, MouseListener {
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
					GameModel.getGameModel().configurarJuego(selectedBomberman,selectedMap);
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
		
		@Override
		public void mouseClicked(MouseEvent e) {
			JButton jb=(JButton)e.getComponent();
			String name=jb.getName();
			if(name=="set1")
				jb.setIcon(new ImageIcon(getClass().getResource(
						"texture/button/set3.png")));
			else if(name=="map1") {
				if(!menuMapOpen) menuMapOpen=true;
				else menuMapOpen=false;
				jb.setIcon(new ImageIcon(getClass().getResource(
						"texture/button/map3.png")));
				viewMapButtons(menuMapOpen);
			} 
			else if(name=="m1") selectedMap=1;
		    else if(name=="m2") selectedMap=2;
			else if(name=="m3") selectedMap=3;
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			JButton jb=(JButton)e.getComponent();
			if(jb.getName()=="set1") {
				jb.setIcon(new ImageIcon(getClass().getResource(
						"texture/button/set2.png")));
			} else if(jb.getName()=="map1") {
				jb.setIcon(new ImageIcon(getClass().getResource(
						"texture/button/map2.png")));
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			JButton jb=(JButton)e.getComponent();
			if(jb.getName()=="set1") {
				jb.setIcon(new ImageIcon(getClass().getResource(
						"texture/button/set1.png")));
			} else if(jb.getName()=="map1") {
				jb.setIcon(new ImageIcon(getClass().getResource(
						"texture/button/map1.png")));
			}
		}
	}
}
