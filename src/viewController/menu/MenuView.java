package viewController.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel[] bombermanList;
	
	public static void main(String args[]) {
		MenuView menuView=new MenuView();
	}

	public MenuView() {
		initialize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		setSize(930,580);
		this.setContentPane(getContentPane());
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
		contentPane.add(createDecoPanel("title",273,30,384,107));
		contentPane.add(createDecoPanel("d1",273,120,400,400));
	}
	
	private JLabel createDecoPanel(String pType, int pX, int pY, int pWidth, int pHeigth) {
		JLabel jlDeco=new JLabel();
		jlDeco.setBounds(pX, pY, pWidth, pHeigth);
		jlDeco.setIcon(new ImageIcon(getClass().getResource("texture/deco/"+pType+".png")));
		return jlDeco;
	}
	
//-----------------------------BOMBERMANS-------------------------------------
	
	private void addBombermans() {
		bombermanList=new JLabel[2];
		bombermanList[0]=new JLabel();
		bombermanList[0].setBounds(50, 50, 50, 50);
		bombermanList[0].setIcon(new ImageIcon(getClass().getResource("texture/choose/11.png")));
		contentPane.add(bombermanList[0]);
		bombermanList[1]=new JLabel();
		bombermanList[1].setBounds(750, 50, 50, 50);
		bombermanList[1].setIcon(new ImageIcon(getClass().getResource("texture/choose/21.png")));
		contentPane.add(bombermanList[1]);
	}
}
