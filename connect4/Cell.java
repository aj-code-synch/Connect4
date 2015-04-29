package connect4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Cell extends JPanel implements MouseMotionListener{

	private String status; 
	public Cell(){
		setPreferredSize(new Dimension(80,80));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		setBackground(Color.WHITE);
	}

	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		super.paintComponent(g);
		g.drawOval(5, 5, 70, 70);

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
