package viewController.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel bombermanList;
	private JLabel lblNewLabel_1;
	
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
			contentPane.add(getLblNewLabel());
		}
		return contentPane;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel();
			lblNewLabel_1.setBounds(357, 178, 216, 228);
			lblNewLabel_1.setIcon(new ImageIcon(MenuView.class.getResource("/viewController/menu/texture/deco/d1.png")));
		}
		return lblNewLabel_1;
	}
}
